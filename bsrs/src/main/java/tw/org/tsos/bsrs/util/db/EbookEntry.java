package tw.org.tsos.bsrs.util.db;

import android.provider.BaseColumns;

/**
 * Created by Mia on 7/31/14.
 */
@SuppressWarnings("DefaultFileTemplate")
public class EbookEntry implements BaseColumns {

    public static final String TABLE_NAME = "ebook";
    public static final String NAME = "name";
    public static final String LINK = "link";

    public EbookEntry() {
    }
}
