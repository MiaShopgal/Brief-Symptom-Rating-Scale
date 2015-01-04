package tw.org.tsos.bsrs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_records, container, false);
        MyDatabase myDatabase = new MyDatabase(getActivity());
        List<Record> recordList = myDatabase.getAllRecord();
        ListView listView = (ListView) view.findViewById(R.id.record_list);
        RecordAdapter recordAdapter = new RecordAdapter(getActivity(), 0, recordList);
        listView.setAdapter(recordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goQuiz();
            }
        });
        View hint = view.findViewById(R.id.record_hint);
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goQuiz();
            }
        });
        if (recordList.size() == 0) {
            listView.setVisibility(View.GONE);
            hint.setVisibility(View.VISIBLE);
        } else {
            hint.setVisibility(View.GONE);
        }
        return view;
    }

    private void goQuiz() {
        //        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.pager);
        //        viewPager.setCurrentItem(0);
    }

}
