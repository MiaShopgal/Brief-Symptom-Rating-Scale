package tw.org.tsos.bsrs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WelcomeMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelcomeMenuFragment extends Fragment {

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
        Button callTeacher = (Button) view.findViewById(R.id.button_tether);
        callTeacher.setTypeface(BsrsApplication.getFontAwesomeTypeface(getActivity()));

        Button callLine = (Button) view.findViewById(R.id.button_line);
        callLine.setTypeface(BsrsApplication.getFontAwesomeTypeface(getActivity()));
        return view;
    }

}
