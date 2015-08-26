package com.example.maximiliano.aquarius.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.maximiliano.aquarius.R;
import com.example.maximiliano.aquarius.data.DetailVO;

import java.util.List;

public class GalleryAdapter extends ArrayAdapter{

    Context context;
    List<DetailVO> detailVOs;


    public GalleryAdapter(Context context, List<DetailVO> detailVOs) {
        super(context, R.layout.gallery_list_adapter, detailVOs);
        this.context = context;
        this.detailVOs = detailVOs;
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

        holder.textView.setText(detailVOs.get(position).getTitle());

        return row;
    }
}
