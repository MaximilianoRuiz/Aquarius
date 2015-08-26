package com.example.maximiliano.aquarius;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    ActionBar actionBar;
    int seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFE8E8E8")));

        seconds = Utility.isSplashShow(this) ? 3 : 0;

        Thread splash = new Thread() {
            public void run() {
                try {
                    sleep(seconds*1000);
                    Intent intent = new Intent(getBaseContext(),HomeActivity.class);
                    startActivity(intent);
                    Utility.stopSplashShow(getBaseContext());
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        splash.start();
    }
}
