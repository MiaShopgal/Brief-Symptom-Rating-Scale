package tw.org.tsos.bsrs.util.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "bsrs.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteQueryBuilder sqLiteQueryBuilder;
    private String[] resourceColumns = {ResourceEntry.NAME,
            ResourceEntry.ADDRESS,
            ResourceEntry.AREA,
            ResourceEntry.COUNTY,
            ResourceEntry.ZIP,
            ResourceEntry.PHONE};
    private String[] ebookColumns = {EbookEntry.NAME, EbookEntry.LINK};

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        sqLiteDatabase = getReadableDatabase();
        sqLiteQueryBuilder = new SQLiteQueryBuilder();

        // you can use an alternate constructor to specify a database location
        // (such as a folder on the sd card)
        // you must ensure that this folder is available and you have permission
        // to write to it
        //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);

    }

    @SuppressWarnings("UnusedDeclaration")
    public Cursor getResources() {

        Cursor c = sqLiteQueryBuilder.query(sqLiteDatabase, resourceColumns, null, null, null, null, null);

        c.moveToFirst();
        return c;

    }

    public List<Resource> queryResources(String county, String area) {
        List<Resource> Resources = new ArrayList<Resource>();
        sqLiteQueryBuilder.setTables(ResourceEntry.TABLE_NAME);
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, resourceColumns,
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
        sqLiteQueryBuilder.setTables(ResourceEntry.TABLE_NAME);
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, resourceColumns, null, null, null, null, null);

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

    public List<Ebook> getAllEbook() {
        List<Ebook> ebookList = new ArrayList<Ebook>();
        sqLiteQueryBuilder.setTables(EbookEntry.TABLE_NAME);
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, ebookColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Ebook ebook = cursorToEbook(cursor);
            ebookList.add(ebook);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return ebookList;
    }

    private Ebook cursorToEbook(Cursor cursor) {
        Ebook ebook = new Ebook();
        ebook.setName(cursor.getString(0));
        ebook.setLink(cursor.getString(1));
        return ebook;
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
