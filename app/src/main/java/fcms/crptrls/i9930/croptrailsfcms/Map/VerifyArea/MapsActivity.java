package fcms.crptrls.i9930.croptrailsfcms.Map.VerifyArea;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpApiInterface;
import fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmDetailsUpdate.FarmDetailsUpdateActivity;
import fcms.crptrls.i9930.croptrailsfcms.R;
import fcms.crptrls.i9930.croptrailsfcms.StatusMsgModel.StatusMsgModel;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.RetrofitClientInstance;
import fcms.crptrls.i9930.croptrailsfcms.Map.VerifyArea.VerifyAreaModel.VerifySendData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener
    {

    private GoogleMap mMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
        LocationManager locationManager;
        GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    Context context;
    FusedLocationProviderClient mFusedLocationClient;
    Double[] latPoints;
    Double[] longPoints;
    String[] lat,lng;
    int i=0;
    Button submit;
    TextView tvarea;
    Boolean onclick=false;
    Button butt_clear;
        String mprovider;
        Location location;
        Button next_butt;
        ProgressBar progressBar;
        Boolean can_add=true;

        private final Map<String, MarkerOptions> mMarkers = new ConcurrentHashMap<String, MarkerOptions>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.new_theme));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        context = this;

        progressBar=(ProgressBar)findViewById(R.id.progressBar_cyclic);
        progressBar.setVisibility(View.VISIBLE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        mprovider = locationManager.getBestProvider(criteria, false);
        //gpslocation=(TextView)findViewById(R.id.gps_location);
        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 2000, 1, this);

        }



        tvarea=(TextView)findViewById(R.id.area_tv);
        submit=(Button)findViewById(R.id.submit_butt);
        butt_clear=(Button)findViewById(R.id.clear_butt);
        next_butt=(Button)findViewById(R.id.next_butt);

        next_butt.setEnabled(false);


        latPoints=new Double[14];
        longPoints=new Double[14];

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>2){
                    //onclick=true;
                    submit_area();
                    next_butt.setEnabled(true);
                    can_add=false;
                    // submit.setEnabled(false);
                }else{
                    Toast.makeText(context, "Please submit atleast 3 points", Toast.LENGTH_SHORT).show();
                }
            }
        });

        next_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                AsyncTaskRunner asyncTaskRunner=new AsyncTaskRunner();
                asyncTaskRunner.execute();
            }
        });
        butt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>0) {
                    tvarea.setText("0");
                    remove("marker" + (i - 1));
                    next_butt.setEnabled(false);
                    can_add=true;
                    i--;
                }
            }
        });
       /* if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }*/
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

        @Override
        public void onPause() {
            super.onPause();

            //stop location updates when Activity is no longer active
            if (mFusedLocationClient != null) {
                mFusedLocationClient.removeLocationUpdates(mLocationCallback);
            }
        }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(120000); // two minute interval
        mLocationRequest.setFastestInterval(120000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);




        





        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                mMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            mMap.setMyLocationEnabled(true);
        }




                       /* new LatLng(-34.364, 147.891),
                        new LatLng(-33.501, 150.217),
                        new LatLng(-32.306, 149.248),
                        new LatLng(-32.491, 147.309)))*/

       /* mMap.setOnPolylineClickListener(this);
        mMap.setOnPolygonClickListener(this);*/



           mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

               @Override
               public void onMapClick(LatLng point) {
                   // TODO Auto-generated method stub


                   if(!onclick) {
                      /* MarkerOptions marker = new MarkerOptions().position(
                               new LatLng(point.latitude, point.longitude)).title("New Marker");

                       LatLng latLng=new LatLng(point.latitude,point.longitude);
                       add("marker"+i,latLng);
                       mMap.addMarker(marker);
                       Log.e("TOUCH Points", point.latitude + "    " + point.longitude);

                       latPoints[i] = point.latitude;
                       longPoints[i] = point.longitude;
                       i++;*/


                       LatLng latLng=new LatLng(point.latitude,point.longitude);
                       add("marker"+i,latLng);
                       //mMap.addMarker(marker);
                       Log.e("TOUCH Points", point.latitude + "    " + point.longitude);

                     //  submit_area();


                   }
                   //new LatLng(28.7041, 77.1025)));
               }
           });


        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(51.893728, -8.491865);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

        private void submit_area() {
            Polygon UCCpolygon;

            if(i<3){
                Toast.makeText(context, "Please submit atleast 3 points", Toast.LENGTH_SHORT).show();
            }

            else if(i==3) {
                UCCpolygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(latPoints[0], longPoints[0]),
                                new LatLng(latPoints[1], longPoints[1]),
                                new LatLng(latPoints[2], longPoints[2]))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#330000FF")));

                List<LatLng> latLngs = new ArrayList<>();
                latLngs.add(new LatLng(latPoints[0],longPoints[0]));
                latLngs.add(new LatLng(latPoints[1],longPoints[1]));
                latLngs.add(new LatLng(latPoints[2],longPoints[2]));


                Log.i("AREA", "computeArea " + SphericalUtil.computeArea(latLngs));
                tvarea.setText(String.valueOf(String.format("%.2f", SphericalUtil.computeArea(latLngs)*0.000247105)));
            }
            else if(i==4) {
                UCCpolygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(latPoints[0], longPoints[0]),
                                new LatLng(latPoints[1], longPoints[1]),
                                new LatLng(latPoints[2], longPoints[2]),
                                new LatLng(latPoints[3], longPoints[3]))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#330000FF")));

                List<LatLng> latLngs = new ArrayList<>();
                latLngs.add(new LatLng(latPoints[0],longPoints[0]));
                latLngs.add(new LatLng(latPoints[1],longPoints[1]));
                latLngs.add(new LatLng(latPoints[2],longPoints[2]));
                latLngs.add(new LatLng(latPoints[3],longPoints[3]));



                Log.i("AREA", "computeArea " + SphericalUtil.computeArea(latLngs));
                tvarea.setText(String.valueOf(String.format("%.2f", SphericalUtil.computeArea(latLngs)*0.000247105)));
            }
            else if(i==5){
                UCCpolygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(latPoints[0], longPoints[0]),
                                new LatLng(latPoints[1], longPoints[1]),
                                new LatLng(latPoints[2], longPoints[2]),
                                new LatLng(latPoints[3], longPoints[3]),
                                new LatLng(latPoints[4], longPoints[4]))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#330000FF")));

                List<LatLng> latLngs = new ArrayList<>();
                latLngs.add(new LatLng(latPoints[0],longPoints[0]));
                latLngs.add(new LatLng(latPoints[1],longPoints[1]));
                latLngs.add(new LatLng(latPoints[2],longPoints[2]));
                latLngs.add(new LatLng(latPoints[3],longPoints[3]));
                latLngs.add(new LatLng(latPoints[4],longPoints[4]));




                Log.i("AREA", "computeArea " + SphericalUtil.computeArea(latLngs));
                tvarea.setText(String.valueOf(String.format("%.2f", SphericalUtil.computeArea(latLngs)*0.000247105)));


            }
            else if(i==6){
                UCCpolygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(latPoints[0], longPoints[0]),
                                new LatLng(latPoints[1], longPoints[1]),
                                new LatLng(latPoints[2], longPoints[2]),
                                new LatLng(latPoints[3], longPoints[3]),
                                new LatLng(latPoints[4], longPoints[4]),
                                new LatLng(latPoints[5], longPoints[5]))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#330000FF")));
                List<LatLng> latLngs = new ArrayList<>();
                latLngs.add(new LatLng(latPoints[0],longPoints[0]));
                latLngs.add(new LatLng(latPoints[1],longPoints[1]));
                latLngs.add(new LatLng(latPoints[2],longPoints[2]));
                latLngs.add(new LatLng(latPoints[3],longPoints[3]));
                latLngs.add(new LatLng(latPoints[4],longPoints[4]));
                latLngs.add(new LatLng(latPoints[5],longPoints[5]));





                Log.i("AREA", "computeArea " + SphericalUtil.computeArea(latLngs));
                tvarea.setText(String.valueOf(String.format("%.2f", SphericalUtil.computeArea(latLngs)*0.000247105)));
            }
            else if(i==7){
                UCCpolygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(latPoints[0], longPoints[0]),
                                new LatLng(latPoints[1], longPoints[1]),
                                new LatLng(latPoints[2], longPoints[2]),
                                new LatLng(latPoints[3], longPoints[3]),
                                new LatLng(latPoints[4], longPoints[4]),
                                new LatLng(latPoints[5], longPoints[5]),
                                new LatLng(latPoints[6], longPoints[6]))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#330000FF")));
                List<LatLng> latLngs = new ArrayList<>();
                latLngs.add(new LatLng(latPoints[0],longPoints[0]));
                latLngs.add(new LatLng(latPoints[1],longPoints[1]));
                latLngs.add(new LatLng(latPoints[2],longPoints[2]));
                latLngs.add(new LatLng(latPoints[3],longPoints[3]));
                latLngs.add(new LatLng(latPoints[4],longPoints[4]));
                latLngs.add(new LatLng(latPoints[5],longPoints[5]));
                latLngs.add(new LatLng(latPoints[6],longPoints[6]));





                Log.i("AREA", "computeArea " + SphericalUtil.computeArea(latLngs));
                tvarea.setText(String.valueOf(String.format("%.2f", SphericalUtil.computeArea(latLngs)*0.000247105)));
            }
            else if(i==8){
                UCCpolygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(latPoints[0], longPoints[0]),
                                new LatLng(latPoints[1], longPoints[1]),
                                new LatLng(latPoints[2], longPoints[2]),
                                new LatLng(latPoints[3], longPoints[3]),
                                new LatLng(latPoints[4], longPoints[4]),
                                new LatLng(latPoints[5], longPoints[5]),
                                new LatLng(latPoints[6], longPoints[6]),
                                new LatLng(latPoints[7], longPoints[7]))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#330000FF")));
                List<LatLng> latLngs = new ArrayList<>();
                latLngs.add(new LatLng(latPoints[0],longPoints[0]));
                latLngs.add(new LatLng(latPoints[1],longPoints[1]));
                latLngs.add(new LatLng(latPoints[2],longPoints[2]));
                latLngs.add(new LatLng(latPoints[3],longPoints[3]));
                latLngs.add(new LatLng(latPoints[4],longPoints[4]));
                latLngs.add(new LatLng(latPoints[5],longPoints[5]));
                latLngs.add(new LatLng(latPoints[6],longPoints[6]));
                latLngs.add(new LatLng(latPoints[7],longPoints[7]));






                Log.i("AREA", "computeArea " + SphericalUtil.computeArea(latLngs));
                tvarea.setText(String.valueOf(String.format("%.2f", SphericalUtil.computeArea(latLngs)*0.000247105)));
            }
            else if(i==9){
                UCCpolygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(latPoints[0], longPoints[0]),
                                new LatLng(latPoints[1], longPoints[1]),
                                new LatLng(latPoints[2], longPoints[2]),
                                new LatLng(latPoints[3], longPoints[3]),
                                new LatLng(latPoints[4], longPoints[4]),
                                new LatLng(latPoints[5], longPoints[5]),
                                new LatLng(latPoints[6], longPoints[6]),
                                new LatLng(latPoints[7], longPoints[7]),
                                new LatLng(latPoints[8], longPoints[8]))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#330000FF")));
                List<LatLng> latLngs = new ArrayList<>();
                latLngs.add(new LatLng(latPoints[0],longPoints[0]));
                latLngs.add(new LatLng(latPoints[1],longPoints[1]));
                latLngs.add(new LatLng(latPoints[2],longPoints[2]));
                latLngs.add(new LatLng(latPoints[3],longPoints[3]));
                latLngs.add(new LatLng(latPoints[4],longPoints[4]));
                latLngs.add(new LatLng(latPoints[5],longPoints[5]));
                latLngs.add(new LatLng(latPoints[6],longPoints[6]));
                latLngs.add(new LatLng(latPoints[7],longPoints[7]));
                latLngs.add(new LatLng(latPoints[8],longPoints[8]));






                Log.i("AREA", "computeArea " + SphericalUtil.computeArea(latLngs));
                tvarea.setText(String.valueOf(String.format("%.2f", SphericalUtil.computeArea(latLngs)*0.000247105)));
            }
            else if(i==10){
                UCCpolygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(latPoints[0], longPoints[0]),
                                new LatLng(latPoints[1], longPoints[1]),
                                new LatLng(latPoints[2], longPoints[2]),
                                new LatLng(latPoints[3], longPoints[3]),
                                new LatLng(latPoints[4], longPoints[4]),
                                new LatLng(latPoints[5], longPoints[5]),
                                new LatLng(latPoints[6], longPoints[6]),
                                new LatLng(latPoints[7], longPoints[7]),
                                new LatLng(latPoints[8], longPoints[8]),
                                new LatLng(latPoints[9], longPoints[9]))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#330000FF")));

                List<LatLng> latLngs = new ArrayList<>();
                latLngs.add(new LatLng(latPoints[0],longPoints[0]));
                latLngs.add(new LatLng(latPoints[1],longPoints[1]));
                latLngs.add(new LatLng(latPoints[2],longPoints[2]));
                latLngs.add(new LatLng(latPoints[3],longPoints[3]));
                latLngs.add(new LatLng(latPoints[4],longPoints[4]));
                latLngs.add(new LatLng(latPoints[5],longPoints[5]));
                latLngs.add(new LatLng(latPoints[6],longPoints[6]));
                latLngs.add(new LatLng(latPoints[7],longPoints[7]));
                latLngs.add(new LatLng(latPoints[8],longPoints[8]));
                latLngs.add(new LatLng(latPoints[9],longPoints[9]));






                Log.i("AREA", "computeArea " + SphericalUtil.computeArea(latLngs));
                tvarea.setText(String.valueOf(String.format("%.2f", SphericalUtil.computeArea(latLngs)*0.000247105)));
            }
            else if(i==11){
                UCCpolygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(latPoints[0], longPoints[0]),
                                new LatLng(latPoints[1], longPoints[1]),
                                new LatLng(latPoints[2], longPoints[2]),
                                new LatLng(latPoints[3], longPoints[3]),
                                new LatLng(latPoints[4], longPoints[4]),
                                new LatLng(latPoints[5], longPoints[5]),
                                new LatLng(latPoints[6], longPoints[6]),
                                new LatLng(latPoints[7], longPoints[7]),
                                new LatLng(latPoints[8], longPoints[8]),
                                new LatLng(latPoints[9], longPoints[9]),
                                new LatLng(latPoints[10], longPoints[10]))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#330000FF")));

                List<LatLng> latLngs = new ArrayList<>();
                latLngs.add(new LatLng(latPoints[0],longPoints[0]));
                latLngs.add(new LatLng(latPoints[1],longPoints[1]));
                latLngs.add(new LatLng(latPoints[2],longPoints[2]));
                latLngs.add(new LatLng(latPoints[3],longPoints[3]));
                latLngs.add(new LatLng(latPoints[4],longPoints[4]));
                latLngs.add(new LatLng(latPoints[5],longPoints[5]));
                latLngs.add(new LatLng(latPoints[6],longPoints[6]));
                latLngs.add(new LatLng(latPoints[7],longPoints[7]));
                latLngs.add(new LatLng(latPoints[8],longPoints[8]));
                latLngs.add(new LatLng(latPoints[9],longPoints[9]));
                latLngs.add(new LatLng(latPoints[10],longPoints[10]));






                Log.i("AREA", "computeArea " + SphericalUtil.computeArea(latLngs));
                tvarea.setText(String.valueOf(String.format("%.2f", SphericalUtil.computeArea(latLngs)*0.000247105)));

            }
            else if(i==12){
                UCCpolygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(latPoints[0], longPoints[0]),
                                new LatLng(latPoints[1], longPoints[1]),
                                new LatLng(latPoints[2], longPoints[2]),
                                new LatLng(latPoints[3], longPoints[3]),
                                new LatLng(latPoints[4], longPoints[4]),
                                new LatLng(latPoints[5], longPoints[5]),
                                new LatLng(latPoints[6], longPoints[6]),
                                new LatLng(latPoints[7], longPoints[7]),
                                new LatLng(latPoints[8], longPoints[8]),
                                new LatLng(latPoints[9], longPoints[9]),
                                new LatLng(latPoints[10], longPoints[10]),
                                new LatLng(latPoints[11], longPoints[11]))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#330000FF")));

                List<LatLng> latLngs = new ArrayList<>();
                latLngs.add(new LatLng(latPoints[0],longPoints[0]));
                latLngs.add(new LatLng(latPoints[1],longPoints[1]));
                latLngs.add(new LatLng(latPoints[2],longPoints[2]));
                latLngs.add(new LatLng(latPoints[3],longPoints[3]));
                latLngs.add(new LatLng(latPoints[4],longPoints[4]));
                latLngs.add(new LatLng(latPoints[5],longPoints[5]));
                latLngs.add(new LatLng(latPoints[6],longPoints[6]));
                latLngs.add(new LatLng(latPoints[7],longPoints[7]));
                latLngs.add(new LatLng(latPoints[8],longPoints[8]));
                latLngs.add(new LatLng(latPoints[9],longPoints[9]));
                latLngs.add(new LatLng(latPoints[10],longPoints[10]));
                latLngs.add(new LatLng(latPoints[11],longPoints[11]));






                Log.i("AREA", "computeArea " + SphericalUtil.computeArea(latLngs));
                tvarea.setText(String.valueOf(String.format("%.2f", SphericalUtil.computeArea(latLngs)*0.000247105)));
            }
            else if(i==13){
                UCCpolygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(latPoints[0], longPoints[0]),
                                new LatLng(latPoints[1], longPoints[1]),
                                new LatLng(latPoints[2], longPoints[2]),
                                new LatLng(latPoints[3], longPoints[3]),
                                new LatLng(latPoints[4], longPoints[4]),
                                new LatLng(latPoints[5], longPoints[5]),
                                new LatLng(latPoints[6], longPoints[6]),
                                new LatLng(latPoints[7], longPoints[7]),
                                new LatLng(latPoints[8], longPoints[8]),
                                new LatLng(latPoints[9], longPoints[9]),
                                new LatLng(latPoints[10], longPoints[10]),
                                new LatLng(latPoints[11], longPoints[11]),
                                new LatLng(latPoints[12], longPoints[12]))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#330000FF")));

                List<LatLng> latLngs = new ArrayList<>();
                latLngs.add(new LatLng(latPoints[0],longPoints[0]));
                latLngs.add(new LatLng(latPoints[1],longPoints[1]));
                latLngs.add(new LatLng(latPoints[2],longPoints[2]));
                latLngs.add(new LatLng(latPoints[3],longPoints[3]));
                latLngs.add(new LatLng(latPoints[4],longPoints[4]));
                latLngs.add(new LatLng(latPoints[5],longPoints[5]));
                latLngs.add(new LatLng(latPoints[6],longPoints[6]));
                latLngs.add(new LatLng(latPoints[7],longPoints[7]));
                latLngs.add(new LatLng(latPoints[8],longPoints[8]));
                latLngs.add(new LatLng(latPoints[9],longPoints[9]));
                latLngs.add(new LatLng(latPoints[10],longPoints[10]));
                latLngs.add(new LatLng(latPoints[11],longPoints[11]));
                latLngs.add(new LatLng(latPoints[12],longPoints[12]));






                Log.i("AREA", "computeArea " + SphericalUtil.computeArea(latLngs));
                tvarea.setText(String.valueOf(String.format("%.2f", SphericalUtil.computeArea(latLngs)*0.000247105)));
            }
            else if(i==14){
                UCCpolygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(latPoints[0], longPoints[0]),
                                new LatLng(latPoints[1], longPoints[1]),
                                new LatLng(latPoints[2], longPoints[2]),
                                new LatLng(latPoints[3], longPoints[3]),
                                new LatLng(latPoints[4], longPoints[4]),
                                new LatLng(latPoints[5], longPoints[5]),
                                new LatLng(latPoints[6], longPoints[6]),
                                new LatLng(latPoints[7], longPoints[7]),
                                new LatLng(latPoints[8], longPoints[8]),
                                new LatLng(latPoints[9], longPoints[9]),
                                new LatLng(latPoints[10], longPoints[10]),
                                new LatLng(latPoints[11], longPoints[11]),
                                new LatLng(latPoints[12], longPoints[12]),
                                new LatLng(latPoints[13], longPoints[13]))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#330000FF")));

                List<LatLng> latLngs = new ArrayList<>();
                latLngs.add(new LatLng(latPoints[0],longPoints[0]));
                latLngs.add(new LatLng(latPoints[1],longPoints[1]));
                latLngs.add(new LatLng(latPoints[2],longPoints[2]));
                latLngs.add(new LatLng(latPoints[3],longPoints[3]));
                latLngs.add(new LatLng(latPoints[4],longPoints[4]));
                latLngs.add(new LatLng(latPoints[5],longPoints[5]));
                latLngs.add(new LatLng(latPoints[6],longPoints[6]));
                latLngs.add(new LatLng(latPoints[7],longPoints[7]));
                latLngs.add(new LatLng(latPoints[8],longPoints[8]));
                latLngs.add(new LatLng(latPoints[9],longPoints[9]));
                latLngs.add(new LatLng(latPoints[10],longPoints[10]));
                latLngs.add(new LatLng(latPoints[11],longPoints[11]));
                latLngs.add(new LatLng(latPoints[12],longPoints[12]));
                latLngs.add(new LatLng(latPoints[13],longPoints[13]));

                Log.i("AREA", "computeArea " + SphericalUtil.computeArea(latLngs));
                tvarea.setText(String.valueOf(String.format("%.2f", SphericalUtil.computeArea(latLngs)*0.000247105)));
            }


            else{
                Toast.makeText(context, "Point Limit exeeds", Toast.LENGTH_SHORT).show();
            }
           /* Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(new LatLng(point.latitude, point.longitude)));*/
        }


        LocationCallback mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                List<Location> locationList = locationResult.getLocations();
                if (locationList.size() > 0) {
                    //The last location in the list is the newest
                    Location location = locationList.get(locationList.size() - 1);
                    Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                    mLastLocation = location;
                    if (mCurrLocationMarker != null) {
                        mCurrLocationMarker.remove();
                    }

                    //Place current location marker
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                   /* MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title("Current Position");
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                    mCurrLocationMarker = mMap.addMarker(markerOptions);*/

                    //move map camera
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        };

        public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
        private void checkLocationPermission() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    new AlertDialog.Builder(this)
                            .setTitle("Location Permission Needed")
                            .setMessage("This app needs the Location permission, please accept to use location functionality")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Prompt the user once explanation has been shown
                                    ActivityCompat.requestPermissions(MapsActivity.this,
                                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                            MY_PERMISSIONS_REQUEST_LOCATION );
                                }
                            })
                            .create()
                            .show();


                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION );
                }
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode,
                                               String permissions[], int[] grantResults) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_LOCATION: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        // permission was granted, yay! Do the
                        // location-related task you need to do.
                        if (ContextCompat.checkSelfPermission(this,
                                Manifest.permission.ACCESS_FINE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {

                            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                            mMap.setMyLocationEnabled(true);
                        }

                    } else {

                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                    }
                    return;
                }

                // other 'case' lines to check for other
                // permissions this app might request
            }
        }


        @Override
        public void onConnected(@Nullable Bundle bundle) {

        }

        @Override
        public void onConnectionSuspended(int i) {

        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }

        private static final double EARTH_RADIUS = 6371000;// meters

        public static double calculateAreaOfGPSPolygonOnEarthInSquareMeters(final List<Location> locations) {
            return calculateAreaOfGPSPolygonOnSphereInSquareMeters(locations, EARTH_RADIUS);
        }

        private static double calculateAreaOfGPSPolygonOnSphereInSquareMeters(final List<Location> locations, final double radius) {
            if (locations.size() < 3) {
                return 0;
            }

            final double diameter = radius * 2;
            final double circumference = diameter * Math.PI;
            final List<Double> listY = new ArrayList<Double>();
            final List<Double> listX = new ArrayList<Double>();
            final List<Double> listArea = new ArrayList<Double>();
            // calculate segment x and y in degrees for each point
            final double latitudeRef = locations.get(0).getLatitude();
            final double longitudeRef = locations.get(0).getLongitude();
            for (int i = 1; i < locations.size(); i++) {
                final double latitude = locations.get(i).getLatitude();
                final double longitude = locations.get(i).getLongitude();
                listY.add(calculateYSegment(latitudeRef, latitude, circumference));
                Log.d("Area", String.format("Y %s: %s", listY.size() - 1, listY.get(listY.size() - 1)));
                listX.add(calculateXSegment(longitudeRef, longitude, latitude, circumference));
                Log.d("Area", String.format("X %s: %s", listX.size() - 1, listX.get(listX.size() - 1)));
            }

            // calculate areas for each triangle segment
            for (int i = 1; i < listX.size(); i++) {
                final double x1 = listX.get(i - 1);
                final double y1 = listY.get(i - 1);
                final double x2 = listX.get(i);
                final double y2 = listY.get(i);
                listArea.add(calculateAreaInSquareMeters(x1, x2, y1, y2));
                Log.d("Area", String.format("area %s: %s", listArea.size() - 1, listArea.get(listArea.size() - 1)));
            }

            // sum areas of all triangle segments
            double areasSum = 0;
            for (final Double area : listArea) {
                areasSum = areasSum + area;
            }

            // get abolute value of area, it can't be negative
            return Math.abs(areasSum);// Math.sqrt(areasSum * areasSum);
        }

        private static Double calculateAreaInSquareMeters(final double x1, final double x2, final double y1, final double y2) {
            return (y1 * x2 - x1 * y2) / 2;
        }

        private static double calculateYSegment(final double latitudeRef, final double latitude, final double circumference) {
            return (latitude - latitudeRef) * circumference / 360.0;
        }

        private static double calculateXSegment(final double longitudeRef, final double longitude, final double latitude,
                                                final double circumference) {
            return (longitude - longitudeRef) * circumference * Math.cos(Math.toRadians(latitude)) / 360.0;
        }


        private void add(String name, final LatLng ll) {


            if(can_add) {
                if (i < 14) {
                    final MarkerOptions marker = new MarkerOptions().position(ll).title(name);
                    mMarkers.put(name, marker);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMap.addMarker(marker);
                            latPoints[i] = ll.latitude;
                            longPoints[i] = ll.longitude;
                            i++;
                        }
                    });
                } else {
                    Toast.makeText(context, "Cant add More points", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(context, "Please Clear Then Add More", Toast.LENGTH_SHORT).show();
            }
        }


        private void remove(String name) {
            mMarkers.remove(name);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mMap.clear();

                    for (MarkerOptions item : mMarkers.values()) {
                        mMap.addMarker(item);
                    }
                }
            });
        }


        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String s) {
            //gps_status=true;
           // mGoogleApiClient=null;
           // Toast.makeText(context, "Gps Enabled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            //Toast.makeText(context, "Gps Disabled", Toast.LENGTH_SHORT).show();

            enableGPS();
        }

        private void enableGPS() {
            /*if (mGoogleApiClient == null) {*/

                //Toast.makeText(context, "Coming in enable gps", Toast.LENGTH_SHORT).show();
                mGoogleApiClient = new GoogleApiClient.Builder(this)
                        .addApi(LocationServices.API).addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(MapsActivity.this).build();
                mGoogleApiClient.connect();
                LocationRequest locationRequest = LocationRequest.create();
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                locationRequest.setInterval(10 * 1000);
                locationRequest.setFastestInterval(2 * 1000);

                LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                        .addLocationRequest(locationRequest);

                // **************************
                builder.setAlwaysShow(true); // this is the key ingredient
                // **************************

                PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
                        .checkLocationSettings(mGoogleApiClient, builder.build());
                result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                    @Override
                    public void onResult(LocationSettingsResult result) {
                        final Status status = result.getStatus();
                        final LocationSettingsStates state = result
                                .getLocationSettingsStates();
                        switch (status.getStatusCode()) {
                            case LocationSettingsStatusCodes.SUCCESS:
                                // All location settings are satisfied. The client can
                                // initialize location
                               // Toast.makeText(MapsActivity.this, "Gps Enabled", Toast.LENGTH_SHORT).show();

                                // requests here.
                                break;
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                // Location settings are not satisfied. But could be
                                // fixed by showing the user
                                // a dialog.

                                try {
                                    // Show the dialog by calling
                                    // startResolutionForResult(),
                                    // and check the result in onActivityResult().
                                    //Toast.makeText(MapsActivity.this, "Requesting for gps", Toast.LENGTH_SHORT).show();

                                    status.startResolutionForResult(MapsActivity.this, 1000);
                                } catch (IntentSender.SendIntentException e) {
                                   // Toast.makeText(MapsActivity.this, "Requesting for gps in catch", Toast.LENGTH_SHORT).show();

                                    // Ignore the error.
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                // Location settings are not satisfied. However, we have
                                // no way to fix the
                                // settings so we won't show the dialog.
                                //Toast.makeText(MapsActivity.this, "Settings change unavailable", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
            //}
        }


        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == 1000) {
                if (resultCode == Activity.RESULT_OK) {
                    String result = data.getStringExtra("result");
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                    enableGPS();
                }
            }
        }


        private class AsyncTaskRunner extends AsyncTask<String, Void, String> {
            public AsyncTaskRunner() {
                super();
            }

            @Override
            protected String doInBackground(String... params) {

                lat=new String[i];
                lng=new String[i];

               for(int j=0;j<i;j++){
                   lat[j]=String.valueOf(latPoints[j]);
                   lng[j]=String.valueOf(longPoints[j]);
               }

                ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
                VerifySendData verifySendData=new VerifySendData();
                verifySendData.setComp_id("0");
                verifySendData.setFarm_id("2");
                verifySendData.setArea(tvarea.getText().toString().trim());
                verifySendData.setLat(lat);
                verifySendData.setLng(lng);
                Call<StatusMsgModel> statusMsgModelCall=apiService.getMsgStatusForVerifyFarm(verifySendData);
                statusMsgModelCall.enqueue(new Callback<StatusMsgModel>(){

                    @Override
                    public void onResponse(Call<StatusMsgModel> call, Response<StatusMsgModel> response) {

                        StatusMsgModel statusMsgModel=response.body();
                        if(statusMsgModel.getStatus()==1) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Intent intent=new Intent(context,FarmDetailsUpdateActivity.class);
                            ActivityOptions options = null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                context.startActivity(intent, options.toBundle());
                            }else{
                                startActivity(intent);
                            }
                            //Toast.makeText(context, statusMsgModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }else{
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(context, "Response Error Try again latter", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusMsgModel> call, Throwable t) {

                    }

                });
              /*  str_et_amount=et_exp_amount.getText().toString().trim();
                str_et_date=et_exp_date.getText().toString().trim();
                str_et_narration=et_exp_narration.getText().toString().trim();

                register("0","1",str_et_amount,str_et_date,pictureImagePath,str_et_narration,"0");*/
                //public void register(String comp_id, String sv_id, String amount,String exp_date, String img_path,String comment, String category_id){





                return null;
            }


            @Override
            protected void onPreExecute() {
            }

            @Override
            protected void onPostExecute(String s) {


            }
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

        }

    }


