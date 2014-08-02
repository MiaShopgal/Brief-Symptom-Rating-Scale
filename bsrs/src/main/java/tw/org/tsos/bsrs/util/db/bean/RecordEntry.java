package tw.org.tsos.bsrs.util.db.bean;

import android.provider.BaseColumns;

/**
 * Created by Mia on 7/31/14.
 */
@SuppressWarnings("DefaultFileTemplate")
public class RecordEntry implements BaseColumns {

    public static final String TABLE_NAME = "record";
    public static final String DATE = "date";
    public static final String SCORE = "score";
    public static final String TEXT = "text";

    public RecordEntry() {
    }
}
