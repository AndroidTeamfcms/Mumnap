package fcms.crptrls.i9930.croptrailsfcms.TestFolder;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Calendar;

import fcms.crptrls.i9930.croptrailsfcms.R;
import fcms.crptrls.i9930.croptrailsfcms.ServicesAndBroadCastRecivers.UploadGpsLogToServer;

public class TestLocationcheckActivity extends AppCompatActivity {


    private FusedLocationProviderClient mFusedLocationClient;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_locationcheck);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        context=this;
/*
        AlarmManager alarmManager = (AlarmManager)getSystemService(context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, UploadGpsLogToServer.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmIntent.setData((Uri.parse("custom://"+System.currentTimeMillis())));
        //alarmManager.cancel(pendingIntent);
        Calendar alarmStartTime = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        Calendar current_time=Calendar.getInstance();
        alarmStartTime.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
        alarmStartTime.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
        alarmStartTime.set(Calendar.SECOND,now.get(Calendar.SECOND)+10);
        if (current_time.after(alarmStartTime)) {
            Log.d("Hey", "Added a day");
            alarmStartTime.add(Calendar.DATE, 1);
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmStartTime.getTimeInMillis(), 15*60*1000 ,pendingIntent);//AlarmManager.INTERVAL_DAY
        Log.d("Alarm", "Alarms set for everyday 8 am.");*/
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.e("Test_Location",location.getLatitude()+" "+location.getLongitude());
                            //Toast.makeText(TestLocationcheckActivity.this, location.getLatitude()+"   "+location.getLongitude(), Toast.LENGTH_SHORT).show();
                            // Logic to handle location object
                        }
                    }
                });
    }
}
