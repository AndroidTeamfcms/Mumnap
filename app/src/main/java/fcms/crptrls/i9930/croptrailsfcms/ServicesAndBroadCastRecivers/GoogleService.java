package fcms.crptrls.i9930.croptrailsfcms.ServicesAndBroadCastRecivers;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fcms.crptrls.i9930.croptrailsfcms.DatsbaseHandler.DatabaseHandler;
import fcms.crptrls.i9930.croptrailsfcms.DatsbaseHandler.SaveGpsGetterSetter;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpApiInterface;
import fcms.crptrls.i9930.croptrailsfcms.Login.model.Post;
import fcms.crptrls.i9930.croptrailsfcms.SharedPref.SharedPreferencesMethod;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by deepshikha on 24/11/16.
 */

public class GoogleService extends Service implements LocationListener{

    boolean isGPSEnable = false;
    boolean isNetworkEnable = false;
    double latitude,longitude;
    LocationManager locationManager;
    Location location;
    private Handler mHandler = new Handler();
    private Timer mTimer = null;
    long notify_interval =1000;
    public static String str_receiver = "servicetutorial.service.receiver";
    Intent intent;
    public final String TAG="MYTAG";




    public GoogleService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mTimer = new Timer();
        mTimer.schedule(new TimerTaskToGetLocation(),1000,notify_interval);
        intent = new Intent(str_receiver);
//        fn_getlocation();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @SuppressLint("MissingPermission")
    private void fn_getlocation(){
        locationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnable && !isNetworkEnable){

        }else {

            if (isNetworkEnable){
                location = null;
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,10*100,100,this);
                if (locationManager!=null){
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location!=null){

                        Log.e(TAG,"locationFromnetwork"+location.getLatitude()+"");
                        Log.e(TAG,location.getLongitude()+"");

                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        fn_update(location);
                    }
                }

            }


            if (isGPSEnable){
                location = null;
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10*100,100,this);
                if (locationManager!=null){
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location!=null){
                        Log.e(TAG,"locationfromgps"+location.getLatitude()+"");
                        Log.e(TAG,location.getLongitude()+"");
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();

                       // fn_update(location);
                    }
                }
            }


        }

    }

    private class TimerTaskToGetLocation extends TimerTask{
        @Override
        public void run() {

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    fn_getlocation();
                }
            });

        }
    }

    private void fn_update(Location location){

        Log.e(TAG,"Coming in fnupdate");
        intent.putExtra("latutide",location.getLatitude()+"");
        intent.putExtra("longitude",location.getLongitude()+"");
        sendBroadcast(intent);

        DatabaseHandler dbget = new DatabaseHandler(getApplicationContext());
        List<SaveGpsGetterSetter> contacts = dbget.getallgpsCordinates();
       /* Calendar cfirst = Calendar.getInstance();
        SimpleDateFormat dffirst = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String formattedDatefirst = dffirst.format(cfirst.getTime());
        DatabaseHandler dbsavefirst = new DatabaseHandler(this);
        dbsavefirst.addGpsCordinates(new SaveGpsGetterSetter(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()), SharedPreferencesMethod.getString(getApplicationContext(),SharedPreferencesMethod.SVUSERID),formattedDatefirst));*/
       // contacts.get(contacts.size()-1);

        if(contacts.size()>0) {
            String str_lat = contacts.get(contacts.size() - 1).getLati_cord();
            String str_long = contacts.get(contacts.size() - 1).getLongi_cord();
       /* String log = "Id: "+contacts.get(contacts.size()-1).getId()+"    Latitude"+contacts.get(contacts.size()-1).getLati_cord()+" ,Longitude: " + contacts.get(contacts.size()-1).getLongi_cord()+"   Date"+contacts.get(contacts.size()-1).getDoa();
        Log.e("GpsCordinates in db: ", log);
*/


            if ((!str_lat.equals(String.valueOf(location.getLatitude())) || !str_long.equals(String.valueOf(location.getLongitude())))) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(c.getTime());

                DatabaseHandler dbsave = new DatabaseHandler(this);
                dbsave.addGpsCordinates(new SaveGpsGetterSetter(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()), SharedPreferencesMethod.getString(getApplicationContext(),SharedPreferencesMethod.SVUSERID),formattedDate));
            }else{
                Log.e(TAG,"same Gps Coordinates");
            }
        }else{
            Calendar cfirst = Calendar.getInstance();
            SimpleDateFormat dffirst = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDatefirst = dffirst.format(cfirst.getTime());
            DatabaseHandler dbsavefirst = new DatabaseHandler(this);
            dbsavefirst.addGpsCordinates(new SaveGpsGetterSetter(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()), SharedPreferencesMethod.getString(getApplicationContext(),SharedPreferencesMethod.SVUSERID),formattedDatefirst));
        }
        /* ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
        apiService.savePost(SharedPreferencesMethod.getString(this,SharedPreferencesMethod.USERNAME),
                SharedPreferencesMethod.getString(this,SharedPreferencesMethod.PASSWORD),
                location.getLatitude()+"_"+location.getLongitude(),"").enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            }
        });*/
    }


}

