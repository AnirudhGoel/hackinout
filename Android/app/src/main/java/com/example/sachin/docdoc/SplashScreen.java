package com.example.sachin.docdoc;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.location.Location;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.WindowManager;

import com.example.sachin.docdoc.Location.LocationTracker;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        startLocationTracker();
        //In manifest hide the action bar from the main activity so that splash screen appears properly
        final int SPLASH_TIME_OUT=3000;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Log.e("flow","in run");

                Intent intent = new Intent();


                    // set the new task and clear flags
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setClass(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                Log.e("flow","after login activity");
                Fade fade= null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    fade = new Fade();
                    fade.setDuration(200);
                }



                finish();
            }
        },SPLASH_TIME_OUT);
    }
    private void startLocationTracker() {
        // Configure the LocationTracker's broadcast receiver to run every 5 minutes.
        Intent intent = new Intent(this, LocationTracker.class);
        Log.e("flow","in manifest");
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            Log.e("inout","working");
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),
                    com.example.sachin.docdoc.Location.LocationProvider.THIRTY_MINUTES, pendingIntent);
        com.example.sachin.docdoc.Location.LocationProvider locationProvider = com.example.sachin.docdoc.Location.LocationProvider.getInstance();
        locationProvider.configureIfNeeded(this);
        Location loc = com.example.sachin.docdoc.Location.LocationProvider.getInstance().getCurrentLocation();



    }
                }




