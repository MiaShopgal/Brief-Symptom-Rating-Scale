package tw.org.tsos.bsrs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private static final String TAG = BlankFragment.class.getSimpleName();

    public BlankFragment() {
        // Required empty public constructor
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
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Log.d(TAG, "on resume");
        newQuiz(fragmentTransaction);
    }

    public void newQuiz(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.replace(R.id.fragment_blank, new QuizFragment());
        fragmentTransaction.commitAllowingStateLoss();
    }
}
