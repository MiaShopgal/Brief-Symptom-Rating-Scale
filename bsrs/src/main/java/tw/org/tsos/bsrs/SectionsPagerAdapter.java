package tw.org.tsos.bsrs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by Miao on 7/12/14.
 */
@SuppressWarnings("DefaultFileTemplate")
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @SuppressWarnings("UnusedDeclaration")
    private static final String TAG = SectionsPagerAdapter.class.getSimpleName();
    private final Context mContext;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        Log.d(TAG, "getting item " + i);
        switch (i) {
            case 0:
                return new BlankFragment();
            case 1:
                return new ResourceFragment();
            case 2:
                return new CallFragment();
            case 3:
                return new EbookFragment();
            case 4:
                return new RecordsFragment();
            default:
                return new BlankFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getStringArray(R.array.taps_name)[position];
    }

}
