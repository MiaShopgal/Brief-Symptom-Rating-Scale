package tw.org.tsos.bsrs;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this quizFragment.
 */
public class QuizFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the quizFragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = QuizFragment.class.getSimpleName();
    private View mView;
    private TextView quizNumber;
    private TextView quizWording;

    public QuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this quizFragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of quizFragment QuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(String param1, String param2) {

        QuizFragment quizFragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        quizFragment.setArguments(args);
        return quizFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this quizFragment
        mView = inflater.inflate(R.layout.fragment_quiz, container, false);
        if (savedInstanceState != null) {
            return mView;
        }
        final LinearLayout highlight = (LinearLayout) mView.findViewById(R.id.highlight_options);
        quizNumber = (TextView) mView.findViewById(R.id.quiz_number);
        quizWording = (TextView) mView.findViewById(R.id.quiz_wording);
        final int[] score = {-1};

        final LinearLayout quizOptions = (LinearLayout) mView.findViewById(R.id.quiz_options);
        for (int i = 0; i < quizOptions.getChildCount(); i++) {
            final View child = quizOptions.getChildAt(i);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    unMarkAll();
                    switch (v.getId()) {
                        case R.id.quiz_option_zero:
                            score[0] = 0;
                            mView.findViewById(R.id.quiz_option_zero_highlight).setBackgroundColor(Color.YELLOW);
                            break;
                        case R.id.quiz_option_one:
                            score[0] = 1;
                            mView.findViewById(R.id.quiz_option_one_highlight).setBackgroundColor(Color.YELLOW);
                            break;
                        case R.id.quiz_option_two:
                            score[0] = 2;
                            mView.findViewById(R.id.quiz_option_two_highlight).setBackgroundColor(Color.YELLOW);
                            break;
                        case R.id.quiz_option_three:
                            score[0] = 3;
                            mView.findViewById(R.id.quiz_option_three_highlight).setBackgroundColor(Color.YELLOW);
                            break;
                        case R.id.quiz_option_four:
                            score[0] = 4;
                            mView.findViewById(R.id.quiz_option_four_highlight).setBackgroundColor(Color.YELLOW);
                            break;
                    }
                }

                private void unMarkAll() {
                    for (int i = 0; i < highlight.getChildCount(); i++) {
                        highlight.getChildAt(i).setBackgroundColor(Color.GRAY);
                    }
                }
            });
        }

        final Button button = (Button) mView.findViewById(R.id.button_next_quiz);
        button.setOnClickListener(new View.OnClickListener() {
            int sum;
            int quiz = 1;

            @Override
            public void onClick(View v) {
                if (score[0] == -1) {
                    Toast.makeText(getActivity(), "請選擇", Toast.LENGTH_LONG).show();
                    return;
                }
                for (int i = 0; i < highlight.getChildCount(); i++) {
                    View child = highlight.getChildAt(i);
                    child.setBackgroundColor(Color.GRAY);
                }

                switch (quiz) {
                    case 1:
                        quizWording.setText(R.string.quiz_question_2);
                        break;
                    case 2:
                        quizWording.setText(R.string.quiz_question_3);
                        break;
                    case 3:
                        quizWording.setText(R.string.quiz_question_4);
                        break;
                    case 4:
                        quizWording.setText(R.string.quiz_question_5);
                        break;
                    case 5:
                        quizWording.setText(R.string.quiz_question_six);
                        button.setText(getString(R.string.quiz_result));
                        break;
                    case 6:
                        FragmentTransaction trans = getFragmentManager().beginTransaction();
                        trans.replace(R.id.fragment_blank, new CallFragment());

                        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        trans.addToBackStack(null);

                        trans.commit();
                        break;
                }
                if (quiz != 5) {
                    sum = sum + score[0];
                }
                quiz++;
                if (quiz <= 6) {
                    quizNumber.setText(String.valueOf(quiz));
                }
                Log.d(TAG, "sum=" + sum + ", next quiz=" + quiz);
                score[0] = -1;
            }
        });
        return mView;
    }

}
