package tw.org.tsos.bsrs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miao on 7/12/14.
 */
@SuppressWarnings("DefaultFileTemplate")
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    public List<Fragment> mFragmentList;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new QuizFragment());
        mFragmentList.add(new ResourceFragment());
        mFragmentList.add(new CallFragment());
        mFragmentList.add(new EbookFragment());
        mFragmentList.add(new RecordsFragment());
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources()
                       .getStringArray(R.array.taps_name)[position];

    }
}
