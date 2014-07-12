package tw.org.tsos.bsrs;

import android.app.Application;
import android.graphics.Typeface;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by bono on 5/31/14.
 */
@SuppressWarnings("DefaultFileTemplate")
public class BsrsApplication extends Application {

    private static final String PROPERTY_ID = "UA-42058753-2";
    private static final String TAG = BsrsActivity.class.getSimpleName();
    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = (trackerId == TrackerName.APP_TRACKER) ?
                    analytics.newTracker(PROPERTY_ID) :
                    (trackerId == TrackerName.GLOBAL_TRACKER) ?
                            analytics.newTracker(R.xml.global_tracker) :
                            analytics.newTracker(R.xml.global_tracker);
            mTrackers.put(trackerId, t);

        }
        return mTrackers.get(trackerId);
    }

    private void setDefaultFont() {

        try {
            String fontAwesome = "fontawesome.ttf";
            final Typeface bold = Typeface.createFromAsset(getAssets(), fontAwesome);
            final Typeface italic = Typeface.createFromAsset(getAssets(), fontAwesome);
            final Typeface boldItalic = Typeface.createFromAsset(getAssets(), fontAwesome);
            final Typeface regular = Typeface.createFromAsset(getAssets(), fontAwesome);
            //NOTE:for action bar
            Field SERIF = Typeface.class.getDeclaredField("SERIF");
            SERIF.setAccessible(true);
            SERIF.set(null, regular);

            Field DEFAULT = Typeface.class.getDeclaredField("DEFAULT");
            DEFAULT.setAccessible(true);
            DEFAULT.set(null, regular);

            Field DEFAULT_BOLD = Typeface.class.getDeclaredField("DEFAULT_BOLD");
            DEFAULT_BOLD.setAccessible(true);
            DEFAULT_BOLD.set(null, bold);

            Field sDefaults = Typeface.class.getDeclaredField("sDefaults");
            sDefaults.setAccessible(true);
            sDefaults.set(null, new Typeface[]{regular, bold, italic, boldItalic});

        } catch (NoSuchFieldException e) {
            Log.d(TAG, e.getMessage());
        } catch (IllegalAccessException e) {
            Log.d(TAG, e.getMessage());
        } catch (Throwable e) {
            //cannot crash app if there is a failure with overriding the default font!
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setDefaultFont();
    }

    /**
     * Enum used to identify the tracker that needs to be used for tracking.
     * <p/>
     * A single tracker is usually enough for most purposes. In case you do need multiple trackers,
     * storing them all in Application object helps ensure that they are created only once per
     * application instance.
     */
    public enum TrackerName {
        APP_TRACKER,
        // Tracker used only in this app.
        GLOBAL_TRACKER,
        // Tracker used by all the apps from a company. eg: roll-up tracking.
        ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
    }
}
