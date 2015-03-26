package tw.org.tsos.bsrs.util.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import tw.org.tsos.bsrs.util.db.bean.Ebook;
import tw.org.tsos.bsrs.util.db.bean.EbookEntry;
import tw.org.tsos.bsrs.util.db.bean.Record;
import tw.org.tsos.bsrs.util.db.bean.RecordEntry;
import tw.org.tsos.bsrs.util.db.bean.Resource;
import tw.org.tsos.bsrs.util.db.bean.ResourceEntry;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "bsrs.db";
    private static final int DATABASE_VERSION = 3;
    @SuppressWarnings("UnusedDeclaration")
    private static final String TAG = MyDatabase.class.getSimpleName();
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteQueryBuilder sqLiteQueryBuilder;
    private String[] resourceColumns = {ResourceEntry.NAME,
            ResourceEntry.ADDRESS,
            ResourceEntry.AREA,
            ResourceEntry.COUNTY,
            ResourceEntry.ZIP,
            ResourceEntry.PHONE};
    private String[] ebookColumns = {EbookEntry.NAME, EbookEntry.LINK, EbookEntry.COVER};
    private String[] recordColumns = {RecordEntry.DATE, RecordEntry.SCORE, RecordEntry.LEVEL};

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        sqLiteDatabase = getWritableDatabase();
        //        setForcedUpgrade();
        sqLiteQueryBuilder = new SQLiteQueryBuilder();

        // you can use an alternate constructor to specify a database location
        // (such as a folder on the sd card)
        // you must ensure that this folder is available and you have permission
        // to write to it
        //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);

    }

    public long saveRecord(Record record) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RecordEntry.DATE, record.getDate());
        contentValues.put(RecordEntry.SCORE, record.getScore());
        contentValues.put(RecordEntry.LEVEL, record.getLevel());
        long index = sqLiteDatabase.insert(RecordEntry.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return index;
    }

    public List<String> queryArea(String county) {
        List<String> areaList = new ArrayList<>();
        sqLiteQueryBuilder.setTables(ResourceEntry.TABLE_NAME);
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, new String[]{ResourceEntry.AREA}, ResourceEntry.ADDRESS + " like \"" + county +
                                                         "%\"", null, null, null, null
        );

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String area = cursor.getString(0);
            areaList.add(area);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return areaList;
    }

    public List<Resource> queryResources(String county, String area) {
        List<Resource> Resources = new ArrayList<>();
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
        List<Resource> Resources = new ArrayList<>();
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
        List<Ebook> ebookList = new ArrayList<>();
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

    public List<Record> getAllRecord() {
        List<Record> recordList = new ArrayList<>();
        sqLiteQueryBuilder.setTables(RecordEntry.TABLE_NAME);
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, recordColumns, null, null, null, null, RecordEntry.DATE + " DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Record record = cursorToRecord(cursor);
            recordList.add(record);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return recordList;
    }

    private Record cursorToRecord(Cursor cursor) {
        Record record = new Record();
        record.setDate(cursor.getLong(0));
        record.setScore(cursor.getInt(1));
        record.setLevel(cursor.getInt(2));
        return record;
    }

    private Ebook cursorToEbook(Cursor cursor) {
        Ebook ebook = new Ebook();
        ebook.setName(cursor.getString(0));
        ebook.setLink(cursor.getString(1));
        ebook.setCover(cursor.getString(2));
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
