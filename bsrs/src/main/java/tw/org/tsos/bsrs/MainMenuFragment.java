package tw.org.tsos.bsrs;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragment extends Fragment implements View.OnClickListener {

    public MainMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WelcomeMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuFragment newInstance() {
        MainMenuFragment fragment = new MainMenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome_menu, container, false);
        List<View> childList = getAllChildrenBFS(view);
        for (View child : childList) {
            if (child instanceof TextView) {
                TextView text = (TextView) child;
                text.setTypeface(BsrsApplication.getFontAwesomeTypeface(getActivity()));
                text.setOnClickListener(this);
            }
        }
        return view;
    }

    public List<View> getAllChildrenBFS(View v) {
        List<View> visited = new ArrayList<View>();
        List<View> unvisited = new ArrayList<View>();
        unvisited.add(v);

        while (!unvisited.isEmpty()) {
            View child = unvisited.remove(0);
            visited.add(child);
            if (!(child instanceof ViewGroup)) {
                continue;
            }
            ViewGroup group = (ViewGroup) child;
            final int childCount = group.getChildCount();
            for (int i = 0; i < childCount; i++) {
                unvisited.add(group.getChildAt(i));
            }
        }

        return visited;
    }

    @Override
    public void onClick(View v) {
        int tapPosition = -1;
        String number = null;
        switch (v.getId()) {
            case R.id.button_relieved_line:
                number = getString(R.string.phone_number_of_relieved);
                break;
            case R.id.button_tether:
                number = getString(R.string.phone_number_of_teacher);
                break;
            case R.id.button_line:
                number = getString(R.string.phone_number_of_line);
                break;
            case R.id.button_quiz:
                tapPosition = 0;
                break;
            case R.id.button_resource:
                tapPosition = 1;
                break;
            case R.id.button_ebook:
                tapPosition = 3;
                break;
            case R.id.button_records:
                tapPosition = 4;
                break;
        }
        Intent intent = null;

        if (tapPosition != -1) {
            intent = new Intent(getActivity(), BsrsActivity.class);
            intent.putExtra(BsrsApplication.TAP_EXTRA, tapPosition);
        }
        if (number != null) {
            Uri uri = Uri.parse("tel:" + number);
            intent = new Intent(Intent.ACTION_DIAL, uri);

        }
        if (intent != null) {
            PackageManager packageManager = getActivity().getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
            boolean isIntentSafe = activities.size() > 0;
            if (isIntentSafe) {
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "請使用電話撥打" + number, Toast.LENGTH_LONG).show();
            }
        }
    }
}
