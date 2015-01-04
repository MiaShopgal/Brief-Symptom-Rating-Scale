package tw.org.tsos.bsrs.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import tw.org.tsos.bsrs.R;
import tw.org.tsos.bsrs.util.EbookAdapter;
import tw.org.tsos.bsrs.util.db.MyDatabase;
import tw.org.tsos.bsrs.util.db.bean.Ebook;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EbookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EbookFragment extends Fragment {

    public EbookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EbookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EbookFragment newInstance() {
        EbookFragment fragment = new EbookFragment();
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
        View view = inflater.inflate(R.layout.fragment_ebook, container, false);
        MyDatabase myDatabase = new MyDatabase(getActivity());
        List<Ebook> ebookList = myDatabase.getAllEbook();
        ListView listView = (ListView) view.findViewById(R.id.ebook_list);
        EbookAdapter ebookAdapter = new EbookAdapter(getActivity(), 0, ebookList);
        listView.setAdapter(ebookAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ebook ebook = (Ebook) parent.getItemAtPosition(position);
                Uri webPage = Uri.parse(ebook.getLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        return view;
    }

}
