package com.example.maximiliano.aquarius;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

public class GalleryAdapter extends ArrayAdapter{

    Context context;
    String[] list;

    TextView textView;

    public GalleryAdapter(Context context, String[] list) {
        super(context, R.layout.gallery_list_adapter, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.gallery_list_adapter, parent, false);

        textView = (TextView) row.findViewById(R.id.textView);
        textView.setText(list[position]);

        return row;
    }
}
