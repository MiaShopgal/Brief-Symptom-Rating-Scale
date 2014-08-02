package tw.org.tsos.bsrs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tw.org.tsos.bsrs.util.LEVEL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    private static final String ARG_SCORE = "score";
    private static final String ARG_BAD_IDEA = "bad_idea";

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
        scoreText.setText(String.format(getString(R.string.score_text), mScore));

        TextView face = (TextView) view.findViewById(R.id.score_face);
        face.setTypeface(BsrsApplication.getFontAwesomeTypeface(getActivity()));
        if (mScore < 10) {
            face.setText(getString(R.string.fa_smile_o));
        } else {
            face.setText(getString(R.string.fa_frown_o));
        }

        TextView levelText = (TextView) view.findViewById(R.id.level_info);
        LEVEL level = LEVEL.ONE;
        if (mScore <= 5) {
            level = LEVEL.ONE;
        }
        if (6 <= mScore && mScore <= 9) {
            level = LEVEL.TWO;
        }
        if (10 <= mScore && mScore <= 14) {
            level = LEVEL.THREE;
        }
        if (mScore >= 15) {
            level = LEVEL.FOUR;
        }

        switch (level) {
            case ONE:
                levelText.setText(getString(R.string.score_level_1_text));
                break;
            case TWO:
                levelText.setText(getString(R.string.score_level_2_text));
                break;
            case THREE:
                levelText.setText(getString(R.string.score_level_3_text));
                break;
            case FOUR:
                levelText.setText(getString(R.string.score_level_4_text));
                break;
        }

        view.findViewById(R.id.button_resource).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.pager);
                viewPager.setCurrentItem(1);
            }
        });
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
