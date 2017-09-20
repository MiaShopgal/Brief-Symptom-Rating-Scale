package tw.org.tsos.bsrs.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import tw.org.tsos.bsrs.R;
import tw.org.tsos.bsrs.util.ResourceAdapter;
import tw.org.tsos.bsrs.util.csv.MakeRequestTask;
import tw.org.tsos.bsrs.util.db.MyDatabase;
import tw.org.tsos.bsrs.util.db.bean.Resource;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResourceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResourceFragment extends Fragment {

    private static final String TAG = ResourceFragment.class.getSimpleName();
    private Spinner countySpinner;
    private Spinner areaSpinner;
    private ArrayList<String> countyDistinct;
    private ArrayList<String> areaDistinct;
    private MyDatabase myDatabase;
    private ResourceAdapter resourceAdapter;
    private List<Resource> resourceList;
    private ArrayAdapter areaAdapter;

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
        myDatabase = new MyDatabase(getActivity());
        resourceList = myDatabase.getAllResources();
        Log.d(TAG, "resourceList=" + resourceList);
        ListView listView = view.findViewById(R.id.resource_list);
        resourceAdapter = new ResourceAdapter(getActivity(), 0, resourceList);
        listView.setAdapter(resourceAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Resource resource = (Resource) parent.getItemAtPosition(position);
                Log.d(TAG, "resource=" + resource);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(String.format(getString(R.string.map_search_keyword), resource.getAddress())));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        countySpinner = view.findViewById(R.id.county);
        areaSpinner = view.findViewById(R.id.area);
        List<String> countyList = new ArrayList<>();
        for (Resource resource : resourceList) {
            countyList.add(resource.getCounty());
        }
        countyDistinct = new ArrayList<>(new LinkedHashSet<>(countyList));

        updateArea(0);
        //noinspection unchecked
        SpinnerAdapter countyAdapter = new ArrayAdapter(getActivity(), R.layout.spinner_item, countyDistinct);
        //noinspection unchecked
        areaAdapter = new ArrayAdapter(getActivity(), R.layout.spinner_item, areaDistinct);

        areaSpinner.setAdapter(areaAdapter);
        countySpinner.setAdapter(countyAdapter);

        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "choosing " + areaDistinct.get(position));
                updateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        countySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "choosing " + countyDistinct.get(position));
                updateArea(position);
                updateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        new MakeRequestTask().execute();

        return view;
    }


    private void updateArea(int index) {
        List<String> areaByCounty = myDatabase.queryArea(countyDistinct.get(index));
        Log.d(TAG, "areaByCounty=" + areaByCounty);
        areaDistinct = new ArrayList<>(new LinkedHashSet<>(areaByCounty));
        //noinspection unchecked
        areaAdapter = new ArrayAdapter(getActivity(), R.layout.spinner_item, areaDistinct);
        areaSpinner.setAdapter(areaAdapter);
    }

    private void updateList() {
        String county = countyDistinct.get(countySpinner.getSelectedItemPosition());
        String area = areaDistinct.get(areaSpinner.getSelectedItemPosition());
        List<Resource> queryResources = myDatabase.queryResources(county, area);
        resourceList.clear();
        resourceList.addAll(queryResources);
        resourceAdapter.notifyDataSetChanged();
    }

}
