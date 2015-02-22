package tw.org.tsos.bsrs.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import tw.org.tsos.bsrs.MyVolley;
import tw.org.tsos.bsrs.R;
import tw.org.tsos.bsrs.util.VolleyErrorHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WelcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelcomeFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = WelcomeFragment.class.getSimpleName();
    OnIntroClickListener mOnIntroClickListener;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WelcomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WelcomeFragment newInstance(String param1, String param2) {
        WelcomeFragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "being clicked");
        mOnIntroClickListener.onIntroClicked();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnIntroClickListener = (OnIntroClickListener) activity;
        } catch (Exception e) {
            throw new ClassCastException(activity.toString() + " must implement OnIntroClickListener");

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        view.findViewById(R.id.welcome_intro).setOnClickListener(this);

        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();

        int screenWidthInPix = displayMetrics.widthPixels;

        int screenHeightInPix = displayMetrics.heightPixels;
        final String url = "http://tspc.tw/tspc/uploadfiles/Image/02.jpg";
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Log.d(TAG, "response");
                Drawable drawable = new BitmapDrawable(getResources(), response);
                RelativeLayout background = (RelativeLayout) view.findViewById(R.id.welcome_intro);
                //noinspection deprecation
                background.setBackgroundDrawable(drawable);
            }
        }, screenWidthInPix, screenHeightInPix, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "on error response " + VolleyErrorHelper.getMessage(error, url));
            }
        });
        MyVolley.getRequestQueue().add(request);
        return view;
    }

    public interface OnIntroClickListener {

        public void onIntroClicked();
    }

}
