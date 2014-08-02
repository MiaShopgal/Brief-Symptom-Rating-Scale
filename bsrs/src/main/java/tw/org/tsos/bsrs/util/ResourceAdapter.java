package tw.org.tsos.bsrs.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import tw.org.tsos.bsrs.R;
import tw.org.tsos.bsrs.util.db.bean.Resource;

public class ResourceAdapter extends ArrayAdapter<Resource> {

    public ResourceAdapter(Context context, int resource, List<Resource> resources) {
        super(context, resource, resources);
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.resource_item, null);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag(R.id.id_holder);
        if (viewHolder == null) {
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(R.id.id_holder, viewHolder);
        }
        Resource resource = getItem(position);
        viewHolder.name.setText(resource.getName());
        viewHolder.address.setText(resource.getAddress());
        viewHolder.phone.setText(resource.getPhone());
        return convertView;
    }

    private class ViewHolder {

        private TextView name;
        private TextView address;
        private TextView phone;

        private ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.name);
            address = (TextView) view.findViewById(R.id.address);
            phone = (TextView) view.findViewById(R.id.phone);
            view.setTag(this);
        }
    }
}
