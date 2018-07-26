package fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmFullDetailPage;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fcms.crptrls.i9930.croptrailsfcms.CustomTextView.CustomTextView;
import fcms.crptrls.i9930.croptrailsfcms.DataHandler.DataHandler;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpApiInterface;
import fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmDetailModel.AddVisitSendData;
import fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmDetailsUpdate.FarmDetailsUpdateActivity;
import fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmFullDetailPage.model.HarvestAndFloweringSendData;
import fcms.crptrls.i9930.croptrailsfcms.GerminationAndSpacing.GerminationSpacingActivity;
import fcms.crptrls.i9930.croptrailsfcms.Landing.LandingActivity;
import fcms.crptrls.i9930.croptrailsfcms.Map.ShowArea.ShowAreaOnMapActivity;
import fcms.crptrls.i9930.croptrailsfcms.R;
import fcms.crptrls.i9930.croptrailsfcms.Report.Model.SendFarmData;
import fcms.crptrls.i9930.croptrailsfcms.Report.Model.ViewFarmData;
import fcms.crptrls.i9930.croptrailsfcms.Report.Model.ViewFarmResult;
import fcms.crptrls.i9930.croptrailsfcms.Report.VisitReportActivity;
import fcms.crptrls.i9930.croptrailsfcms.SharedPref.SharedPreferencesMethod;
import fcms.crptrls.i9930.croptrailsfcms.StatusMsgModel.StatusMsgModel;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.RetrofitClientInstance;
import fcms.crptrls.i9930.croptrailsfcms.Map.VerifyArea.MapsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class FarmDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    CustomTextView add_visit, verify_farm_area_and_details,show_previous_visits,germi_and_spacing_custom_text;
    LinearLayout /*rel_update_farm_details*/show_farm_details, rel_add_visit;
    Context context;
    Animation mAnimation;
    ImageView down_arrow_img,down_arrow_img_show_visits,down_arrow_img_show_verify_farm;
    Toolbar mActionBarToolbar;
    TextView title_name,title_address;
    TextView avg_pp_spacing_tv,avg_rr_spacing_tv,avg_germination_tv,avg_farm_popu_tv;

    RelativeLayout rel_add_visit_full_lay;

    Spinner farmer_inm_material;
    EditText farmer_inm_quantity;
    Spinner farmer_inm_unit;
    Spinner farmer_iwm_material;
    EditText farmer_iwm_quantity;
    Spinner farmer_iwm_unit;
    Spinner farmer_ipm_material;
    EditText farmer_ipm_quantity;
    Spinner farmer_ipm_unit;
    Spinner farmer_cpc_grade;
    Spinner farmer_sml_moisture_level;
    EditText farmer_other_comment;
    Spinner assistant_inm_material;
    EditText assistant_inm_quantity;
    Spinner assistant_inm_unit;
    Spinner assistant_iwm_material;
    EditText assistant_iwm_quantity;
    Spinner assistant_iwm_unit;
    Spinner assistant_ipm_material;
    EditText assistant_ipm_quantity;
    Spinner assistant_ipm_unit;
    ImageView down_arrow_img_germi_and_spacing;

    TextView farmer_inm_done_date;
    TextView farmer_iwm_done_date;
    TextView farmer_ipm_done_date;
    TextView assistant_inm_done_date;
    TextView assistant_iwm_done_date;
    TextView assistant_ipm_done_date;
    TextView actual_harvest_date;
    TextView actual_flowering_date;

    EditText standing_crop_area_et;
    TextView view_farm_area_on_map;

    Spinner assistant_cpc_grade;
    Spinner assistant_sml_moisture_level;
    Spinner assistant_thining_moisture_level;
    EditText assistant_other_comment;
    TextView visit_submit;
    int farmer_inm_material_int;
    int farmer_inm_unit_int;
    int farmer_iwm_material_int;
    int farmer_iwm_unit_int;
    int farmer_ipm_material_int;
    int farmer_ipm_unit_int;
    int farmer_cpc_grade_int;
    int farmer_sml_moisture_level_int;
    int assistant_inm_material_int;
    int assistant_inm_unit_int;
    int assistant_iwm_material_int;
    int assistant_iwm_unit_int;
    int assistant_ipm_material_int;
    int assistant_ipm_unit_int;
    int assistant_cpc_grade_int;
    int assistant_sml_moisture_level_int;
    int assistant_thining_moisture_level_int;
    int[] material_int_arr;
    int[] unit_id_int_arr;
    int[] template_int_arr;
    String[] quantity_str_arr;
    String[] done_date_str_arr;
    String[] comment_str_arr;
    String[] pres_arr;
    TextView farm_lot_no;
    List<List<ViewFarmResult>> resultList;
    List<ViewFarmResult> viewFarmResults;
    LinearLayout dynamic_button;
    LinearLayout.LayoutParams params1;
    CardView card_view_show_map;
    CardView actual_flowering_card_view;
    ProgressBar progressBar_cyclic;
    Calendar myCalendarharvesting = Calendar.getInstance();
    TextView tv_actual_flowering_date;
    CardView actual_harvesting_card_view;
    int  visit_count=0;
    ScrollView scroll_view_farm_details;
    LinearLayout germi_data_hidden_lay;

    String navigateTo = "";
    String germi_navigate_to="";

    TextView add_details_irrigation_source, add_details_previous_crop, add_details_irrigation_type, add_details_soil_type;
    TextView add_details_sowing_date, add_details_exp_harvesting_date, add_details_exp_flowering_date;
    Calendar myCalendarActinm = Calendar.getInstance();
    Calendar myCalendarActiwm = Calendar.getInstance();
    Calendar myCalendarActipm = Calendar.getInstance();
    Calendar myCalendarDescinm = Calendar.getInstance();
    Calendar myCalendarDesciwm = Calendar.getInstance();
    Calendar myCalendarDescipm = Calendar.getInstance();
    Calendar myCalendarAddFarmflowering = Calendar.getInstance();
    Calendar myCalendarAddFarmHarvest = Calendar.getInstance();








    private void updateactinm() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        farmer_inm_done_date.setText(sdf.format(myCalendarActinm.getTime()));
        farmer_inm_done_date.setError(null);

    }
    private void updateactiwm() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        farmer_iwm_done_date.setText(sdf.format(myCalendarActiwm.getTime()));
        farmer_iwm_done_date.setError(null);

    }
    private void updateactipm() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        farmer_ipm_done_date.setText(sdf.format(myCalendarActipm.getTime()));
        farmer_ipm_done_date.setError(null);

    }
    private void updatedescinm() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        assistant_inm_done_date.setText(sdf.format(myCalendarDescinm.getTime()));
        assistant_inm_done_date.setError(null);

    }
    private void updatedesciwm() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        assistant_iwm_done_date.setText(sdf.format(myCalendarDesciwm.getTime()));
        assistant_iwm_done_date.setError(null);

    }
    private void updatedescipm() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        assistant_ipm_done_date.setText(sdf.format(myCalendarDescipm.getTime()));
        assistant_ipm_done_date.setError(null);
    }

    private void update_actual_flowering_date() {



        //actual_flowering_date.setText(sdf.format(myCalendarAddFarmflowering.getTime()));



        String submit_format = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat submit_sdf = new SimpleDateFormat(submit_format, Locale.US);
        String final_submit_actual_flowering_date=submit_sdf.format(myCalendarAddFarmflowering.getTime());

        ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
        HarvestAndFloweringSendData harvestAndFloweringSendData = new HarvestAndFloweringSendData();
        harvestAndFloweringSendData.setFarm_id(SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVFARMID));
        harvestAndFloweringSendData.setActual_flowering_date(final_submit_actual_flowering_date);
        Call<StatusMsgModel> statusMsgModelCall = apiService.getActualFloweringDateStatus(harvestAndFloweringSendData);
        statusMsgModelCall.enqueue(new Callback<StatusMsgModel>() {
            @Override
            public void onResponse(Call<StatusMsgModel> call, Response<StatusMsgModel> response) {
                StatusMsgModel statusMsgModel=response.body();

                if(statusMsgModel.getStatus()!=0){

                    Toast.makeText(context, "Flowering Date Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LandingActivity.class);
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

                   /* String myFormat = "dd/MM/yyyy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    actual_flowering_date.setText(sdf.format(myCalendarAddFarmflowering.getTime()));*/
                }
            }

            @Override
            public void onFailure(Call<StatusMsgModel> call, Throwable t) {

            }
        });




    }

    private void update_actual_harvesting_date() {


        String submit_format = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat submit_sdf = new SimpleDateFormat(submit_format, Locale.US);
        String final_submit_actual_harvesting_date=submit_sdf.format(myCalendarAddFarmHarvest.getTime());

        ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
        HarvestAndFloweringSendData harvestAndFloweringSendData = new HarvestAndFloweringSendData();
        harvestAndFloweringSendData.setFarm_id(SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVFARMID));
        harvestAndFloweringSendData.setActual_harvest_date(final_submit_actual_harvesting_date);
        Call<StatusMsgModel> statusMsgModelCall = apiService.getActualHarvestDateStatus(harvestAndFloweringSendData);
        statusMsgModelCall.enqueue(new Callback<StatusMsgModel>() {
            @Override
            public void onResponse(Call<StatusMsgModel> call, Response<StatusMsgModel> response) {
                StatusMsgModel statusMsgModel=response.body();

                if(statusMsgModel.getStatus()!=0){

                    Toast.makeText(context, "Harvesting Date Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LandingActivity.class);
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

                   /* String myFormat = "dd/MM/yyyy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    actual_flowering_date.setText(sdf.format(myCalendarAddFarmflowering.getTime()));*/
                }
            }

            @Override
            public void onFailure(Call<StatusMsgModel> call, Throwable t) {

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*Intent intent=new Intent(context,LandingActivity.class);
        startActivity(intent);
        finish();*/
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
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
        setContentView(R.layout.activity_farm_details);
        context = this;


         title_name=(TextView)findViewById(R.id.title_name);
         title_address=(TextView)findViewById(R.id.title_address);
        String firstLetter=DataHandler.newInstance().getFetchFarmResult().getName().substring(0,1).toUpperCase();

        String cap = DataHandler.newInstance().getFetchFarmResult().getName().substring(1);

        title_name.setText(DataHandler.newInstance().getFetchFarmResult().getLotNo());
        title_address.setText(firstLetter+cap);

        mActionBarToolbar = (Toolbar) findViewById(R.id.confirm_order_toolbar_layout_for_farm_details);
        setSupportActionBar(mActionBarToolbar);

        //getSupportActionBar().setTitle("My Title");

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        /*update_farm_details=(CustomTextView)findViewById(R.id.update_farmer_details);
        sowing_details=(CustomTextView)findViewById(R.id.sowing_details_card);*/
        add_visit = (CustomTextView) findViewById(R.id.add_visit);
        verify_farm_area_and_details = (CustomTextView) findViewById(R.id.verify_farm_area_and_details);
        germi_and_spacing_custom_text=(CustomTextView)findViewById(R.id.germi_and_spacing_custom_text);
        show_previous_visits=(CustomTextView)findViewById(R.id.show_previous_visits);
        /*rel_update_farm_details=(RelativeLayout)findViewById(R.id.detail_full_layout);*/
        show_farm_details = (LinearLayout) findViewById(R.id.show_farm_details);
        rel_add_visit = (LinearLayout) findViewById(R.id.rel_add_visit_drop);
        down_arrow_img = (ImageView) findViewById(R.id.down_arrow_img);
        down_arrow_img_show_visits=(ImageView)findViewById(R.id.down_arrow_img_show_visits);
        down_arrow_img_show_verify_farm=(ImageView)findViewById(R.id.down_arrow_img_show_verify_farm);
        farm_lot_no = (TextView) findViewById(R.id.farm_lot_no);

        dynamic_button = (LinearLayout) findViewById(R.id.dynamic_button);
        down_arrow_img_germi_and_spacing=(ImageView)findViewById(R.id.down_arrow_img_germi_and_spacing);
        scroll_view_farm_details=(ScrollView)findViewById(R.id.scroll_view_farm_details);

        rel_add_visit_full_lay=(RelativeLayout)findViewById(R.id.rel_add_visit_full_lay);
         avg_pp_spacing_tv=(TextView)findViewById(R.id.avg_pp_spacing_tv);
         avg_rr_spacing_tv=(TextView)findViewById(R.id.avg_rr_spacing_tv);
         avg_germination_tv=(TextView)findViewById(R.id.avg_germination_tv);
         avg_farm_popu_tv=(TextView)findViewById(R.id.avg_farm_popu_tv);


        farmer_inm_material = (Spinner) findViewById(R.id.farmer_inm_material);
        farmer_inm_quantity = (EditText) findViewById(R.id.farmer_inm_quantity);
        farmer_inm_unit = (Spinner) findViewById(R.id.farmer_inm_unit);
        farmer_iwm_material = (Spinner) findViewById(R.id.farmer_iwm_material);
        farmer_iwm_quantity = findViewById(R.id.farmer_iwm_quantity);
        farmer_iwm_unit = (Spinner) findViewById(R.id.farmer_iwm_unit);
        farmer_ipm_material = (Spinner) findViewById(R.id.farmer_ipm_material);
        farmer_ipm_quantity = (EditText) findViewById(R.id.farmer_ipm_quantity);
        farmer_ipm_unit = (Spinner) findViewById(R.id.farmer_ipm_unit);
        farmer_cpc_grade = (Spinner) findViewById(R.id.farmer_cpc_grade);
        farmer_sml_moisture_level = (Spinner) findViewById(R.id.farmer_sml_moisture_level);
        farmer_other_comment = (EditText) findViewById(R.id.farmer_other_comment);
        assistant_inm_material = (Spinner) findViewById(R.id.assistant_inm_material);
        assistant_inm_quantity = (EditText) findViewById(R.id.assistant_inm_quantity);
        assistant_inm_unit = (Spinner) findViewById(R.id.assistant_inm_unit);
        assistant_iwm_material = (Spinner) findViewById(R.id.assistant_iwm_material);
        assistant_iwm_quantity = findViewById(R.id.assistant_iwm_quantity);
        assistant_iwm_unit = (Spinner) findViewById(R.id.assistant_iwm_unit);

        standing_crop_area_et=(EditText)findViewById(R.id.standing_crop_area_et);

        farmer_inm_done_date = (TextView) findViewById(R.id.farmer_inm_done_date);
        farmer_iwm_done_date = (TextView) findViewById(R.id.farmer_iwm_done_date);
        farmer_ipm_done_date = (TextView) findViewById(R.id.farmer_ipm_done_date);
        assistant_inm_done_date = (TextView)findViewById(R.id.assistant_inm_done_date);
        assistant_iwm_done_date = (TextView) findViewById(R.id.assistant_iwm_done_date);
        assistant_ipm_done_date = (TextView) findViewById(R.id.assistant_ipm_done_date);
        actual_harvest_date=(TextView)findViewById(R.id.add_details_actual_harvest_date);
        actual_flowering_date=(TextView)findViewById(R.id.add_details_actual_flowering_date);

        view_farm_area_on_map=(TextView)findViewById(R.id.view_farm_area_on_map);

        assistant_ipm_material = (Spinner) findViewById(R.id.assistant_ipm_material);
        assistant_ipm_quantity = (EditText) findViewById(R.id.assistant_ipm_quantity);
        assistant_ipm_unit = (Spinner) findViewById(R.id.assistant_ipm_unit);
        assistant_cpc_grade = (Spinner) findViewById(R.id.assistant_cpc_grade);
        assistant_sml_moisture_level = (Spinner) findViewById(R.id.assistant_sml_moisture_level);
        assistant_thining_moisture_level = (Spinner) findViewById(R.id.assistant_thining_moisture_level);
        assistant_other_comment = (EditText) findViewById(R.id.assistant_other_comment);
        card_view_show_map = (CardView) findViewById(R.id.card_view_show_map);
        progressBar_cyclic = (ProgressBar) findViewById(R.id.progressBar_cyclic);
        add_details_irrigation_source = (TextView) findViewById(R.id.add_details_irrigationSource);
        add_details_previous_crop = (TextView) findViewById(R.id.add_details_previousCrop);
        add_details_irrigation_type = (TextView) findViewById(R.id.add_details_irrigationType);
        add_details_soil_type = (TextView) findViewById(R.id.add_details_soilType);
        add_details_sowing_date = (TextView) findViewById(R.id.add_details_sowingDate);
        add_details_exp_flowering_date = (TextView) findViewById(R.id.add_details_expFloweringDate);
        add_details_exp_harvesting_date = (TextView) findViewById(R.id.add_details_expHarvestDate);
        actual_flowering_card_view = (CardView) findViewById(R.id.actual_flowering_card_view);
        actual_harvesting_card_view = (CardView) findViewById(R.id.actual_harvesting_card_view);
        germi_data_hidden_lay=(LinearLayout)findViewById(R.id.germi_data_hidden_lay);


        if(DataHandler.newInstance().getFetchFarmResult().getLotNo()!=null) {
            farm_lot_no.setText(DataHandler.newInstance().getFetchFarmResult().getLotNo());
        }

        visit_submit = (TextView) findViewById(R.id.act_pres_submit);


        params1 = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);



        final DatePickerDialog.OnDateSetListener dateactinm = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarActinm.set(Calendar.YEAR, year);
                myCalendarActinm.set(Calendar.MONTH, monthOfYear);
                myCalendarActinm.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateactinm();
            }
        };

        final DatePickerDialog.OnDateSetListener dateactiwm = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarActiwm.set(Calendar.YEAR, year);
                myCalendarActiwm.set(Calendar.MONTH, monthOfYear);
                myCalendarActiwm.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateactiwm();
            }
        };

        final DatePickerDialog.OnDateSetListener dateactipm = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarActipm.set(Calendar.YEAR, year);
                myCalendarActipm.set(Calendar.MONTH, monthOfYear);
                myCalendarActipm.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateactipm();
            }
        };


        final DatePickerDialog.OnDateSetListener datedescinm = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarDescinm.set(Calendar.YEAR, year);
                myCalendarDescinm.set(Calendar.MONTH, monthOfYear);
                myCalendarDescinm.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatedescinm();
            }
        };

        final DatePickerDialog.OnDateSetListener datedesciwm = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarDesciwm.set(Calendar.YEAR, year);
                myCalendarDesciwm.set(Calendar.MONTH, monthOfYear);
                myCalendarDesciwm.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatedesciwm();
            }
        };

        final DatePickerDialog.OnDateSetListener datedescipm = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarDescipm.set(Calendar.YEAR, year);
                myCalendarDescipm.set(Calendar.MONTH, monthOfYear);
                myCalendarDescipm.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatedescipm();
            }
        };

        final DatePickerDialog.OnDateSetListener add_details_farm_flowering = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarAddFarmflowering.set(Calendar.YEAR, year);
                myCalendarAddFarmflowering.set(Calendar.MONTH, monthOfYear);
                myCalendarAddFarmflowering.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                new AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to enter Actual Flowering Date ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                update_actual_flowering_date();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        };

        final DatePickerDialog.OnDateSetListener add_details_farm_harvesting = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarAddFarmHarvest.set(Calendar.YEAR, year);
                myCalendarAddFarmHarvest.set(Calendar.MONTH, monthOfYear);
                myCalendarAddFarmHarvest.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                new AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to enter Actual Harvesting Date ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                update_actual_harvesting_date();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        };



        farmer_inm_done_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FarmDetailsActivity.this, dateactinm, myCalendarActinm
                        .get(Calendar.YEAR), myCalendarActinm.get(Calendar.MONTH),
                        myCalendarActinm.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        farmer_iwm_done_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FarmDetailsActivity.this, dateactiwm, myCalendarActiwm
                        .get(Calendar.YEAR), myCalendarActiwm.get(Calendar.MONTH),
                        myCalendarActiwm.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        farmer_ipm_done_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FarmDetailsActivity.this, dateactipm, myCalendarActipm
                        .get(Calendar.YEAR), myCalendarActipm.get(Calendar.MONTH),
                        myCalendarActipm.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        assistant_inm_done_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FarmDetailsActivity.this, datedescinm, myCalendarDescinm
                        .get(Calendar.YEAR), myCalendarDescinm.get(Calendar.MONTH),
                        myCalendarDescinm.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        assistant_iwm_done_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FarmDetailsActivity.this, datedesciwm, myCalendarDesciwm
                        .get(Calendar.YEAR), myCalendarDesciwm.get(Calendar.MONTH),
                        myCalendarDesciwm.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        assistant_ipm_done_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FarmDetailsActivity.this, datedescipm, myCalendarDescipm
                        .get(Calendar.YEAR), myCalendarDescipm.get(Calendar.MONTH),
                        myCalendarDescipm.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        actual_flowering_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FarmDetailsActivity.this, add_details_farm_flowering, myCalendarAddFarmflowering
                        .get(Calendar.YEAR), myCalendarAddFarmflowering.get(Calendar.MONTH),
                        myCalendarAddFarmflowering.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        actual_harvest_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FarmDetailsActivity.this, add_details_farm_harvesting, myCalendarAddFarmHarvest
                        .get(Calendar.YEAR), myCalendarAddFarmHarvest.get(Calendar.MONTH),
                        myCalendarAddFarmHarvest.get(Calendar.DAY_OF_MONTH)).show();
            }
        });









        String actual_area="";
        String soil_type = "";
        String avg_germination="";
        if(DataHandler.newInstance().getFetchFarmResult().getSoilType()!=null) {
            soil_type = DataHandler.newInstance().getFetchFarmResult().getSoilType().toString();
        }
        if(DataHandler.newInstance().getFetchFarmResult().getActualArea()!=null){
            actual_area=DataHandler.newInstance().getFetchFarmResult().getActualArea().toString();
        }
        if(DataHandler.newInstance().getFetchFarmResult().getGermination()!=null){
            avg_germination=DataHandler.newInstance().getFetchFarmResult().getGermination().toString();
        }

        if ((!actual_area.trim().equals("0")) && (!soil_type.trim().equals(""))) {
            verify_farm_area_and_details.setText("View Farm Details");
            navigateTo = "view_details";
            add_details_irrigation_source.setText(DataHandler.newInstance().getFetchFarmResult().getIrrigationSource().toString());
            add_details_previous_crop.setText(DataHandler.newInstance().getFetchFarmResult().getPreviousCrop().toString());
            add_details_irrigation_type.setText(DataHandler.newInstance().getFetchFarmResult().getIrrigationType().toString());
            add_details_soil_type.setText(DataHandler.newInstance().getFetchFarmResult().getSoilType().toString());
            add_details_sowing_date.setText(DataHandler.newInstance().getFetchFarmResult().getSowingDate().toString());
            add_details_exp_flowering_date.setText(DataHandler.newInstance().getFetchFarmResult().getExpFloweringDate().toString());
            add_details_exp_harvesting_date.setText(DataHandler.newInstance().getFetchFarmResult().getExpHarvestDate().toString());
            if(!DataHandler.newInstance().getFetchFarmResult().getActualFloweringDate().toString().matches("0000-01-01")){
                actual_flowering_date.setText(DataHandler.newInstance().getFetchFarmResult().getActualFloweringDate().toString());
                actual_flowering_date.setCompoundDrawables(null,null,null,null);
                actual_flowering_date.setClickable(false);
            }
            if(!DataHandler.newInstance().getFetchFarmResult().getActualHarvestDate().toString().matches("0000-01-01")){
                actual_harvest_date.setText(DataHandler.newInstance().getFetchFarmResult().getActualHarvestDate().toString());
                actual_harvest_date.setCompoundDrawables(null,null,null,null);
                actual_harvest_date.setClickable(false);
            }

            view_farm_area_on_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShowAreaOnMapActivity.class);
                    ActivityOptions options = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        context.startActivity(intent, options.toBundle());
                        //finish();
                    } else {
                        startActivity(intent);
                        //finish();
                    }
                }
            });

        } else {
            if (actual_area.trim().equals("0") ) {
                navigateTo = "map";
                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 270.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                down_arrow_img_show_verify_farm.startAnimation(rotateAnimation);
            } else {
                verify_farm_area_and_details.setText("Verify Farm Details");
                navigateTo = "verify_details";
                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 270.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                down_arrow_img_show_verify_farm.startAnimation(rotateAnimation);
            }
        }



            if (avg_germination.equals("")) {
                germi_navigate_to = "add_form";
                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 270.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                down_arrow_img_germi_and_spacing.startAnimation(rotateAnimation);

            } else {
                if (DataHandler.newInstance().getFetchFarmResult().getSpacingPtp() != NULL) {
                    avg_pp_spacing_tv.setText(DataHandler.newInstance().getFetchFarmResult().getSpacingPtp().toString());
                }
                if (DataHandler.newInstance().getFetchFarmResult().getSpacingRtr() != NULL) {
                    avg_rr_spacing_tv.setText(DataHandler.newInstance().getFetchFarmResult().getSpacingRtr().toString());
                }
                if (DataHandler.newInstance().getFetchFarmResult().getPopulation() != NULL) {
                    avg_farm_popu_tv.setText(String.valueOf(DataHandler.newInstance().getFetchFarmResult().getPopulation().toString()));
                }
                if (DataHandler.newInstance().getFetchFarmResult().getGermination() != NULL) {
                    avg_germination_tv.setText(String.valueOf(DataHandler.newInstance().getFetchFarmResult().getGermination().toString()));
                }

                germi_navigate_to = "view_germi_form";
                germi_and_spacing_custom_text.setText("View Germination and Spacing Form");
            }










        progressBar_cyclic.setVisibility(View.VISIBLE);

        AsyncFetchVisit asyncFetchVisit = new AsyncFetchVisit();
        asyncFetchVisit.execute();


        actual_flowering_card_view.setOnClickListener(this);
        actual_harvesting_card_view.setOnClickListener(this);


        card_view_show_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to open Map ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(context, ShowAreaOnMapActivity
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
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });


        visit_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (farmer_inm_material.getSelectedItem().toString().trim().equals("DAP")) {
                    farmer_inm_material_int = 1;

                } else if (farmer_inm_material.getSelectedItem().toString().trim().equals("MOP")) {
                    farmer_inm_material_int = 2;

                } else if (farmer_inm_material.getSelectedItem().toString().trim().equals("Urea")) {
                    farmer_inm_material_int = 3;
                } else if (farmer_inm_material.getSelectedItem().toString().trim().equals("20-20-10-13")) {
                    farmer_inm_material_int = 4;
                }

                if (farmer_inm_unit.getSelectedItem().toString().trim().equals("kg")) {
                    farmer_inm_unit_int = 1;

                } else if (farmer_inm_unit.getSelectedItem().toString().trim().equals("litre")) {
                    farmer_inm_unit_int = 1;

                } else if (farmer_inm_unit.getSelectedItem().toString().trim().equals("quintol")) {
                    farmer_inm_unit_int = 1;

                } else if (farmer_inm_unit.getSelectedItem().toString().trim().equals("ton")) {
                    farmer_inm_unit_int = 1;
                }

                if (farmer_iwm_material.getSelectedItem().toString().trim().equals("Atrizen")) {
                    farmer_iwm_material_int = 5;

                } else if (farmer_iwm_material.getSelectedItem().toString().trim().equals("Laudis")) {
                    farmer_iwm_material_int = 6;

                } else if (farmer_iwm_material.getSelectedItem().toString().trim().equals("Roundup")) {
                    farmer_iwm_material_int = 7;
                }

                if (farmer_iwm_unit.getSelectedItem().toString().trim().equals("kg")) {
                    farmer_iwm_unit_int = 1;

                } else if (farmer_iwm_unit.getSelectedItem().toString().trim().equals("litre")) {
                    farmer_iwm_unit_int = 1;

                } else if (farmer_iwm_unit.getSelectedItem().toString().trim().equals("quintol")) {
                    farmer_inm_unit_int = 1;

                } else if (farmer_iwm_unit.getSelectedItem().toString().trim().equals("ton")) {
                    farmer_inm_unit_int = 1;
                }

                if (farmer_ipm_material.getSelectedItem().toString().trim().equals("Carazen")) {
                    farmer_ipm_material_int = 8;

                } else if (farmer_ipm_material.getSelectedItem().toString().trim().equals("Amistar")) {
                    farmer_ipm_material_int = 9;
                }

                if (farmer_ipm_unit.getSelectedItem().toString().trim().equals("kg")) {
                    farmer_ipm_unit_int = 1;

                } else if (farmer_ipm_unit.getSelectedItem().toString().trim().equals("litre")) {
                    farmer_ipm_unit_int = 1;

                } else if (farmer_ipm_unit.getSelectedItem().toString().trim().equals("quintol")) {
                    farmer_ipm_unit_int = 1;

                } else if (farmer_ipm_unit.getSelectedItem().toString().trim().equals("ton")) {
                    farmer_ipm_unit_int = 1;
                }

                if (farmer_cpc_grade.getSelectedItem().toString().trim().equals("A Grade")) {
                    farmer_cpc_grade_int = 10;
                } else if (farmer_cpc_grade.getSelectedItem().toString().trim().equals("B Grade")) {
                    farmer_cpc_grade_int = 11;
                } else if (farmer_cpc_grade.getSelectedItem().toString().trim().equals("C Grade")) {
                    farmer_cpc_grade_int = 12;
                } else if (farmer_cpc_grade.getSelectedItem().toString().trim().equals("D Grade")) {
                    farmer_cpc_grade_int = 13;
                }


                if (farmer_sml_moisture_level.getSelectedItem().toString().trim().equals("Moist")) {
                    farmer_sml_moisture_level_int = 16;
                } else if (farmer_sml_moisture_level.getSelectedItem().toString().trim().equals("Not Moist")) {
                    farmer_sml_moisture_level_int = 14;
                } else if (farmer_sml_moisture_level.getSelectedItem().toString().trim().equals("Less Moist")) {
                    farmer_sml_moisture_level_int = 15;
                } else if (farmer_sml_moisture_level.getSelectedItem().toString().trim().equals("High Moist")) {
                    farmer_sml_moisture_level_int = 17;
                }

                if (assistant_inm_material.getSelectedItem().toString().trim().equals("DAP")) {
                    assistant_inm_material_int = 1;
                } else if (assistant_inm_material.getSelectedItem().toString().trim().equals("MOP")) {
                    assistant_inm_material_int = 2;
                } else if (assistant_inm_material.getSelectedItem().toString().trim().equals("Urea")) {
                    assistant_inm_material_int = 3;
                } else if (assistant_inm_material.getSelectedItem().toString().trim().equals("20-20-10-13")) {
                    assistant_inm_material_int = 4;
                }


                if (assistant_inm_unit.getSelectedItem().toString().trim().equals("kg")) {
                    assistant_inm_unit_int = 1;
                } else if (assistant_inm_unit.getSelectedItem().toString().trim().equals("litre")) {
                    assistant_inm_unit_int = 1;
                } else if (assistant_inm_unit.getSelectedItem().toString().trim().equals("quintol")) {
                    assistant_inm_unit_int = 1;
                } else if (assistant_inm_unit.getSelectedItem().toString().trim().equals("ton")) {
                    assistant_inm_unit_int = 1;
                }


                if (assistant_iwm_material.getSelectedItem().toString().trim().equals("Atrizen")) {
                    assistant_iwm_material_int = 5;
                } else if (assistant_inm_material.getSelectedItem().toString().trim().equals("Laudis")) {
                    assistant_iwm_material_int = 6;
                } else if (assistant_inm_material.getSelectedItem().toString().trim().equals("Roundup")) {
                    assistant_iwm_material_int = 7;
                }

                if (assistant_iwm_unit.getSelectedItem().toString().trim().equals("kg")) {
                    assistant_iwm_unit_int = 1;
                } else if (assistant_iwm_unit.getSelectedItem().toString().trim().equals("litre")) {
                    assistant_iwm_unit_int = 1;
                } else if (assistant_iwm_unit.getSelectedItem().toString().trim().equals("quintol")) {
                    assistant_iwm_unit_int = 1;
                } else if (assistant_iwm_unit.getSelectedItem().toString().trim().equals("ton")) {
                    assistant_iwm_unit_int = 1;
                }

                if (assistant_ipm_material.getSelectedItem().toString().trim().equals("Carazen")) {
                    assistant_ipm_material_int = 8;
                } else if (assistant_ipm_material.getSelectedItem().toString().trim().equals("Amistar")) {
                    assistant_ipm_material_int = 9;
                }

                if (assistant_ipm_unit.getSelectedItem().toString().trim().equals("kg")) {
                    assistant_ipm_unit_int = 1;
                } else if (assistant_ipm_unit.getSelectedItem().toString().trim().equals("litre")) {
                    assistant_ipm_unit_int = 1;
                } else if (assistant_ipm_unit.getSelectedItem().toString().trim().equals("quintol")) {
                    assistant_ipm_unit_int = 1;
                } else if (assistant_ipm_unit.getSelectedItem().toString().trim().equals("ton")) {
                    assistant_ipm_unit_int = 1;
                }

                if (assistant_cpc_grade.getSelectedItem().toString().trim().equals("A Grade")) {
                    assistant_cpc_grade_int = 10;
                } else if (assistant_cpc_grade.getSelectedItem().toString().trim().equals("B Grade")) {
                    assistant_cpc_grade_int = 11;
                } else if (assistant_cpc_grade.getSelectedItem().toString().trim().equals("C Grade")) {
                    assistant_cpc_grade_int = 12;
                } else if (assistant_cpc_grade.getSelectedItem().toString().trim().equals("D Grade")) {
                    assistant_cpc_grade_int = 13;
                }

                if (assistant_sml_moisture_level.getSelectedItem().toString().trim().equals("Moist")) {
                    assistant_sml_moisture_level_int = 16;
                } else if (assistant_sml_moisture_level.getSelectedItem().toString().trim().equals("Not Moist")) {
                    assistant_sml_moisture_level_int = 14;
                } else if (assistant_sml_moisture_level.getSelectedItem().toString().trim().equals("Less Moist")) {
                    assistant_sml_moisture_level_int = 15;
                } else if (assistant_sml_moisture_level.getSelectedItem().toString().trim().equals("High Moist")) {
                    assistant_sml_moisture_level_int = 17;
                }

                if (assistant_thining_moisture_level.getSelectedItem().toString().trim().equals("Moist")) {
                    assistant_thining_moisture_level_int = 16;
                } else if (assistant_thining_moisture_level.getSelectedItem().toString().trim().equals("Not Moist")) {
                    assistant_thining_moisture_level_int = 14;
                } else if (assistant_thining_moisture_level.getSelectedItem().toString().trim().equals("Less Moist")) {
                    assistant_thining_moisture_level_int = 15;
                } else if (assistant_thining_moisture_level.getSelectedItem().toString().trim().equals("High Moist")) {
                    assistant_thining_moisture_level_int = 17;
                }



                if(TextUtils.isEmpty(standing_crop_area_et.getText().toString().trim())){
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill Standing Crop Area",Snackbar.LENGTH_LONG);
                    standing_crop_area_et.getParent().requestChildFocus(standing_crop_area_et,standing_crop_area_et);
                    standing_crop_area_et.setError("Standing crop area can't be null");
                    snackbar.show();
                }
                else if(farmer_inm_material.getSelectedItem().toString().equals("Select")) {
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in farmer INM material",Snackbar.LENGTH_LONG);
                    snackbar.show();
                   /* scroll_view_farm_details.smoothScrollTo(0,farmer_inm_material.getTop());
                    farmer_inm_material.requestFocus();*/
                    TextView errorText = (TextView)farmer_inm_material.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("");
                    farmer_inm_material.getParent().requestChildFocus(farmer_inm_quantity,farmer_inm_quantity);
                }
                else if(TextUtils.isEmpty(farmer_inm_quantity.getText().toString().trim()))
                {
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in farmer INM quantity",Snackbar.LENGTH_LONG);

                    farmer_inm_quantity.setError("Enter inm quantity");
                    farmer_inm_quantity.getParent().requestChildFocus(farmer_inm_quantity,farmer_inm_quantity);

                   /* scroll_view_farm_details.smoothScrollTo(0,farmer_inm_quantity.getTop());
                    farmer_inm_quantity.requestFocus();*/
                    snackbar.show();
                }
                else if(farmer_inm_done_date.getText().toString().matches("")){
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in farmer INM done date",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    farmer_inm_done_date.setError("Enter Date");
                    farmer_inm_done_date.getParent().requestChildFocus(farmer_inm_done_date,farmer_inm_done_date);

                }
                else if(farmer_iwm_material.getSelectedItem().toString().equals("Select")) {
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in farmer IWM material",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    TextView errorText = (TextView)farmer_iwm_material.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("");
                    farmer_iwm_material.getParent().requestChildFocus(farmer_iwm_material,farmer_iwm_material);
                }
                else if(TextUtils.isEmpty(farmer_iwm_quantity.getText().toString().trim())){
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in farmer IWM quantity",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    farmer_iwm_quantity.setError("Enter iwm quantity");
                    farmer_iwm_quantity.getParent().requestChildFocus(farmer_iwm_quantity,farmer_iwm_quantity);
                }
                else if(farmer_iwm_done_date.getText().toString().matches("")){
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in farmer IWM done date",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    farmer_iwm_done_date.setError("Enter Date");
                    farmer_iwm_done_date.getParent().requestChildFocus(farmer_iwm_done_date,farmer_iwm_done_date);

                }
                else if(farmer_ipm_material.getSelectedItem().toString().equals("Select")) {
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in farmer IPM material",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    TextView errorText = (TextView)farmer_ipm_material.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("");
                    farmer_ipm_material.getParent().requestChildFocus(farmer_ipm_material,farmer_ipm_material);
                }
                else if(TextUtils.isEmpty(farmer_ipm_quantity.getText().toString().trim())){
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in farmer IPM quantity",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    farmer_ipm_quantity.setError("Enter ipm quantity");
                    farmer_ipm_quantity.getParent().requestChildFocus(farmer_ipm_quantity,farmer_ipm_quantity);

                }
                else if(farmer_ipm_done_date.getText().toString().matches("")){
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in farmer IPM done date",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    farmer_ipm_done_date.setError("Enter Date");
                    farmer_ipm_done_date.getParent().requestChildFocus(farmer_ipm_done_date,farmer_ipm_done_date);

                }
                else if(farmer_cpc_grade.getSelectedItem().toString().equals("Select")) {
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in farmer CPC grade",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    TextView errorText = (TextView)farmer_cpc_grade.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("");
                    farmer_cpc_grade.getParent().requestChildFocus(farmer_cpc_grade,farmer_cpc_grade);
                }
                else if(farmer_sml_moisture_level.getSelectedItem().toString().equals("Select")) {
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in farmer SML level",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    TextView errorText = (TextView)farmer_sml_moisture_level.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("");
                    farmer_sml_moisture_level.getParent().requestChildFocus(farmer_sml_moisture_level,farmer_sml_moisture_level);
                }
                else if(assistant_inm_material.getSelectedItem().toString().equals("Select")) {
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in assistant INM material",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    TextView errorText = (TextView)assistant_inm_material.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("");
                    assistant_inm_material.getParent().requestChildFocus(assistant_inm_material,assistant_inm_material);
                }
                else if(TextUtils.isEmpty(assistant_inm_quantity.getText().toString().trim())){
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in assistant INM quantity",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    assistant_inm_quantity.setError("Enter Prescribed inm quantity");
                    assistant_inm_quantity.getParent().requestChildFocus(assistant_inm_quantity,assistant_inm_quantity);

                }
                else if(assistant_inm_done_date.getText().toString().matches("")){
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in assistant INM done date",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    assistant_inm_done_date.setError("Enter Date");
                    assistant_inm_done_date.getParent().requestChildFocus(assistant_inm_done_date,assistant_inm_done_date);

                }
                else if(assistant_iwm_material.getSelectedItem().toString().equals("Select")) {
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in assistant IWM material",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    TextView errorText = (TextView)assistant_iwm_material.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("");
                    assistant_iwm_material.getParent().requestChildFocus(assistant_iwm_material,assistant_iwm_material);
                }
                else if(TextUtils.isEmpty(assistant_iwm_quantity.getText().toString().trim())){
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in assistant IWM quantity",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    assistant_iwm_quantity.setError("Enter Prescribed iwm quantity");
                    assistant_iwm_quantity.getParent().requestChildFocus(assistant_iwm_quantity,assistant_iwm_quantity);

                }
                else if(assistant_iwm_done_date.getText().toString().matches("")){
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in assistant IWM done date",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    assistant_iwm_done_date.setError("Enter Date");
                    assistant_iwm_done_date.getParent().requestChildFocus(assistant_iwm_done_date,assistant_iwm_done_date);

                }
                else if(assistant_ipm_material.getSelectedItem().toString().equals("Select")) {
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in assistant IPM material",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    TextView errorText = (TextView)assistant_ipm_material.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("");
                    assistant_ipm_material.getParent().requestChildFocus(assistant_ipm_material,assistant_ipm_material);

                }

                else if(TextUtils.isEmpty(assistant_ipm_quantity.getText().toString().trim())){
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in assistant IPM quantity",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    assistant_ipm_quantity.setError("Enter Prescribed ipm quantity");
                    assistant_ipm_quantity.getParent().requestChildFocus(assistant_ipm_quantity,assistant_ipm_quantity);
                }
                else if(assistant_ipm_done_date.getText().toString().matches("")){
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in assistant IPM done date",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    assistant_ipm_done_date.setError("Enter Date");
                    assistant_ipm_done_date.getParent().requestChildFocus(assistant_ipm_done_date,assistant_ipm_done_date);

                }
                else if(assistant_cpc_grade.getSelectedItem().toString().equals("Select")) {
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in assistant CPC grade",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    TextView errorText = (TextView)assistant_cpc_grade.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("");
                    assistant_cpc_grade.getParent().requestChildFocus(assistant_cpc_grade,assistant_cpc_grade);

                }
                else if(assistant_sml_moisture_level.getSelectedItem().toString().equals("Select")) {
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in assistant SML moisture level", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    TextView errorText = (TextView)assistant_sml_moisture_level.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("");
                    assistant_sml_moisture_level.getParent().requestChildFocus(assistant_sml_moisture_level,assistant_sml_moisture_level);

                }
                else if(assistant_thining_moisture_level.getSelectedItem().toString().equals("Select")) {
                    Snackbar snackbar=Snackbar.make(rel_add_visit_full_lay,"Please fill in assistant thinning moisture level",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    TextView errorText = (TextView)assistant_thining_moisture_level.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("");
                    assistant_thining_moisture_level.getParent().requestChildFocus(assistant_thining_moisture_level,assistant_thining_moisture_level);

                }
                else {
                    MyAsyncTask myAsyncTask = new MyAsyncTask();
                    myAsyncTask.execute();
                }



            }
        });


        Onclick();

    }

    private void Onclick() {
      /*  update_farm_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(rel_update_farm_details);
            }
        });
*/
        /*sowing_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(rel_sowing_details);
            }
        });
*/
        add_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(rel_add_visit, "add_visit");
            }
        });


        germi_and_spacing_custom_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actual_area="0";
