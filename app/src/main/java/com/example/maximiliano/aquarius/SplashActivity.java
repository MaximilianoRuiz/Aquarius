package com.example.maximiliano.aquarius;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.maximiliano.aquarius.data.Utility;

public class SplashActivity extends AppCompatActivity {

    private final int THREE_SECONDS = 3;
    private final int ZERO_SECONDS = 0;
    private final int MILI_SECONDS = 1000;

    ActionBar actionBar;
    int seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_satelite);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Utility.ACTIONBAR_BACKGROUND)));

        seconds = Utility.isSplashShow(this) ? THREE_SECONDS : ZERO_SECONDS;

        Thread splash = new Thread() {
            public void run() {
                try {
                    sleep(seconds * MILI_SECONDS);
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
