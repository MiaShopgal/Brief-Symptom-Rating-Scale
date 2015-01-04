package tw.org.tsos.bsrs.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

import tw.org.tsos.bsrs.R;

/**
 * Created by Miao on 7/12/14.
 */
@SuppressWarnings("DefaultFileTemplate")
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @SuppressWarnings("UnusedDeclaration")
    private static final String TAG = SectionsPagerAdapter.class.getSimpleName();
    private final Activity mActivity;
    private final List<Fragment> mFragments;

    public SectionsPagerAdapter(FragmentManager fm, Activity activity, List<Fragment> fragments) {
        super(fm);
        mActivity = activity;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        Log.d(TAG, "getting item " + i);
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mActivity.getResources().getStringArray(R.array.taps_name)[position];
    }

}
