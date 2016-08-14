package com.example.sachin.docdoc.Location;

import android.app.Application;
import android.location.Location;
import android.util.Log;

import com.example.sachin.docdoc.Networking.Main2Activity;

/**
 * Created by sachin on 14/8/16.
 */
public class MyApp extends Application {
    public static String type="doctor";
    @Override
    public void onCreate() {
        super.onCreate();


       // Log.e("inout"," "+loc.getLatitude()+loc.getLongitude());

    }
    public   String getLocation()
    {
        LocationProvider locationProvider = LocationProvider.getInstance();
        locationProvider.configureIfNeeded(this);
        Location loc = LocationProvider.getInstance().getCurrentLocation();
        String s=" "+loc.getLatitude()+loc.getLongitude();
        return s;
    }
}
