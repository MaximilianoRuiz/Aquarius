package com.example.maximiliano.aquarius;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFE8E8E8")));
    }

    public void openSacSection(View v){
        Intent intent = new Intent(getBaseContext(), SACSectionActivity.class);
        startActivity(intent);
    }

    public void openGallery(View v){
        Intent intent = new Intent(getBaseContext(), GalleryActivity.class);
        startActivity(intent);
    }
}
