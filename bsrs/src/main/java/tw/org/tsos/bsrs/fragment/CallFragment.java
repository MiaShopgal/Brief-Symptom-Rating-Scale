package tw.org.tsos.bsrs.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextThemeWrapper;
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

    private static final String TAG = CallFragment.class.getSimpleName();

    public CallFragment() {
        // Required empty public constructor
    }

    public static CallFragment newInstance() {
        CallFragment fragment = new CallFragment();
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
        //        View view = inflater.inflate(R.layout.fragment_call, container, false);
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.call_style);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View view = localInflater.inflate(R.layout.fragment_call, container, false);
        Log.d(TAG, "onCreateView");
        TextView text = (TextView) view.findViewById(R.id.main_menu_phone_icon_text);
        text.setTypeface(BsrsApplication.getFontAwesomeTypeface(getActivity()));
        TextView shareText = (TextView) view.findViewById(R.id.textView2);
        shareText.setText(getString(R.string.two_strings, getString(R.string.please_save_line_to_whom_in_need), getString(R.string.fa_smile_o)));
        shareText.setTypeface(BsrsApplication.getFontAwesomeTypeface(getActivity()));
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.call_main_layout);
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {

            View childView = relativeLayout.getChildAt(i);
            if (childView instanceof TextView) {
                TextView child = (TextView) childView;
                child.setTypeface(BsrsApplication.getFontAwesomeTypeface(getActivity()));
                child.setOnClickListener(this);
            }
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
            case R.id.button_teacher:
                number = getString(R.string.phone_number_of_teacher);
                break;
            case R.id.button_line:
                number = getString(R.string.phone_number_of_line);
                break;
        }
        Intent intent;
        if (number != null) {
            Uri uri = Uri.parse("tel:" + number);
            intent = new Intent(Intent.ACTION_DIAL, uri);

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
