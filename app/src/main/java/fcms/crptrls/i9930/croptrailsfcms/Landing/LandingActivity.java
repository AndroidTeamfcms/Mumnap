package fcms.crptrls.i9930.croptrailsfcms.Landing;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fcms.crptrls.i9930.croptrailsfcms.DataHandler.DataHandler;
import fcms.crptrls.i9930.croptrailsfcms.DatsbaseHandler.DatabaseHandler;
import fcms.crptrls.i9930.croptrailsfcms.DatsbaseHandler.SaveGpsGetterSetter;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpApiInterface;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseActivity;
import fcms.crptrls.i9930.croptrailsfcms.GerminationAndSpacing.GerminationSpacingActivity;
import fcms.crptrls.i9930.croptrailsfcms.GerminationAndSpacing.GerminationSpacingActivity_ViewBinding;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Fragments.FarmFragment;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Fragments.FarmerFragment;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Fragments.ProfileFragment;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Models.FetchFarmData;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Models.FetchFarmSendData;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Units.Model.UnitData;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Units.Model.UnitDataAndStatus;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Units.Model.UnitSendData;
import fcms.crptrls.i9930.croptrailsfcms.Login.LoginActivity;
import fcms.crptrls.i9930.croptrailsfcms.Map.CheckArea.CheckAreaMapActivity;
import fcms.crptrls.i9930.croptrailsfcms.Map.ShowArea.ShowAreaOnMapActivity;
import fcms.crptrls.i9930.croptrailsfcms.R;
import fcms.crptrls.i9930.croptrailsfcms.ServicesAndBroadCastRecivers.SampleBootReceiver;
import fcms.crptrls.i9930.croptrailsfcms.ServicesAndBroadCastRecivers.UploadGpsLogToServer;
import fcms.crptrls.i9930.croptrailsfcms.SharedPref.SharedPreferencesMethod;
import fcms.crptrls.i9930.croptrailsfcms.Map.VerifyArea.MapsActivity;
import fcms.crptrls.i9930.croptrailsfcms.TestFolder.DynamicButtonAdd_demo.TestForAddViewActivity;
import fcms.crptrls.i9930.croptrailsfcms.TestFolder.TestLocationcheckActivity;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        FarmFragment.OnFragmentInteractionListener, FarmerFragment.OnFragmentInteractionListener
        , ProfileFragment.OnFragmentInteractionListener {


    BottomNavigationView navigation;
    Context context;
    Boolean exit = false;
    ProgressBar progressBar;
    RelativeLayout rel_landing_id;

   private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_farm:
                    fragment = new FarmFragment();
                    //temp_value="";
                    //mTextMessage.setText(R.string.title_home);
                    break;
                case R.id.navigation_farmer:
                    fragment = new FarmerFragment();
                    //temp_value="";
                    // mTextMessage.setText(R.string.title_our_products);
                    break;
                case R.id.navigation_profile:
                    fragment = new ProfileFragment();
                    //temp_value="";
                    // mTextMessage.setText(R.string.title_product_literature);
                    break;
                /*case R.id.navigation_blogs:
                    fragment=new BlogFragment();
                    //temp_value="blog";
                    break;
                case R.id.navigation_contacts_us:
                    fragment=new ContactUsFragment();
                    //temp_value="";
                    // mTextMessage.setText(R.string.title_contact_us);
                    break;*/
            }
            return loadFragment(fragment);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.new_theme));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        rel_landing_id=(RelativeLayout)findViewById(R.id.rel_landing_id);
        setSupportActionBar(toolbar);

        FetchUnitAsync fetchUnitAsync=new FetchUnitAsync();
        fetchUnitAsync.execute();










        loadFragment(new FarmFragment());

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        removeShiftMode(navigation);
        navigation.setItemIconTintList(null);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


    }

    @Override
    public void onBackPressed() {
        //exit=false;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();

            if (exit) {
                finish(); // finish activity
            } else {
                Toast.makeText(this, getString(R.string.back_press),
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);

            }


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
/*
        if (id == R.id.nav_camera) {
            Intent intent = new Intent(context, FarmDetailsUpdateActivity.class);
            ActivityOptions options = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                context.startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            Intent intent = new Intent(context, SevenDaysVisitActivity.class);
            ActivityOptions options = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                context.startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }

        }*/  if (id == R.id.nav_slideshow) {

            Intent intent = new Intent(context, CheckAreaMapActivity.class);
            ActivityOptions options = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                context.startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }/* else if (id == R.id.nav_manage) {
            Intent intent = new Intent(context, SowingActivity.class);
            ActivityOptions options = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                context.startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }*/  if (id == R.id.nav_expense) {
            Intent intent = new Intent(context, ExpenseActivity.class);
            ActivityOptions options = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                context.startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }/* else if (id == R.id.nav_share) {
            Intent intent = new Intent(context, TestForAddViewActivity.class);
            ActivityOptions options = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                context.startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
*/

       /* else if (id == R.id.nav_send) {
            Intent intent = new Intent(context, GerminationSpacingActivity
                    .class);
            ActivityOptions options = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                context.startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }*/



        else if (id == R.id.nav_logout) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SharedPreferencesMethod.clear(context);
                            SharedPreferencesMethod.setBoolean(context, SharedPreferencesMethod.SVLOGIN, false);
                            Intent intent = new Intent(context, LoginActivity
                                    .class);
                            ActivityOptions options = null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                context.startActivity(intent, options.toBundle());
                                finish();
                            } else {
                                startActivity(intent);
                                finish();
                            }
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @SuppressLint("RestrictedApi")
    static void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    class FetchUnitAsync extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
            UnitSendData unitSendData=new UnitSendData();
            unitSendData.setComp_id(Integer.parseInt(SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVCOMPID)));
            Call<UnitDataAndStatus> unitDataAndStatusCall=apiService.getUnitData(unitSendData);

            unitDataAndStatusCall.enqueue(new Callback<UnitDataAndStatus>() {
                @Override
                public void onResponse(Call<UnitDataAndStatus> call, Response<UnitDataAndStatus> response) {
                   /* Snackbar snackbar = Snackbar
                            .make(rel_landing_id, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);
                    snackbar.show();*/
                if(response!=null){
                    UnitDataAndStatus unitDataAndStatus=response.body();

                    if(unitDataAndStatus.getStatus()!=0) {
                        List<UnitData> unitDataList = unitDataAndStatus.getData();

                        Set<String> stringSet = new HashSet<String>();

                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < unitDataList.size(); i++) {
                            //SharedPreferencesMethod.setS
                            stringSet.add(unitDataList.get(i).getUnit());
                            //Log.e("Units",unitDataList.get(i).getUnit());
                        }


                        SharedPreferencesMethod.setStringSharedPreferencehistory(context, SharedPreferencesMethod.UNITS, stringSet);
                        Log.e("SHARED", SharedPreferencesMethod.getStringSharedPreferenceshistory(context, SharedPreferencesMethod.UNITS).toString());

                    }
                    }
                }

                @Override
                public void onFailure(Call<UnitDataAndStatus> call, Throwable t) {

                }
            });

            return null;
        }
    }


}
