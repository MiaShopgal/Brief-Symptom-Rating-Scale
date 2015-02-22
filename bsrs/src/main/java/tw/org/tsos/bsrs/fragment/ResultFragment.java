package tw.org.tsos.bsrs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import tw.org.tsos.bsrs.R;
import tw.org.tsos.bsrs.activity.BsrsActivity;
import tw.org.tsos.bsrs.util.db.MyDatabase;
import tw.org.tsos.bsrs.util.db.bean.Record;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    private static final String ARG_SCORE = "score";
    private static final String ARG_BAD_IDEA = "bad_idea";
    private static final String TAG = ResultFragment.class.getSimpleName();

    private int mScore;
    private boolean mBadIdea;
    private View view;

    public ResultFragment() {
        // Required empty public constructor
    }

    public static ResultFragment newInstance(int scores, boolean badIdea) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, scores);
        args.putBoolean(ARG_BAD_IDEA, badIdea);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mScore = getArguments().getInt(ARG_SCORE);
            mBadIdea = getArguments().getBoolean(ARG_BAD_IDEA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_result, container, false);
        if (savedInstanceState != null) {
            return view;
        }
        TextView scoreText = (TextView) view.findViewById(R.id.score_text);
        scoreText.setText(String.valueOf(mScore));

        TextView levelText = (TextView) view.findViewById(R.id.level_info);
        TextView levelTitle = (TextView) view.findViewById(R.id.level_title);
        int level = 1;
        if (mScore <= 5) {
            level = 1;
        }
        if (6 <= mScore && mScore <= 9) {
            level = 2;
        }
        if (10 <= mScore && mScore <= 14) {
            level = 3;
        }
        if (mScore >= 15) {
            level = 4;
        }
        ImageView light = (ImageView) view.findViewById(R.id.light);

        switch (level) {
            case 1:
                levelText.setText(getString(R.string.score_level_1_text));
                levelTitle.setText(getString(R.string.score_level_1_title_text));
                light.setImageDrawable(getResources().getDrawable(R.drawable.green));
                break;
            case 2:
                levelText.setText(getString(R.string.score_level_2_text));
                levelTitle.setText(getString(R.string.score_level_2_title_text));
                light.setImageDrawable(getResources().getDrawable(R.drawable.yellow));
                break;
            case 3:
                levelText.setText(getString(R.string.score_level_3_text));
                levelTitle.setText(getString(R.string.score_level_3_title_text));
                light.setImageDrawable(getResources().getDrawable(R.drawable.red));
                break;
            case 4:
                levelText.setText(getString(R.string.score_level_4_text));
                levelTitle.setText(getString(R.string.score_level_4_title_text));
                light.setImageDrawable(getResources().getDrawable(R.drawable.red));
                break;
        }
        ImageView red = (ImageView) view.findViewById(R.id.red_light);
        if (level == 4) {
            red.setVisibility(View.VISIBLE);
        } else {
            red.setVisibility(View.GONE);
        }
        Record record = new Record();
        record.setDate(System.currentTimeMillis());
        record.setScore(mScore);
        record.setLevel(level);
        MyDatabase myDatabase = new MyDatabase(getActivity());
        Log.d(TAG, "xxx id=" + myDatabase.saveRecord(record));

        View resource = view.findViewById(R.id.view_resource);
        resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = ((BsrsActivity) getActivity()).mSlidingTabsFragment.getmViewPager();
                viewPager.setCurrentItem(1);
            }
        });
        if (mScore > 6 || mBadIdea) {
            resource.setVisibility(View.VISIBLE);
        } else {
            resource.setVisibility(View.GONE);
        }
        view.findViewById(R.id.button_level).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.fragment_blank, new ScoreLevelFragment());

                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.commit();
            }
        });
        view.findViewById(R.id.button_quiz_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_blank, new BlankFragment());

                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBadIdea) {
            view.findViewById(R.id.result_hint).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.result_hint).setVisibility(View.GONE);
        }
    }
}
