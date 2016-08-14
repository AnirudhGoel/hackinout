package com.example.sachin.docdoc.Location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sachin on 14/8/16.
 */
public class LocationTracker extends BroadcastReceiver {

    private PowerManager.WakeLock wakeLock;

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pow = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = pow.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wakeLock.acquire();

        Location currentLocation = LocationProvider.getInstance().getCurrentLocation();
       // Log.e("location",""+currentLocation.getLatitude());

        // Send new location to backend. // this will be different for you

        Log.e("locationfinally",""+currentLocation.getLatitude());
        Toast.makeText(context,currentLocation.getLatitude()+" "+currentLocation.getLatitude(),Toast.LENGTH_LONG).show();
        wakeLock.release();
       //release wavelock
    }
}