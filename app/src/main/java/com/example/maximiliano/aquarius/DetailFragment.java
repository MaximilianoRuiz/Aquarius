package com.example.maximiliano.aquarius;


import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;


import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment {

    TextView tvTitle, tvDescription;
    int position;

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        position = this.getArguments().getInt(GalleryActivityFragment.ITEM_POSITION);
        String[] titles = getResources().getStringArray(R.array.titles);
        String[] descriptions = getResources().getStringArray(R.array.descriptions);

        tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
        tvDescription = (TextView) rootView.findViewById(R.id.tvDescription);

        tvTitle.setText(titles[position]);
        tvDescription.setText(descriptions[position]);

        return rootView;
    }
}
