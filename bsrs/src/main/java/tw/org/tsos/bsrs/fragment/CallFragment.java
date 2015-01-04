package tw.org.tsos.bsrs.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tw.org.tsos.bsrs.BsrsApplication;
import tw.org.tsos.bsrs.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CallFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public CallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CallFragment newInstance(String param1, String param2) {
        CallFragment fragment = new CallFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View view = inflater.inflate(R.layout.fragment_call, container, false);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.call_main_layout);
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            TextView child = (TextView) relativeLayout.getChildAt(i);
            child.setTypeface(BsrsApplication.getFontAwesomeTypeface(getActivity()));
            child.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        String number = null;
        switch (v.getId()) {
            case R.id.button_relieved_line:
                number = getString(R.string.phone_number_of_relieved);
                break;
            case R.id.button_teather:
                number = getString(R.string.phone_number_of_teacher);
                break;
            case R.id.button_line:
                number = getString(R.string.phone_number_of_line);
                break;
        }
        Intent intent = null;

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
