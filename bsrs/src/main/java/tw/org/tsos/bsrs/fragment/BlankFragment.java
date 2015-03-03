package tw.org.tsos.bsrs.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.org.tsos.bsrs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    public static final java.lang.String SET_PROFILE = "SET_PROFILE";
    private static final String TAG = BlankFragment.class.getSimpleName();

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance() {
        BlankFragment blankFragment = new BlankFragment();
        Bundle args = new Bundle();
        blankFragment.setArguments(args);
        return blankFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "on create view");
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "on resume should with arguments");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        int defaultValue = sharedPref.getInt(getString(R.string.gender), 0);
        if (getArguments() != null) {
            if (getArguments().getBoolean(SET_PROFILE)) {
                setProfile(fragmentTransaction);
                Log.d(TAG, "SET_PROFILE true");
                setProfile(fragmentTransaction);
            } else {
                Log.d(TAG, "SET_PROFILE false");
                if (defaultValue == 0) {
                    Log.d(TAG, "defaultValue 0");
                    setProfile(fragmentTransaction);
                } else {
                    Log.d(TAG, "defaultValue != 0");
                    newQuiz(fragmentTransaction);
                }
            }
        }
    }

    public void setProfile(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.replace(R.id.fragment_blank, new ProfileFragment(),ProfileFragment.class.getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void newQuiz(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.replace(R.id.fragment_blank, new QuizFragment(),QuizFragment.class.getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }
}
