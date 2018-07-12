package fcms.crptrls.i9930.croptrailsfcms.Login;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpApiInterface;
import fcms.crptrls.i9930.croptrailsfcms.Landing.LandingActivity;
import fcms.crptrls.i9930.croptrailsfcms.Login.model.Post;
import fcms.crptrls.i9930.croptrailsfcms.SharedPref.SharedPreferencesMethod;
import fcms.crptrls.i9930.croptrailsfcms.R;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements android.location.LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    TextView mapid;
    Context context;
    ImageView cloudicon;
    private GoogleApiClient mGoogleApiClient;
    LocationManager locationManager;
    private String mprovider = "";
    Location location, mLastLocation;
    Boolean locationpermission = false;
    FusedLocationProviderClient mFusedLocationClient;
    TextInputEditText ti_email;
    TextInputEditText ti_password;
    String gps_cordinates="";
    ProgressBar progressBar;
    private Boolean exit = false;



    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_LONG).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.new_theme));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        mapid = (TextView) findViewById(R.id.map_id);
        cloudicon = (ImageView) findViewById(R.id.cloud_icon);
        ti_email=(TextInputEditText)findViewById(R.id.et_email);
        ti_password=(TextInputEditText)findViewById(R.id.et_password);
        progressBar=(ProgressBar)findViewById(R.id.progressBar_cyclic);


        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        cloudicon.startAnimation(animation);

        final Dialog dialog = new Dialog(this, R.style.PauseDialog);






      /*  if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }*/
        Criteria criteria = new Criteria();
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

        mprovider = locationManager.getBestProvider(criteria, false);
        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            location = locationManager.getLastKnownLocation(mprovider);
            //locationManager.requestLocationUpdates(1000, 1, criteria, this, null);
            locationManager.requestLocationUpdates(mprovider, 2000, 1, this);
        }
        if (locationpermission == checkLocationPermission()) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        /*locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
*/
        mapid.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                        .addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    gps_cordinates=location.getLatitude()+"_"+location.getLongitude();
                                  //  Toast.makeText(context, location.getLatitude()+"  "+location.getLongitude(), Toast.LENGTH_SHORT).show();
                                    // Logic to handle location object
                                }
                            }
                        });

                if(ti_email.getText().toString().trim().matches("")){
                            ti_email.setError("Username can't be null");
                }else if(ti_password.getText().toString().trim().matches("")){
                    ti_password.setError("Password can't be null");

                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    String public_ip=getPublicIPAddress(context);
                    AsyncTaskRunner asyncTaskRunner=new AsyncTaskRunner();
                    asyncTaskRunner.execute(gps_cordinates,public_ip);
                }
// Setting the title and layout for the dialog
                /*dialog.setTitle("Hello");
                dialog.setContentView(R.layout.pause_menu);
                dialog.show();*/
              /*  Intent intent=new Intent(context,LandingActivity.class);
                ActivityOptions options =
                        null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    context.startActivity(intent, options.toBundle());
                }else{
                    startActivity(intent);
                }*/
            }
        });
      /*  ApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        ModelSendData modelSendData=new ModelSendData();
        modelSendData.setInspector_num("22");
        modelSendData.setToken3("4");
        modelSendData.setFarm_num("1");
        Call<ModelRecieveData> modelRecieveDataCall=apiService.getsoildata(modelSendData);
        modelRecieveDataCall.enqueue(new Callback<ModelRecieveData>() {
            @Override
            public void onResponse(Call<ModelRecieveData> call, Response<ModelRecieveData> response) {
                int statuscode=response.code();


                // response.message();
*//*
                Log.e("TAG",response.body().toString()+"        "+response.message()+"      "+response.code());
                ModelRecieveData modelRecieveData=response.body();

//                Toast.makeText(LoginActivity.this, modelRecieveData.getResult().toString(), Toast.LENGTH_LONG).show();
                List<Result> result=modelRecieveData.getResult();

                String[] activity=new String[result.size()];

                for(int i=0;i<result.size();i++){
                    activity[i]=result.get(i).getActivity();
                    Log.e("TAG",activity[i]);
                }
*//*


            }

            @Override
            public void onFailure(Call<ModelRecieveData> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Some Error", Toast.LENGTH_SHORT).show();
                Log.e("TAG",t.getMessage());
            }
        });*/

    }






    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Crop Trails wants to access your location.Please enable it in oder to proceed.")
                .setCancelable(false)

                .setNegativeButton("Enable", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);

                        startActivity(intent);



                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }//end of buildAlertMessageNoGps


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //Toast.makeText(context, "granteed", Toast.LENGTH_SHORT).show();

                } else {
                    //Toast.makeText(context, "not granted", Toast.LENGTH_SHORT).show();
                    checkNewFunction();

                }
                return;
            }

            case 2: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(context, "granteed", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "not granted", Toast.LENGTH_SHORT).show();

                }
                return;
            }
        }

    }


    public void checkNewFunction() {
        if (ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                //Toast.makeText(context, "Temporary Block", Toast.LENGTH_SHORT).show();
                if (locationpermission == checkLocationPermission()) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }

            } else {
                buildAlertMessageNoGps();

            }
        } else {
            //Toast.makeText(context, "Allowed", Toast.LENGTH_SHORT).show();

            // Permission has already been granted
        }
    }



