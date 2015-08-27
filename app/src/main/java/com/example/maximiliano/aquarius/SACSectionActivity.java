package com.example.maximiliano.aquarius;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.maximiliano.aquarius.data.Utility;

public class SACSectionActivity extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sacsection);

        actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_satelite);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Utility.ACTIONBAR_BACKGROUND)));
    }
}
