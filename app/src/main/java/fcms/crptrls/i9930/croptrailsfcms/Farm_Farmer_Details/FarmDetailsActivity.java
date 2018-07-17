package fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import fcms.crptrls.i9930.croptrailsfcms.CustomTextView.CustomTextView;
import fcms.crptrls.i9930.croptrailsfcms.DataHandler.DataHandler;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpApiInterface;
import fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmDetailModel.AddVisitSendData;
import fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmDetailsUpdate.FarmDetailsUpdateActivity;
import fcms.crptrls.i9930.croptrailsfcms.Login.LoginActivity;
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

public class FarmDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    CustomTextView add_visit, verify_farm_area_and_details,show_previous_visits;
    LinearLayout /*rel_update_farm_details*/show_farm_details, rel_add_visit;
    Context context;
    Animation mAnimation;
    ImageView down_arrow_img,down_arrow_img_show_visits;
    Toolbar mActionBarToolbar;
    TextView title_name,title_address;

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

    TextView farmer_inm_done_date;
    TextView farmer_iwm_done_date;
    TextView farmer_ipm_done_date;
    TextView assistant_inm_done_date;
    TextView assistant_iwm_done_date;
    TextView assistant_ipm_done_date;

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


    String navigateTo = "";

    TextView add_details_irrigation_source, add_details_previous_crop, add_details_irrigation_type, add_details_soil_type;
    TextView add_details_sowing_date, add_details_exp_harvesting_date, add_details_exp_flowering_date;
    Calendar myCalendarActinm = Calendar.getInstance();
    Calendar myCalendarActiwm = Calendar.getInstance();
    Calendar myCalendarActipm = Calendar.getInstance();
    Calendar myCalendarDescinm = Calendar.getInstance();
    Calendar myCalendarDesciwm = Calendar.getInstance();
    Calendar myCalendarDescipm = Calendar.getInstance();







    private void updateactinm() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        farmer_inm_done_date.setText(sdf.format(myCalendarActinm.getTime()));
    }
    private void updateactiwm() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        farmer_iwm_done_date.setText(sdf.format(myCalendarActiwm.getTime()));
    }
    private void updateactipm() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        farmer_ipm_done_date.setText(sdf.format(myCalendarActipm.getTime()));
    }
    private void updatedescinm() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        assistant_inm_done_date.setText(sdf.format(myCalendarDescinm.getTime()));
    }
    private void updatedesciwm() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        assistant_iwm_done_date.setText(sdf.format(myCalendarDesciwm.getTime()));
    }
    private void updatedescipm() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        assistant_ipm_done_date.setText(sdf.format(myCalendarDescipm.getTime()));
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

        title_name.setText(firstLetter+cap);
        title_address.setText(DataHandler.newInstance().getFetchFarmResult().getAddL1()+", "+DataHandler.newInstance().getFetchFarmResult().getAddL2());

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
        show_previous_visits=(CustomTextView)findViewById(R.id.show_previous_visits);
        /*rel_update_farm_details=(RelativeLayout)findViewById(R.id.detail_full_layout);*/
        show_farm_details = (LinearLayout) findViewById(R.id.show_farm_details);
        rel_add_visit = (LinearLayout) findViewById(R.id.rel_add_visit_drop);
        down_arrow_img = (ImageView) findViewById(R.id.down_arrow_img);
        down_arrow_img_show_visits=(ImageView)findViewById(R.id.down_arrow_img_show_visits);
        farm_lot_no = (TextView) findViewById(R.id.farm_lot_no);

        dynamic_button = (LinearLayout) findViewById(R.id.dynamic_button);


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

        farmer_inm_done_date = (TextView) findViewById(R.id.farmer_inm_done_date);
        farmer_iwm_done_date = (TextView) findViewById(R.id.farmer_iwm_done_date);
        farmer_ipm_done_date = (TextView) findViewById(R.id.farmer_ipm_done_date);
        assistant_inm_done_date = (TextView)findViewById(R.id.assistant_inm_done_date);
        assistant_iwm_done_date = (TextView) findViewById(R.id.assistant_iwm_done_date);
        assistant_ipm_done_date = (TextView) findViewById(R.id.assistant_ipm_done_date);

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


        farm_lot_no.setText(DataHandler.newInstance().getFetchFarmResult().getLotNo());

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
                updateactiwm();
            }
        };

        final DatePickerDialog.OnDateSetListener datedescipm = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarDescipm.set(Calendar.YEAR, year);
                myCalendarDescipm.set(Calendar.MONTH, monthOfYear);
                myCalendarDescipm.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateactipm();
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




        String actual_area = DataHandler.newInstance().getFetchFarmResult().getActualArea().toString();
        String soil_type = DataHandler.newInstance().getFetchFarmResult().getSoilType().toString();

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
        } else {
            if (actual_area.trim().equals("0")) {
                navigateTo = "map";
            } else {
                verify_farm_area_and_details.setText("Verify Farm Details");
                navigateTo = "verify_details";
            }
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


                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
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

                linearLayout.setVisibility(LinearLayout.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
                animation.setDuration(500);
                linearLayout.setAnimation(animation);
                linearLayout.animate();
                animation.start();
            } else {

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

            done_date_str_arr = new String[]{
                    farmer_inm_done_date.getText().toString().trim(),
                    farmer_iwm_done_date.getText().toString().trim(),
                    farmer_ipm_done_date.getText().toString().trim(),
                    "0",
                    "0",
                    "0",
                    assistant_inm_done_date.getText().toString().trim(),
                    assistant_iwm_done_date.getText().toString().trim(),
                    assistant_ipm_done_date.getText().toString().trim(),
                    "0",
                    "0",
                    "0",
                    "0"
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
            addVisitSendData.setApproved_method("abc");
            addVisitSendData.setVisit_date("1996-08-01");
            addVisitSendData.setVisit_number(2);
            addVisitSendData.setEffective_area(35);
            addVisitSendData.setAdded_by(Integer.valueOf(SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVUSERID)));

            Call<StatusMsgModel> statusMsgModelCall = apiService.getVisitMsgStatus(addVisitSendData);
            statusMsgModelCall.enqueue(new Callback<StatusMsgModel>() {
                @Override
                public void onResponse(Call<StatusMsgModel> call, Response<StatusMsgModel> response) {
                    StatusMsgModel statusMsgModel = response.body();
                    //Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(FarmDetailsActivity.this, statusMsgModel.getMsg(), Toast.LENGTH_SHORT).show();
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
                    ViewFarmData viewFarmData = response.body();
                    //String status=viewFarmData.getStatus().toString();

                    String msg = viewFarmData.getMsg().toString();
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    resultList = viewFarmData.getResult();
                    int l = resultList.size();
                    makeButtons(l);
                    for (int i = 0; i < resultList.size(); i++) {
                        viewFarmResults = resultList.get(i);
                    }

                    progressBar_cyclic.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure(Call<ViewFarmData> call, Throwable t) {
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
                    Intent intent = new Intent(getApplicationContext(), VisitReportActivity.class);
                    intent.putExtra("visit", y);
                    startActivity(intent);
                    //Toast.makeText(FirstPage.this, "Button"+x+"clicked", Toast.LENGTH_SHORT).show();
                }
            });
            params1.setMargins(20, 60, 20, 0);
            btn.setLayoutParams(params1);
            dynamic_button.addView(btn);


        }

    }

}
