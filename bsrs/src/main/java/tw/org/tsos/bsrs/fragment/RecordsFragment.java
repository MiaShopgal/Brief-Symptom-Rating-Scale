package tw.org.tsos.bsrs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import tw.org.tsos.bsrs.R;
import tw.org.tsos.bsrs.util.RecordAdapter;
import tw.org.tsos.bsrs.util.db.MyDatabase;
import tw.org.tsos.bsrs.util.db.bean.Record;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordsFragment extends Fragment {

    private static final String TAG = RecordsFragment.class.getSimpleName();
    private List<Record> recordList;
    private MyDatabase myDatabase;
    private RecordAdapter recordAdapter;
    private ListView listView;
    private LinearLayout recordListLayout;

    public RecordsFragment() {
        // Required empty public constructor
    }

    public static RecordsFragment newInstance() {
        RecordsFragment fragment = new RecordsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d(TAG, "isVisibleToUser");
            if (recordList == null) {
                return;
            }
            recordList.clear();
            recordList = myDatabase.getAllRecord();
            Log.d(TAG, "setUserVisibleHint recordList size=" + recordList.size());
            if (recordList.size() > 0) {
                recordAdapter = new RecordAdapter(getActivity(), 0, recordList);
                listView.setAdapter(recordAdapter);
                recordAdapter.notifyDataSetChanged();
                recordListLayout.setVisibility(View.VISIBLE);
            } else {
                recordListLayout.setVisibility(View.GONE);
            }
        } else {
            Log.d(TAG, "is NOT VisibleToUser");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_records, container, false);
        myDatabase = new MyDatabase(getActivity());
        recordList = myDatabase.getAllRecord();
        Log.d(TAG, "onCreateView recordList size=" + recordList.size());
        listView = (ListView) view.findViewById(R.id.record_list);
        recordListLayout = (LinearLayout) view.findViewById(R.id.record_list_layout);
        recordAdapter = new RecordAdapter(getActivity(), 0, recordList);
        listView.setAdapter(recordAdapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goQuiz();
            }
        });*/
        View hint = view.findViewById(R.id.record_hint);
        //        listView.setEmptyView(hint);
        if (recordList.size() > 0) {
            recordListLayout.setVisibility(View.VISIBLE);
        } else {
            recordListLayout.setVisibility(View.GONE);
        }
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goQuiz();
            }
        });
        return view;
    }

    private void goQuiz() {
        //        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.pager);
        //        viewPager.setCurrentItem(0);
    }

}
