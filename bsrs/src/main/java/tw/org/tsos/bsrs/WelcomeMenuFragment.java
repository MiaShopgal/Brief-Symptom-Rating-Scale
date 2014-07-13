package tw.org.tsos.bsrs;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WelcomeMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelcomeMenuFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WelcomeMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WelcomeMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WelcomeMenuFragment newInstance(String param1, String param2) {
        WelcomeMenuFragment fragment = new WelcomeMenuFragment();
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
        View view = inflater.inflate(R.layout.fragment_welcome_menu, container, false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.layout_list);
        for (int i = 1; i < linearLayout.getChildCount(); i++) { //NOTE first child is image view
            TextView child = (TextView) linearLayout.getChildAt(i);
            child.setTypeface(BsrsApplication.getFontAwesomeTypeface(getActivity()));
            child.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        int tapPosition = -1;
        String number = null;
        switch (v.getId()) {
            case R.id.button_tether:
                number = "1955";
                break;
            case R.id.button_line:
                number = "1980";
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
                Toast.makeText(getActivity(), "請使用電話撥打" + number, Toast.LENGTH_LONG)
                     .show();
            }
        }
    }
}
