package tw.org.tsos.bsrs.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import tw.org.tsos.bsrs.MyVolley;
import tw.org.tsos.bsrs.R;
import tw.org.tsos.bsrs.util.db.bean.Ebook;

public class EbookAdapter extends ArrayAdapter<Ebook> {

    public EbookAdapter(Context context, int resource, List<Ebook> ebookList) {
        super(context, resource, ebookList);
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ebook_item, null);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag(R.id.id_holder);
        if (viewHolder == null) {
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(R.id.id_holder, viewHolder);
        }
        Ebook ebook = getItem(position);
        viewHolder.name.setText(ebook.getName());
        //FIXME need fix this url
        viewHolder.ebookCover.setImageUrl("http://tspc.tw/tspc/uploadfiles/Image/02.jpg", MyVolley.getImageLoader());
        return convertView;
    }

    private class ViewHolder {

        private TextView name;
        private NetworkImageView ebookCover;

        private ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.name);
            ebookCover = (NetworkImageView) view.findViewById(R.id.ebook_cover);
            view.setTag(this);
        }
    }
}