if(DataHandler.newInstance().getFetchFarmResult().getActualArea()!=null){
    actual_area=DataHandler.newInstance().getFetchFarmResult().getActualArea().toString();
}
                if(!actual_area.equals("0")) {

                    if (germi_navigate_to.equals("add_form")) {
                        Intent intent = new Intent(context, GerminationSpacingActivity.class);
                        ActivityOptions options = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            context.startActivity(intent, options.toBundle());
                        } else {
                            startActivity(intent);
                        }
                    } else {
                        animate(germi_data_hidden_lay, "show_germi_farm");

                    }
                }else{
                    Toast.makeText(context, "Please Verify farm area first", Toast.LENGTH_SHORT).show();
                }
            }
        });


        show_previous_visits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(dynamic_button,"show_visits");
            }
        });

        verify_farm_area_and_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (navigateTo.equals("map")) {


                    Intent intent = new Intent(context, MapsActivity.class);
                    ActivityOptions options = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        context.startActivity(intent, options.toBundle());
                    } else {
                        startActivity(intent);
                    }


                } else if (navigateTo.equals("verify_details")) {



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

                } else {
                    animate(show_farm_details, "farm_details");
                }
            }
        });
    }


    public void animate(final LinearLayout linearLayout, String from_where) {

        //LinearLayout dialog   = (LinearLayout)findViewById(R.id.my_layout);


        if (from_where.equals("add_visit")) {
            if (linearLayout.getVisibility() == View.GONE) {

                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                down_arrow_img.startAnimation(rotateAnimation);


                linearLayout.setVisibility(LinearLayout.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
                animation.setDuration(1000);
                linearLayout.setAnimation(animation);
                linearLayout.animate();
                animation.start();
            } else {
                RotateAnimation rotateAnimation = new RotateAnimation(180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                down_arrow_img.startAnimation(rotateAnimation);

                linearLayout.setVisibility(LinearLayout.INVISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
                animation.setDuration(1000);
                linearLayout.setAnimation(animation);
                linearLayout.animate();
                animation.start();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        linearLayout.setVisibility(LinearLayout.GONE);
                    }
                }, 1000);
            }
        } else if(from_where.equals("farm_details")) {
            if (linearLayout.getVisibility() == View.GONE) {

                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                down_arrow_img_show_verify_farm.startAnimation(rotateAnimation);


                linearLayout.setVisibility(LinearLayout.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
                animation.setDuration(500);
                linearLayout.setAnimation(animation);
                linearLayout.animate();
                animation.start();
            } else {
                RotateAnimation rotateAnimation = new RotateAnimation(180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                down_arrow_img_show_verify_farm.startAnimation(rotateAnimation);


                linearLayout.setVisibility(LinearLayout.INVISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
                animation.setDuration(500);
                linearLayout.setAnimation(animation);
                linearLayout.animate();
                animation.start();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        linearLayout.setVisibility(LinearLayout.GONE);
                    }
                }, 400);
            }
        } else if(from_where.equals("show_germi_farm")) {
            if (linearLayout.getVisibility() == View.GONE) {

                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                down_arrow_img_germi_and_spacing.startAnimation(rotateAnimation);


                linearLayout.setVisibility(LinearLayout.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
                animation.setDuration(500);
                linearLayout.setAnimation(animation);
                linearLayout.animate();
                animation.start();
            } else {
                RotateAnimation rotateAnimation = new RotateAnimation(180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                down_arrow_img_germi_and_spacing.startAnimation(rotateAnimation);


                linearLayout.setVisibility(LinearLayout.INVISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
                animation.setDuration(500);
                linearLayout.setAnimation(animation);
                linearLayout.animate();
                animation.start();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        linearLayout.setVisibility(LinearLayout.GONE);
                    }
                }, 400);
            }
        }
        else{
            if (linearLayout.getVisibility() == View.GONE) {

                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                down_arrow_img_show_visits.startAnimation(rotateAnimation);


                linearLayout.setVisibility(LinearLayout.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
                animation.setDuration(1000);
                animation.setFillAfter(false);
                linearLayout.setAnimation(animation);
                linearLayout.animate();
                animation.start();
            } else {
                RotateAnimation rotateAnimation = new RotateAnimation(180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                down_arrow_img_show_visits.startAnimation(rotateAnimation);

                linearLayout.setVisibility(LinearLayout.INVISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
                animation.setDuration(1000);
                animation.setFillAfter(false);
                linearLayout.setAnimation(animation);
                linearLayout.animate();
                animation.start();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        linearLayout.setVisibility(LinearLayout.GONE);
                    }
                }, 1000);
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.actual_flowering_card_view) {
            final Calendar myCalendarflowering = Calendar.getInstance();

            final DatePickerDialog.OnDateSetListener dateflowering = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    myCalendarflowering.set(Calendar.YEAR, year);
                    myCalendarflowering.set(Calendar.MONTH, monthOfYear);
                    myCalendarflowering.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String myFormat = "dd/MM/yyyy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    tv_actual_flowering_date.setText(sdf.format(myCalendarflowering.getTime()));
                }
            };
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.flowering_dialog, null);
            dialogBuilder.setView(dialogView);
            tv_actual_flowering_date = (TextView) dialogView.findViewById(R.id.tv_actual_flowering_date);


            tv_actual_flowering_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(FarmDetailsActivity.this, dateflowering, myCalendarflowering
                            .get(Calendar.YEAR), myCalendarflowering.get(Calendar.MONTH),
                            myCalendarflowering.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
            //final Spinner spinner = (Spinner) dialogView.findViewById(R.id.mySpinnersetlang);

            /*AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();*/

            dialogBuilder.setCancelable(true);
            dialogBuilder.setMessage("Enter Actual Flowering date");
            dialogBuilder.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }

            }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
