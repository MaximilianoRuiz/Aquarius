package com.example.maximiliano.aquarius.fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maximiliano.aquarius.adapters.GalleryAdapter;
import com.example.maximiliano.aquarius.R;
import com.example.maximiliano.aquarius.data.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class GalleryActivityFragment extends Fragment {

    public static final String ITEM_POSITION = "ITEM_POSITION";
    ListView listView;
    List<String> list;

    public GalleryActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        list = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.titles)));

        if (Utility.isASCOrderPreferenceSelected(getActivity())) {
            Collections.sort(list);
        } else {
            Collections.sort(list, Collections.reverseOrder());
        }

        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(new GalleryAdapter(getActivity(), list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int container;

                Bundle bundle = new Bundle();
                bundle.putInt(ITEM_POSITION, position);

                Fragment fragment = new DetailFragment();
                fragment.setArguments(bundle);


                FragmentTransaction trans = getFragmentManager().beginTransaction();

                if(getActivity().findViewById(R.id.main_detail) != null){
                    container = R.id.main_detail;
                } else {
                    container = R.id.main;
                }

                trans.replace(container, fragment);
                trans.commit();
            }
        });

        return rootView;
    }
}
