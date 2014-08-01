package tw.org.tsos.bsrs.util.db;

import android.provider.BaseColumns;

/**
 * Created by Mia on 7/31/14.
 */
@SuppressWarnings("DefaultFileTemplate")
public class ResourceEntry implements BaseColumns {

    public static final String TABLE_NAME = "resource";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String AREA = "area";
    public static final String COUNTY = "county";
    public static final String ZIP = "zip";
    public static final String PHONE = "phone";

    public ResourceEntry() {
    }
}
