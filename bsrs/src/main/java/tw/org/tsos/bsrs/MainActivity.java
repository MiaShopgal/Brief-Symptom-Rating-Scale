package tw.org.tsos.bsrs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.fragment_main, null);
        setContentView(view);
        findViewById(R.id.button_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BsrsActivity.class);
                startActivity(intent);
            }
        });
        //        setupEvent(view, R.id.main_button_map, R.string.mainPageCategory, R.string.mainPageView, R.string.clickingGoogleMap);
        findViewById(R.id.main_button_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                GoogleAnalytics.getInstance(getApplicationContext()).dispatchLocalHits();
                Tracker t = ((BsrsApplication) getApplication()).getTracker(BsrsApplication.TrackerName.APP_TRACKER);
                // Build and send an Event.
                t.send(new HitBuilders.EventBuilder().setCategory(getString(R.string.mainPageCategory))
                                                     .setAction(getString(R.string.mainPageView))
                                                     .setLabel(getString(R.string.clickingGoogleMap))
                                                     .build());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.map_search_keyword)));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Tracker t = ((BsrsApplication) getApplication()).getTracker(BsrsApplication.TrackerName.APP_TRACKER);
        t.setScreenName(String.valueOf(R.string.homePath));
        t.send(new HitBuilders.AppViewBuilder().build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("UnusedDeclaration")
    private void setupEvent(View v, int buttonId, final int categoryId, final int actionId, final int labelId) {
        final Button pageViewButton = (Button) v.findViewById(buttonId);
        pageViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get tracker.
                Tracker t = ((BsrsApplication) getApplication()).getTracker(BsrsApplication.TrackerName.APP_TRACKER);
                // Build and send an Event.
                t.send(new HitBuilders.EventBuilder().setCategory(getString(categoryId))
                                                     .setAction(getString(actionId))
                                                     .setLabel(getString(labelId))
                                                     .build());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.map_search_keyword)));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
