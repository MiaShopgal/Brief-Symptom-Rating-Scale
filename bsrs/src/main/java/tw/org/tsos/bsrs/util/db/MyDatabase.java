package tw.org.tsos.bsrs.util.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "resource.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteQueryBuilder sqLiteQueryBuilder;
    private String[] allColumns = {ResourceEntry.NAME,
            ResourceEntry.ADDRESS,
            ResourceEntry.AREA,
            ResourceEntry.COUNTY,
            ResourceEntry.ZIP,
            ResourceEntry.PHONE};

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        sqLiteDatabase = getReadableDatabase();
        sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(ResourceEntry.TABLE_NAME);

        // you can use an alternate constructor to specify a database location
        // (such as a folder on the sd card)
        // you must ensure that this folder is available and you have permission
        // to write to it
        //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);

    }

    @SuppressWarnings("UnusedDeclaration")
    public Cursor getResources() {

        Cursor c = sqLiteQueryBuilder.query(sqLiteDatabase, allColumns, null, null, null, null, null);

        c.moveToFirst();
        return c;

    }

    public List<Resource> queryResources(String county, String area) {
        List<Resource> Resources = new ArrayList<Resource>();

        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, allColumns,
                                                 ResourceEntry.AREA + "=\"" + area + "\" AND " + ResourceEntry.COUNTY + "=\"" + county + "\"", null,
                                                 null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Resource Resource = cursorToResource(cursor);
            Resources.add(Resource);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return Resources;
    }

    public List<Resource> getAllResources() {
        List<Resource> Resources = new ArrayList<Resource>();

        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Resource Resource = cursorToResource(cursor);
            Resources.add(Resource);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return Resources;
    }

    private Resource cursorToResource(Cursor cursor) {
        Resource resource = new Resource();
        resource.setName(cursor.getString(0));
        resource.setAddress(cursor.getString(1));
        resource.setArea(cursor.getString(2));
        resource.setCounty(cursor.getString(3));
        resource.setZip(cursor.getInt(4));
        resource.setPhone(cursor.getString(5));
        return resource;
    }
}