/*
    @Override
    protected void onStart() {
//        mGoogleApiClient.connect();
        super.onStart();
    }


    protected void onStop() {
       // mGoogleApiClient.disconnect();
        super.onStop();
    }*/



    public boolean checkLocationPermission() {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }


    public static String getPublicIPAddress(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo info = cm.getActiveNetworkInfo();

        RunnableFuture<String> futureRun = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                if ((info != null && info.isAvailable()) && (info.isConnected())) {
                    StringBuilder response = new StringBuilder();

                    try {
                        HttpURLConnection urlConnection = (HttpURLConnection) (
                                new URL("http://checkip.amazonaws.com/").openConnection());
                        urlConnection.setRequestProperty("User-Agent", "Android-device");
                        //urlConnection.setRequestProperty("Connection", "close");
                        urlConnection.setReadTimeout(15000);
                        urlConnection.setConnectTimeout(15000);
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setRequestProperty("Content-type", "application/json");
                        urlConnection.connect();

                        int responseCode = urlConnection.getResponseCode();

                        if (responseCode == HttpURLConnection.HTTP_OK) {

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                            String line;
                            while ((line = reader.readLine()) != null) {
                                response.append(line);
                            }

                        }
                        urlConnection.disconnect();
                        return response.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //Log.w(TAG, "No network available INTERNET OFF!");
                    return null;
                }
                return null;
            }
        });

        new Thread(futureRun).start();

        try {
            return futureRun.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(context, " "+location.getLatitude()+"    "+location.getLongitude(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        enableGPS();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
       /* try {

            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                Toast.makeText(context, mLastLocation.getLatitude()+"    "+mLastLocation.getLongitude(), Toast.LENGTH_SHORT).show();


                //String xyz=mLastLocation.getLatitude()+"_"+mLastLocation.getLongitude();

                //gps.setText(xyz);
            }
        } catch (SecurityException e) {
           // Toast.makeText(context, "security exception", Toast.LENGTH_SHORT).show();

        }*/
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void enableGPS() {
      //  Toast.makeText(context, "enableGPS called", Toast.LENGTH_SHORT).show();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(LoginActivity.this).build();
        mGoogleApiClient.connect();
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000);
        locationRequest.setFastestInterval(2 * 1000);

        LocationSettingsRequest.Builder builder = new
                LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        // **************************
        builder.setAlwaysShow(true); // this is the key ingredient
        // **************************

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi
                        .checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result
                        .getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:



                       // Toast.makeText(context, "Gps Enabled", Toast.LENGTH_SHORT).show();

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:


                        try {
                           // Toast.makeText(LoginActivity.this, "Requesting for gps", Toast.LENGTH_SHORT).show();


                            status.startResolutionForResult(LoginActivity.this, 1000);
                        } catch (IntentSender.SendIntentException e) {

                        }
                        break;
                    case
                            LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                        break;
                }
            }
        });


    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
              //  Toast.makeText(context, "ResultOk", Toast.LENGTH_SHORT).show();


            }
            if (resultCode == Activity.RESULT_CANCELED) {

               // Toast.makeText(context, "resultCanceled", Toast.LENGTH_LONG).show();
              //  enableGPS();
            }
        }
    }


    private class AsyncTaskRunner extends AsyncTask<String, Void, String> {
        public AsyncTaskRunner() {
            super();
        }

        String user_name=ti_email.getText().toString().trim();
        String password=ti_password.getText().toString().trim();
        @Override
        protected String doInBackground(String... strings) {
            String gps=strings[0];
            String public_ip=strings[1];
            ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
            apiService.savePost(user_name,password,gps,public_ip).enqueue(new Callback<Post>() {

                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        String z = response.body().toString();
                        Post post = response.body();
                        progressBar.setVisibility(View.INVISIBLE);
                        if(post.getMsg().equals("Login Successfull")) {
                            Intent intent=new Intent(context,LandingActivity.class);
                            SharedPreferencesMethod.setBoolean(context, SharedPreferencesMethod.SVLOGIN, true);
                            ActivityOptions options =
                                    null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                context.startActivity(intent, options.toBundle());
                                finish();
                            }else{
                                startActivity(intent);
                                finish();
                            }
                        }else{
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(context, post.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                        // Toast.makeText(LoginActivity.this, z, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(context, "Facing problem", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            return null;
        }
    }
}