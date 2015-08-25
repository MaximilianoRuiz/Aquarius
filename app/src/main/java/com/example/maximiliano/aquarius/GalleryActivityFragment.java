package com.example.maximiliano.aquarius;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class GalleryActivityFragment extends Fragment {

    public static final String ITEM_POSITION = "ITEM_POSITION";
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt(ITEM_POSITION, position);

                Fragment fragment = new DetailFragment();
                fragment.setArguments(bundle);


                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.main, fragment);
                trans.commit();
            }
        });

        return rootView;
    }
}
