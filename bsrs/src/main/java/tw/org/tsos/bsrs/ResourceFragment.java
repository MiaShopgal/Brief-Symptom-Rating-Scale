package tw.org.tsos.bsrs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tw.org.tsos.bsrs.util.db.MyDatabase;
import tw.org.tsos.bsrs.util.db.Resource;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResourceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResourceFragment extends Fragment {

    private static final String TAG = ResourceFragment.class.getSimpleName();

    public ResourceFragment() {
        // Required empty public constructor
    }

    public static ResourceFragment newInstance() {
        ResourceFragment fragment = new ResourceFragment();
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

        View view = inflater.inflate(R.layout.fragment_resource, container, false);
        MyDatabase myDatabase = new MyDatabase(getActivity());
        List<Resource> cursor = myDatabase.getAllResources();
        Log.d(TAG, "cursor=" + cursor);

        /*List<String[]> list = new ArrayList<String[]>();
        try {
            String next[] = {};
            CSVReader csvReader = new CSVReader(new InputStreamReader(getActivity().getAssets().open("resource.csv")));
            for (; ; ) {
                next = csvReader.readNext();
                if (next != null) {
                    list.add(next);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "first row" + Arrays.toString(list.get(0)));*/
        return view;
    }

}
