package tw.org.tsos.bsrs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class BsrsActivity extends ActionBarActivity implements ActionBar.TabListener {

    private static final String TAG = BsrsActivity.class.getSimpleName();
    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    NavUtils.navigateUpFromSameTask(getParent());
                    Log.d(TAG, "home");
                    break;
                case R.id.bsrs_action_profile:
                    Log.d(TAG, "bsrs_action_profile");
                    break;
                case R.id.bsrs_action_call:
                    Log.d(TAG, "bsrs_action_call");
                    break;
            }
            return true;
        }
    };
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private int tapPosition;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsrs);
        if (savedInstanceState != null) {
            return;
        }
        Intent intent = getIntent();
        tapPosition = intent.getExtras().getInt(BsrsApplication.TAP_EXTRA);
        Log.d(TAG, "showing empty view with tapPosition =" + tapPosition);
        /*final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);*/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.home);
        //        mViewPager = (ViewPager) findViewById(R.id.pager);

        //        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
        //        mViewPager.setAdapter(mSectionsPagerAdapter);
        //        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        //            @Override
        //            public void onPageSelected(int position) {
        //                super.onPageSelected(position);
        ////                actionBar.setSelectedNavigationItem(position);
        //            }
        //        });
        /*for (int i = 0; i < 5; i++) {
            actionBar.addTab(actionBar.newTab().setText(getResources().getStringArray(R.array.taps_name)[i]).setTabListener(this));
        }*/
        //        mViewPager.setCurrentItem(tapPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bsrs, menu);
        return true;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
        Log.d(TAG, "select " + tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Log.d(TAG, "UN select " + tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        int position = tab.getPosition();
        Log.d(TAG, "RE select " + position);
    }
}
