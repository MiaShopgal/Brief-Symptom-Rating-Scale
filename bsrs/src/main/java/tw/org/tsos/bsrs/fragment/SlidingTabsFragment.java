package tw.org.tsos.bsrs.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tw.org.tsos.bsrs.R;
import tw.org.tsos.bsrs.adapter.SectionsPagerAdapter;
import tw.org.tsos.bsrs.view.SlidingTabLayout;

public class SlidingTabsFragment extends Fragment {

    public int mDefaultPosition;
    public List<Fragment> mTabs = new ArrayList<>();
    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;
    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;

    public ViewPager getmViewPager() {
        return mViewPager;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTabs.add(BlankFragment.newInstance());
        mTabs.add(new RecordsFragment());
        mTabs.add(new EbookFragment());
        mTabs.add(new ResourceFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sliding_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view,
                              @Nullable
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SectionsPagerAdapter(getChildFragmentManager(), getActivity(), mTabs));
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tab);
        mSlidingTabLayout.setViewPager(mViewPager);
        mViewPager.setCurrentItem(mDefaultPosition);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
