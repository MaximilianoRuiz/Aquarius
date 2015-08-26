package com.example.maximiliano.aquarius;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GalleryAdapter extends ArrayAdapter{

    Context context;
    String[] list;


    public GalleryAdapter(Context context, String[] list) {
        super(context, R.layout.gallery_list_adapter, list);
        this.context = context;
        this.list = list;
    }

    public static class GalleryViewHolder{
        public final TextView textView;

        public GalleryViewHolder(View v) {
            textView = (TextView) v.findViewById(R.id.textView);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.gallery_list_adapter, parent, false);

        GalleryViewHolder holder = new GalleryViewHolder(row);

        holder.textView.setText(list[position]);

        return row;
    }
}
