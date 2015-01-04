package tw.org.tsos.bsrs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import tw.org.tsos.bsrs.BsrsApplication;
import tw.org.tsos.bsrs.R;
import tw.org.tsos.bsrs.fragment.CallFragment;
import tw.org.tsos.bsrs.fragment.SlidingTabsFragment;

public class BsrsActivity extends ActionBarActivity {

    private static final String TAG = BsrsActivity.class.getSimpleName();
    public SlidingTabsFragment mSlidingTabsFragment;
    private FragmentTransaction transaction;
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
                    //FIXME create new fragment for profile
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new CallFragment());
                    transaction.commit();
                    break;
                case R.id.bsrs_action_call:
                    Log.d(TAG, "bsrs_action_call");
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new CallFragment());
                    transaction.commit();
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsrs);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            int tapPosition = intent.getExtras().getInt(BsrsApplication.TAP_EXTRA);
            Log.d(TAG, "showing empty view with tapPosition =" + tapPosition);
        /*final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);*/
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(R.drawable.home);

            transaction = getSupportFragmentManager().beginTransaction();
            mSlidingTabsFragment = new SlidingTabsFragment();
            mSlidingTabsFragment.mDefaultPosition = tapPosition;
            transaction.replace(R.id.fragment_container, mSlidingTabsFragment);
            transaction.commit();
        }
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
}
