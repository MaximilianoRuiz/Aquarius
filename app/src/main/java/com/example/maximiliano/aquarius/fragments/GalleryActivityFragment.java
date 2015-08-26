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
import com.example.maximiliano.aquarius.data.DetailVO;
import com.example.maximiliano.aquarius.data.Utility;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class GalleryActivityFragment extends Fragment {

    public static final String DETAILVO = "DETAILVO";
    ListView listView;
    List<DetailVO> detailVOs;

    public GalleryActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        detailVOs = Utility.obteinDetailVOList(getContext());

        if (Utility.isASCOrderPreferenceSelected(getActivity())) {
            Collections.sort(detailVOs, new Comparator<DetailVO>() {
                @Override
                public int compare(DetailVO obj1, DetailVO obj2) {
                    return obj1.getTitle().compareTo(obj2.getTitle());
                }
            });
        } else {
            Collections.sort(detailVOs, new Comparator<DetailVO>() {
                @Override
                public int compare(DetailVO obj1, DetailVO obj2) {
                    return obj2.getPriority().compareTo(obj1.getPriority());
                }
            });
        }

        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(new GalleryAdapter(getActivity(), detailVOs));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int container;

                Bundle bundle = new Bundle();
                bundle.putSerializable(DETAILVO, detailVOs.get(position));

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
