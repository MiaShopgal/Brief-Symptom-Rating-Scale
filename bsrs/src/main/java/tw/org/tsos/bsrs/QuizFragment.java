package tw.org.tsos.bsrs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;

    public QuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(String param1, String param2) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_quiz, container, false);
        LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.qz_list);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View child = linearLayout.getChildAt(i);
            child.setOnClickListener(this);
        }
        return mView;
    }

    @Override
    public void onClick(View v) {
        int nextImage = 0;
        switch (v.getId()) {
            case R.id.imageView:
                nextImage = 1;
                break;
            /*case R.id.imageView2:
                nextImage = 2;
                break;
            case R.id.imageView3:
                nextImage = 3;
                break;
            case R.id.imageView4:
                nextImage = 4;
                break;
            case R.id.imageView5:
                nextImage = 5;
                break;
            case R.id.imageView6:
                nextImage = 6;
                break;*/
            case R.id.imageView7:
                nextImage = 2;
                break;
            case R.id.imageView8:
                nextImage = 0;
                break;
        }
        updateView(nextImage);
    }

    private void updateView(int nextImage) {
        LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.qz_list);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View child = linearLayout.getChildAt(i);
            child.setVisibility(View.GONE);
        }
        linearLayout.getChildAt(nextImage)
                    .setVisibility(View.VISIBLE);
    }
}
