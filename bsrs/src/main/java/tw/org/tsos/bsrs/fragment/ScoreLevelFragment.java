package tw.org.tsos.bsrs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.org.tsos.bsrs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreLevelFragment extends Fragment {

    public ScoreLevelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_score_level, container, false);
        if (savedInstanceState != null) {
            return view;
        }
        view.findViewById(R.id.view_resource).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.pager);
                //                viewPager.setCurrentItem(1);
            }
        });
        return view;
    }

}
