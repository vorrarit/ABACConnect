package com.zicure.abacconnect.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.zicure.abacconnect.R;
import com.zicure.abacconnect.register.LoginActivity;


/**
 * Created by DUMP129 on 10/20/2015.
 */
public class Splash extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable;
    long delay_time;
    long time = 3000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!preferences.getBoolean("first_time", false)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("first_time", true);
            editor.commit();
            setContentView(R.layout.splash_screen);
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Splash.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            };
        } else {
            Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        delay_time = time;
        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }
}