//                        finish();
                    dialog.cancel();
                }
            });
            // Create the AlertDialog object and return it
            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();


        }


        if (v.getId() == R.id.actual_harvesting_card_view) {

            final Calendar myCalendarflowering = Calendar.getInstance();

            final DatePickerDialog.OnDateSetListener dateflowering = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    myCalendarflowering.set(Calendar.YEAR, year);
                    myCalendarflowering.set(Calendar.MONTH, monthOfYear);
                    myCalendarflowering.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String myFormat = "dd/MM/yyyy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    tv_actual_flowering_date.setText(sdf.format(myCalendarflowering.getTime()));
                }
            };

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.flowering_dialog, null);
            dialogBuilder.setView(dialogView);
            tv_actual_flowering_date = (TextView) dialogView.findViewById(R.id.tv_actual_flowering_date);


            tv_actual_flowering_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(FarmDetailsActivity.this, dateflowering, myCalendarflowering
                            .get(Calendar.YEAR), myCalendarflowering.get(Calendar.MONTH),
                            myCalendarflowering.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
            //final Spinner spinner = (Spinner) dialogView.findViewById(R.id.mySpinnersetlang);

            /*AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();*/

            dialogBuilder.setCancelable(true);
            dialogBuilder.setMessage("Enter Actual Flowering date");
            dialogBuilder.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }

            }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
