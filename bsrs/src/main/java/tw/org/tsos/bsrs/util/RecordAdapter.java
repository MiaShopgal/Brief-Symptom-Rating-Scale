package tw.org.tsos.bsrs.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import tw.org.tsos.bsrs.R;
import tw.org.tsos.bsrs.util.db.bean.Record;

public class RecordAdapter extends ArrayAdapter<Record> {

    private static final String TAG = RecordAdapter.class.getSimpleName();

    public RecordAdapter(Context context, int resource, List<Record> recordList) {
        super(context, resource, recordList);
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.record_item, null);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag(R.id.id_holder);
        if (viewHolder == null) {
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(R.id.id_holder, viewHolder);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        Record record = getItem(position);
        Log.d(TAG, "record=" + record);
        viewHolder.text.setText(record.getText());
        viewHolder.score.setText(String.format(getContext().getString(R.string.score_text), record.getScore()));
        viewHolder.date.setText(sdf.format(record.getDate()));
        return convertView;
    }

    private class ViewHolder {

        private TextView date;
        private TextView score;
        private TextView text;

        private ViewHolder(View view) {
            date = (TextView) view.findViewById(R.id.date);
            score = (TextView) view.findViewById(R.id.score);
            text = (TextView) view.findViewById(R.id.text);
            view.setTag(this);
        }
    }
}
