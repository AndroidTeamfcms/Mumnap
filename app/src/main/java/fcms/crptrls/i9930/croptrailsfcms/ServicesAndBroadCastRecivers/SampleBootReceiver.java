package fcms.crptrls.i9930.croptrailsfcms.ServicesAndBroadCastRecivers;


import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.concurrent.Executor;


/**
 * Created by hp on 13-04-2018.
 */

public class SampleBootReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("Tag","Coming here in broad");

        Intent serviceIntent = new Intent(context,GoogleService.class);
        context.startService(serviceIntent);


    }

}
