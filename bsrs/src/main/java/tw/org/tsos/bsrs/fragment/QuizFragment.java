package tw.org.tsos.bsrs.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tw.org.tsos.bsrs.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this quizFragment.
 */
public class QuizFragment extends Fragment {

    // the quizFragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = QuizFragment.class.getSimpleName();
    private TextView quizNumber;
    private TextView quizWording;
    private int mQuiz = 1;
    private boolean mBadIdea = false;
    private ArrayList<View> highlight;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance() {

        QuizFragment quizFragment = new QuizFragment();
        Bundle args = new Bundle();
        quizFragment.setArguments(args);
        return quizFragment;
    }

    private void setColor(TextView view, String fulltext, String subtext, int color) {
        view.setText(fulltext, TextView.BufferType.SPANNABLE);
        Spannable str = (Spannable) view.getText();
        int i = fulltext.indexOf(subtext);
        str.setSpan(new ForegroundColorSpan(color), i, i + subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        final View mView = inflater.inflate(R.layout.fragment_quiz_new, container, false);
        if (savedInstanceState != null) {
            return mView;
        }

        TextView instruction = (TextView) mView.findViewById(R.id.quiz_instruction);
        setColor(instruction, (String) instruction.getText(), getString(R.string.quiz_instruction_hight_light),
                 getResources().getColor(R.color.pink));

        highlight = new ArrayList<>();
        highlight.add(mView.findViewById(R.id.quiz_option_zero_highlight));
        highlight.add(mView.findViewById(R.id.quiz_option_one_highlight));
        highlight.add(mView.findViewById(R.id.quiz_option_two_highlight));
        highlight.add(mView.findViewById(R.id.quiz_option_three_highlight));
        highlight.add(mView.findViewById(R.id.quiz_option_four_highlight));

        quizNumber = (TextView) mView.findViewById(R.id.quiz_number);
        quizWording = (TextView) mView.findViewById(R.id.quiz_wording);
        final int[] score = {-1};

        final LinearLayout quizOptions = (LinearLayout) mView.findViewById(R.id.quiz_options);
        for (int i = 0; i < quizOptions.getChildCount(); i++) {
            final View child = quizOptions.getChildAt(i);
            //noinspection UnusedDeclaration
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    unMarkAll();
                    switch (v.getId()) {
                        case R.id.quiz_option_zero:
                            score[0] = 0;
                            ((TextView) mView.findViewById(R.id.quiz_option_zero_highlight)).setTextColor(Color.WHITE);
                            mView.findViewById(R.id.quiz_option_zero_highlight).setBackgroundResource(R.drawable.circle_green);
                            break;
                        case R.id.quiz_option_one:
                            score[0] = 1;
                            ((TextView) mView.findViewById(R.id.quiz_option_one_highlight)).setTextColor(Color.WHITE);
                            mView.findViewById(R.id.quiz_option_one_highlight).setBackgroundResource(R.drawable.circle_yellow);
                            break;
                        case R.id.quiz_option_two:
                            score[0] = 2;
                            ((TextView) mView.findViewById(R.id.quiz_option_two_highlight)).setTextColor(Color.WHITE);
                            mView.findViewById(R.id.quiz_option_two_highlight).setBackgroundResource(R.drawable.circle_orange);
                            break;
                        case R.id.quiz_option_three:
                            score[0] = 3;
                            ((TextView) mView.findViewById(R.id.quiz_option_three_highlight)).setTextColor(Color.WHITE);
                            mView.findViewById(R.id.quiz_option_three_highlight).setBackgroundResource(R.drawable.circle_orange_red);
                            break;
                        case R.id.quiz_option_four:
                            score[0] = 4;
                            ((TextView) mView.findViewById(R.id.quiz_option_four_highlight)).setTextColor(Color.WHITE);
                            mView.findViewById(R.id.quiz_option_four_highlight).setBackgroundResource(R.drawable.circle_red);
                            break;
                    }
                    if (mQuiz == 6 && score[0] >= 2) {
                        mBadIdea = true;
                    }
                    Log.d(TAG, "mBadIdea=" + mBadIdea);
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
                unMarkAll();
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
                        Log.d(TAG, "before replace with child fragment manager");
                        fragmentTransaction.replace(R.id.fragment_blank, ResultFragment.newInstance(sum, mBadIdea));

                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        break;
                }

                mQuiz++;
                switch (mQuiz) {
                    default:
                        quizNumber.setText(String.valueOf(mQuiz));
                        break;
                    case 6:
                        quizNumber.setText(getString(R.string.quiz_extra));
                        quizNumber.setTextAppearance(getActivity(), android.R.style.TextAppearance);
                        break;
                }
                Log.d(TAG, "sum=" + sum + ", next mQuiz=" + mQuiz);
                score[0] = -1;

            }
        });
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    private void unMarkAll() {
        for (View view : highlight) {
            view.setBackgroundResource(R.drawable.circle_grey);
            ((TextView) view).setTextColor(Color.BLACK);
        }
    }
}
