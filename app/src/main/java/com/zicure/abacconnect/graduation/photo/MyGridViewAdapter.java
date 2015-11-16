package com.zicure.abacconnect.graduation.photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.graduation.photo.model.GraduatePhoto;

/**
 * Created by DUMP129 on 9/24/2015.
 */
public class MyGridViewAdapter extends BaseAdapter {
    private String TAG = "MyGridViewAdapter";
    private Context mContext;
    private GraduatePhoto graduatePhoto;
    private MyGridViewAdapterOnClickListener myGridViewAdapterOnClickListener = null;

    // Constructor
    public MyGridViewAdapter(GraduatePhoto graduatePhoto, Context context) {
        this.graduatePhoto = graduatePhoto;
        this.mContext = context;
    }

    // Setter
    public void setMyGridViewListener(MyGridViewAdapterOnClickListener myGridViewAdapterOnClickListener) {
        this.myGridViewAdapterOnClickListener = myGridViewAdapterOnClickListener;
    }

    @Override
    public int getCount() {
        return graduatePhoto.getGraduatePhotoData().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder = new ViewHolder();
        final Bitmap bmp;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) (ApplicationContext.getInstance().getContext()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.graduate_photo_grid_items, null);
            holder.tvGraduatePhoto = (TextView) rowView.findViewById(R.id.tvGraduatePhoto);
            holder.imgViewGraduatePhoto = (ImageView) rowView.findViewById(R.id.imgViewGraduatePhoto);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.tvGraduatePhoto.setText(graduatePhoto.getGraduatePhotoData().get(position).getGradPhotoName());
        bmp = BitmapFactory.decodeResource(ApplicationContext.getInstance().getContext().getResources(), graduatePhoto.getGraduatePhotoData().get(position).getImageResource());
        holder.imgViewGraduatePhoto.setImageBitmap(bmp);

        // SetOnClickListener
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myGridViewAdapterOnClickListener != null) {
                    myGridViewAdapterOnClickListener.rowViewOnClick(v, position);
                }
            }
        });

        return rowView;
    }

    public static class ViewHolder {
        ImageView imgViewGraduatePhoto = null;
        TextView tvGraduatePhoto = null;
    }

    public interface MyGridViewAdapterOnClickListener {
        public void rowViewOnClick(View v, int position);
    }
}