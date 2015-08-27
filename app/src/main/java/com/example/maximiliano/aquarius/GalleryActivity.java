package com.example.maximiliano.aquarius;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.maximiliano.aquarius.data.Utility;
import com.example.maximiliano.aquarius.fragments.GalleryActivityFragment;

public class GalleryActivity extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_satelite);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Utility.ACTIONBAR_BACKGROUND)));

        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, new GalleryActivityFragment());
        fragmentTransaction.commit();

    }

    public void changeActivityTitle(String title){
        actionBar.setTitle(title);
    }
}