//                        finish();
                    dialog.cancel();
                }
            });
            // Create the AlertDialog object and return it
            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();


        }



    }


    private void updatefloweringDateLabel() {

    }


    class MyAsyncTask extends AsyncTask<String, Void, String> {
        public MyAsyncTask() {
            super();
        }

        @Override
        protected String doInBackground(String... strings) {

            template_int_arr = new int[]{1, 2, 3, 4, 5, 7, 1, 2, 3, 4, 5, 6, 7};

            material_int_arr = new int[]{
                    /*farmer_inm_material.getSelectedItem().toString().trim(),
                    farmer_iwm_material.getSelectedItem().toString().trim(),
                    farmer_ipm_material.getSelectedItem().toString().trim(),
                    farmer_cpc_grade.getSelectedItem().toString().trim(),
                    farmer_sml_moisture_level.getSelectedItem().toString().trim(),
                    "0",
                    assistant_inm_material.getSelectedItem().toString().trim(),
                    assistant_iwm_material.getSelectedItem().toString().trim(),
                    assistant_ipm_material.getSelectedItem().toString().trim(),
                    assistant_cpc_grade.getSelectedItem().toString().trim(),
                    assistant_sml_moisture_level.getSelectedItem().toString().trim(),
                    assistant_thining_moisture_level.getSelectedItem().toString().trim(),
                    "0"*/
                    farmer_inm_material_int,
                    farmer_iwm_material_int,
                    farmer_ipm_material_int,
                    farmer_cpc_grade_int,
                    farmer_sml_moisture_level_int,
                    0,
                    assistant_inm_material_int,
                    assistant_iwm_material_int,
                    assistant_ipm_material_int,
                    assistant_cpc_grade_int,
                    assistant_sml_moisture_level_int,
                    assistant_thining_moisture_level_int,
                    0
            };

            quantity_str_arr = new String[]{
                    farmer_inm_quantity.getText().toString().trim(),
                    farmer_iwm_quantity.getText().toString().trim(),
                    farmer_ipm_quantity.getText().toString().trim(),
                    "0",
                    "0",
                    "0",
                    assistant_inm_quantity.getText().toString().trim(),
                    assistant_iwm_quantity.getText().toString().trim(),
                    assistant_ipm_quantity.getText().toString().trim(),
                    "0",
                    "0",
                    "0",
                    "0"
            };

            unit_id_int_arr = new int[]{
                    /*farmer_inm_unit.getSelectedItem().toString().trim(),
                    farmer_iwm_unit.getSelectedItem().toString().trim(),
                    farmer_ipm_unit.getSelectedItem().toString().trim(),
                    assistant_inm_unit.getSelectedItem().toString().trim(),
                    assistant_iwm_unit.getSelectedItem().toString().trim(),
                    assistant_ipm_unit.getSelectedItem().toString().trim()
                    */
                    farmer_inm_unit_int,
                    farmer_iwm_unit_int,
                    farmer_ipm_unit_int,
                    0,
                    0,
                    0,
                    assistant_inm_unit_int,
                    assistant_iwm_unit_int,
                    assistant_ipm_unit_int,
                    0,
                    0,
                    0,
                    0

            };


            String submit_format = "yyyy-MM-dd"; //In which you need put here
            SimpleDateFormat submit_sdf = new SimpleDateFormat(submit_format, Locale.US);

            Date farmer_inm_done_date_slash_format=null,farmer_iwm_done_date_slash_format=null;
            Date farmer_ipm_done_date_slash_format=null,assistant_inm_done_date_slash_format=null;
            Date assistant_iwm_done_date_slash_format=null,assistant_ipm_done_date_slash_format=null;


            try {
                farmer_inm_done_date_slash_format=new SimpleDateFormat("dd/MM/yyyy").parse(farmer_inm_done_date.getText().toString().trim());
                farmer_iwm_done_date_slash_format=new SimpleDateFormat("dd/MM/yyyy").parse(farmer_iwm_done_date.getText().toString().trim());
                farmer_ipm_done_date_slash_format=new SimpleDateFormat("dd/MM/yyyy").parse(farmer_ipm_done_date.getText().toString().trim());
                assistant_inm_done_date_slash_format=new SimpleDateFormat("dd/MM/yyyy").parse(assistant_inm_done_date.getText().toString().trim());
                assistant_iwm_done_date_slash_format=new SimpleDateFormat("dd/MM/yyyy").parse(assistant_iwm_done_date.getText().toString().trim());
                assistant_ipm_done_date_slash_format=new SimpleDateFormat("dd/MM/yyyy").parse(assistant_ipm_done_date.getText().toString().trim());

            } catch (ParseException e) {
                e.printStackTrace();
            }

            String str_farmer_inm_done_date=submit_sdf.format(farmer_inm_done_date_slash_format);
            String str_farmer_iwm_done_date=submit_sdf.format(farmer_iwm_done_date_slash_format);
            String str_farmer_ipm_done_date=submit_sdf.format(farmer_ipm_done_date_slash_format);
            String str_assistant_inm_done_date=submit_sdf.format(assistant_inm_done_date_slash_format);
            String str_assistant_iwm_done_date=submit_sdf.format(assistant_iwm_done_date_slash_format);
            String str_assistant_ipm_done_date=submit_sdf.format(assistant_ipm_done_date_slash_format);


            done_date_str_arr = new String[]{
                    str_farmer_inm_done_date,
                    str_farmer_iwm_done_date,
                    str_farmer_ipm_done_date,
                    "0000-01-01",
                    "0000-01-01",
                    "0000-01-01",
                    str_assistant_inm_done_date,
                    str_assistant_iwm_done_date,
                    str_assistant_ipm_done_date,
                    "0000-01-01",
                    "0000-01-01",
                    "0000-01-01",
                    "0000-01-01"
            };

            //final String[] array5={farmer_cpc_grade.getSelectedItem().toString().trim(),assistant_cpc_grade.getSelectedItem().toString().trim()};

       /* final String[] array6={farmer_sml_moisture_level.getSelectedItem().toString().trim(),
                assistant_sml_moisture_level.getSelectedItem().toString().trim(),
                assistant_thining_moisture_level.getSelectedItem().toString().trim()};*/

            comment_str_arr = new String[]{
                    "0",
                    "0",
                    "0",
                    "0",
                    "0",
                    farmer_other_comment.getText().toString().trim(),
                    "0",
                    "0",
                    "0",
                    "0",
                    "0",
                    "0",
                    assistant_other_comment.getText().toString().trim()
            };

            pres_arr = new String[]{
                    "N", "N", "N", "N", "N", "N", "Y", "Y", "Y", "Y", "Y", "Y", "Y"
            };


            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(c);



            for(int i=0;i<template_int_arr.length;i++) {
                Log.e("template_array"+i, String.valueOf(template_int_arr[i]));
                Log.e("comment_str_array"+i, comment_str_arr[i].toString());
                Log.e("done_date_str_arr"+i, done_date_str_arr[i].toString());
                Log.e("pres_arr"+i, pres_arr[i].toString());
                Log.e("quantity_str_arr"+i, quantity_str_arr[i].toString());
                Log.e("unit_id_int_arr"+i, String.valueOf(unit_id_int_arr[i]));
                Log.e("material_int_arr"+i, String.valueOf(material_int_arr[i]));
            }
            Log.e("formattedDate",formattedDate.toString());
            Log.e("visit_count",String.valueOf(visit_count));
            Log.e("comp_id",SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVCOMPID));
            Log.e("farm_id",SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVFARMID));
            Log.e("user_id",SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVUSERID));
            Log.e("effective_area",standing_crop_area_et.getText().toString());
            Log.e("visit_date",formattedDate);


            ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
            AddVisitSendData addVisitSendData = new AddVisitSendData();
            addVisitSendData.setActivity_template_id(template_int_arr);
            addVisitSendData.setComment(comment_str_arr);
            addVisitSendData.setDone_date(done_date_str_arr);
            addVisitSendData.setIs_prescribed(pres_arr);
            addVisitSendData.setQty(quantity_str_arr);
            addVisitSendData.setUnit_id(unit_id_int_arr);
            addVisitSendData.setMaterial_id(material_int_arr);
            addVisitSendData.setFarm_id(Integer.valueOf(SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVFARMID)));
            addVisitSendData.setComp_id(Integer.valueOf(SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVCOMPID)));
            addVisitSendData.setApproved_method("images");
            addVisitSendData.setVisit_date(formattedDate);
            addVisitSendData.setVisit_number(visit_count+1);
            addVisitSendData.setEffective_area(Float.valueOf(standing_crop_area_et.getText().toString().trim()));
            addVisitSendData.setAdded_by(Integer.valueOf(SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVUSERID)));

            Call<StatusMsgModel> statusMsgModelCall = apiService.getVisitMsgStatus(addVisitSendData);
            statusMsgModelCall.enqueue(new Callback<StatusMsgModel>() {
                @Override
                public void onResponse(Call<StatusMsgModel> call, Response<StatusMsgModel> response) {
                    if(response!=null) {
                        StatusMsgModel statusMsgModel = response.body();

                        if (statusMsgModel.getStatus() != 0) {
                            //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LandingActivity.class);
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
                    }
                }

                @Override
                public void onFailure(Call<StatusMsgModel> call, Throwable t) {
                    Toast.makeText(FarmDetailsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            return null;
        }

    }


    class AsyncFetchVisit extends AsyncTask<String, Void, String> {
        public AsyncFetchVisit() {
            super();
        }

        @Override
        protected String doInBackground(String... strings) {

            ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
            SendFarmData sendFarmData = new SendFarmData();

            sendFarmData.setFarm_id(SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVFARMID));
            sendFarmData.setComp_id(SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVCOMPID));



                Call<ViewFarmData> callData = apiService.viewFarmDataFunction(sendFarmData);
                callData.enqueue(new Callback<ViewFarmData>() {
                    @Override
                    public void onResponse(Call<ViewFarmData> call, Response<ViewFarmData> response) {

                        try {
                        ViewFarmData viewFarmData = response.body();
                        //String status=viewFarmData.getStatus().toString();

                        String msg = viewFarmData.getMsg().toString();
                       // Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        resultList = viewFarmData.getResult();
                        int l = resultList.size();
                        visit_count = resultList.size();

                       // Toast.makeText(context, visit_count + "", Toast.LENGTH_SHORT).show();
                        makeButtons(l);
                        for (int i = 0; i < resultList.size(); i++) {
                            viewFarmResults = resultList.get(i);
                        }

                        progressBar_cyclic.setVisibility(View.INVISIBLE);
                        }catch (Exception e){
                            e.printStackTrace();
                            progressBar_cyclic.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ViewFarmData> call, Throwable t) {
                        progressBar_cyclic.setVisibility(View.INVISIBLE);
                    }

                });


            return null;
        }
    }


    public void makeButtons(int l) {
        for (int i = l-1; i >=0; i--) {
            final int x = i;
            final int m = i + 1;
            final String y = String.valueOf(m);
            Button btn = new Button(this);
            btn.setText("Visit" + m);
            int color = Color.parseColor("#00f0f0");
            btn.setBackgroundColor(color);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataHandler.newInstance().setViewFarmResultList(resultList.get(x));
                    Intent intent = new Intent(context, VisitReportActivity.class);
                    ActivityOptions options = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        context.startActivity(intent, options.toBundle());
                        //finish();
                    } else {
                        startActivity(intent);
                        //finish();
                    }
                    //Toast.makeText(FirstPage.this, "Button"+x+"clicked", Toast.LENGTH_SHORT).show();
                }
            });
            params1.setMargins(20, 60, 20, 0);
            btn.setLayoutParams(params1);
            dynamic_button.addView(btn);


        }

    }

}
