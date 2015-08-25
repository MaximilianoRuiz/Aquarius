package com.example.maximiliano.aquarius;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class GalleryActivityFragment extends Fragment {

    ListView listView;
    String[] list;

    public GalleryActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        list = getResources().getStringArray(R.array.titles);

        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(new GalleryAdapter(getActivity(), list));

        return rootView;
    }
}
