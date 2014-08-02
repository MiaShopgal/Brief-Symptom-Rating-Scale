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

    // the quizFragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = QuizFragment.class.getSimpleName();
    private View mView;
    private TextView quizNumber;
    private TextView quizWording;
    private int mQuiz = 1;
    private boolean mBadIdea = false;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance() {

        QuizFragment quizFragment = new QuizFragment();
        Bundle args = new Bundle();
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
                            mBadIdea = mQuiz == 6;
                            Log.d(TAG, "mBadIdea=" + mBadIdea);
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
                if (mQuiz != 6) {
                    sum = sum + score[0];
                }
                switch (mQuiz) {
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
                        mQuiz = 0;
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_blank, ResultFragment.newInstance(sum, mBadIdea));

                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        break;
                }

                mQuiz++;
                if (mQuiz <= 6) {
                    quizNumber.setText(String.valueOf(mQuiz));
                }
                Log.d(TAG, "sum=" + sum + ", next mQuiz=" + mQuiz);
                score[0] = -1;
            }
        });
        return mView;
    }

}
