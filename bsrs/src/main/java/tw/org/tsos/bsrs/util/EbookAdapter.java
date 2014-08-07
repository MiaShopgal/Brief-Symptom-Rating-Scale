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

    @SuppressWarnings("UnusedDeclaration")
    private static final String TAG = EbookAdapter.class.getSimpleName();

    public EbookAdapter(Context context, int resource, List<Ebook> ebookList) {
        super(context, resource, ebookList);
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.ebook_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(R.id.id_holder, viewHolder);

            /*DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            int screenWidthInPix = displayMetrics.widthPixels;
            int screenHeightInPix = displayMetrics.heightPixels;
            ImageRequest request = new ImageRequest(ebook.getCover(), new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    Log.d(TAG, "response");
                    //                Drawable drawable = new BitmapDrawable(getContext().getResources(), response);
                    viewHolder.ebookCover.setImageBitmap(response);
                }
            }, screenWidthInPix / 2, screenHeightInPix / 2, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "on error response " + VolleyErrorHelper.getMessage(error, ebook.getCover()));
                }
            }
            );
            MyVolley.getRequestQueue().add(request);
®
            */
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final Ebook ebook = getItem(position);
        viewHolder.name.setText(ebook.getName());
        viewHolder.ebookCover.setImageUrl(ebook.getCover(), MyVolley.getImageLoader());

        return view;
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
