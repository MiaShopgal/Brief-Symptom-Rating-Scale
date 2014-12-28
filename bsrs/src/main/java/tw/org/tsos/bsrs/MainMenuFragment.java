package tw.org.tsos.bsrs;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = MainMenuFragment.class.getSimpleName();

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
        TextView text = (TextView) view.findViewById(R.id.main_menu_phone_icon_text);
        text.setTypeface(BsrsApplication.getFontAwesomeTypeface(getActivity()));

        view.findViewById(R.id.view_quiz).setOnClickListener(this);
        view.findViewById(R.id.view_ebook).setOnClickListener(this);
        view.findViewById(R.id.view_records).setOnClickListener(this);
        view.findViewById(R.id.view_records).setOnClickListener(this);
        view.findViewById(R.id.button_relieved_line).setOnClickListener(this);
        view.findViewById(R.id.button_tether).setOnClickListener(this);
        view.findViewById(R.id.button_line).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "clicking");
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
            case R.id.view_quiz:
                Log.d(TAG, "clicking quiz");
                tapPosition = 0;
                break;
            case R.id.view_resource:
                tapPosition = 1;
                break;
            case R.id.view_ebook:
                tapPosition = 3;
                break;
            case R.id.view_records:
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
