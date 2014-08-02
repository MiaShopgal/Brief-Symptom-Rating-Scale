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
import java.util.Date;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Record record = getItem(position);
        Log.d(TAG, "record=" + record);
        String date = sdf.format(new Date(record.getDate()));
        String text = "";
        switch (record.getLevel()) {
            case 1:
                text = getContext().getString(R.string.score_level_1_text);
                break;
            case 2:
                text = getContext().getString(R.string.score_level_2_text);
                break;
            case 3:
                text = getContext().getString(R.string.score_level_3_text);
                break;
            case 4:
                text = getContext().getString(R.string.score_level_4_text);
                break;
        }

        viewHolder.text.setText(text);
        viewHolder.score.setText(String.format(getContext().getString(R.string.score_text), record.getScore()));
        viewHolder.date.setText(date);
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
