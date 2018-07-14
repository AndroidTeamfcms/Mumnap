package fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmDetailsUpdate;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpApiInterface;
import fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmDetailsUpdate.Model.FarmDetailsUpdateSendData;
import fcms.crptrls.i9930.croptrailsfcms.Landing.LandingActivity;
import fcms.crptrls.i9930.croptrailsfcms.R;
import fcms.crptrls.i9930.croptrailsfcms.StatusMsgModel.StatusMsgModel;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmDetailsUpdateActivity extends AppCompatActivity {

    TextView sowingDateet,expFloweringDateet,expHarvestingDateet,update_farm_details;
    Spinner spinner_irri_source,spinner_irri_type,spinner_perv_crop,spinner_soil_type;
    Calendar myCalendarsowing = Calendar.getInstance();
    Calendar myCalendarflowering = Calendar.getInstance();
    Calendar myCalendarharvesting = Calendar.getInstance();
    Context context;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.new_theme));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_details_update);


        context=this;

        sowingDateet=(TextView) findViewById(R.id.sowing_date_et);
        expFloweringDateet=(TextView)findViewById(R.id.exp_flowering_date_et);
        expHarvestingDateet=(TextView)findViewById(R.id.exp_harvesting_date_et);
        progressBar=(ProgressBar)findViewById(R.id.progressBar_cyclic);
        spinner_irri_source=(Spinner)findViewById(R.id.spinner_irrigation_source);
        spinner_irri_type=(Spinner)findViewById(R.id.spinner_irrigation_type);
        spinner_perv_crop=(Spinner)findViewById(R.id.spinner_previous_crop);
        spinner_soil_type=(Spinner)findViewById(R.id.spinner_soil_type);
        update_farm_details=(TextView)findViewById(R.id.update_farm_details);


        final DatePickerDialog.OnDateSetListener datesowing = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarsowing.set(Calendar.YEAR, year);
                myCalendarsowing.set(Calendar.MONTH, monthOfYear);
                myCalendarsowing.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatesowingDateLabel();
            }
        };

      /*  final DatePickerDialog.OnDateSetListener dateflowering = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarflowering.set(Calendar.YEAR, year);
                myCalendarflowering.set(Calendar.MONTH, monthOfYear);
                myCalendarflowering.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatefloweringDateLabel();
            }
        };

        final DatePickerDialog.OnDateSetListener dateharvesting = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarharvesting.set(Calendar.YEAR, year);
                myCalendarharvesting.set(Calendar.MONTH, monthOfYear);
                myCalendarharvesting.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateHarvestingDateLabel();
            }
        };*/

        sowingDateet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FarmDetailsUpdateActivity.this, datesowing, myCalendarsowing
                        .get(Calendar.YEAR), myCalendarsowing.get(Calendar.MONTH),
                        myCalendarsowing.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



      /*  expFloweringDateet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FarmDetailsUpdateActivity.this, dateflowering, myCalendarflowering
                        .get(Calendar.YEAR), myCalendarflowering.get(Calendar.MONTH),
                        myCalendarflowering.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        expHarvestingDateet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FarmDetailsUpdateActivity.this, dateharvesting, myCalendarharvesting
                        .get(Calendar.YEAR), myCalendarharvesting.get(Calendar.MONTH),
                        myCalendarharvesting.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/


        update_farm_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner asyncTaskRunner=new AsyncTaskRunner();
                asyncTaskRunner.execute();
            }
        });
    }

    private void updatesowingDateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        sowingDateet.setText(sdf.format(myCalendarsowing.getTime()));


        myCalendarflowering=myCalendarsowing;
        myCalendarflowering.add(Calendar.DATE,45);
        expFloweringDateet.setText(sdf.format(myCalendarflowering.getTime()));

        myCalendarharvesting=myCalendarsowing;
        myCalendarharvesting.add(Calendar.DATE,45);
        expHarvestingDateet.setText(sdf.format(myCalendarharvesting.getTime()));

        myCalendarsowing.add(Calendar.DATE,-90);
    }

   /* private void updatefloweringDateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        expFloweringDateet.setText(sdf.format(myCalendarflowering.getTime()));
    }

    private void updateHarvestingDateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        expHarvestingDateet.setText(sdf.format(myCalendarharvesting.getTime()));
    }
*/

    private class AsyncTaskRunner extends AsyncTask<String, Void, String> {
        public AsyncTaskRunner() {
            super();
        }

        @Override
        protected String doInBackground(String... params) {

            String submit_format = "yyyy-MM-dd"; //In which you need put here
            SimpleDateFormat submit_sdf = new SimpleDateFormat(submit_format, Locale.US);


            Date sowing_date_final = null,flowering_date_final=null,harvesting_date_final = null;
            try {
                sowing_date_final=new SimpleDateFormat("dd/MM/yyyy").parse(sowingDateet.getText().toString().trim());
                flowering_date_final=new SimpleDateFormat("dd/MM/yyyy").parse(expFloweringDateet.getText().toString().trim());
                harvesting_date_final=new SimpleDateFormat("dd/MM/yyyy").parse(expHarvestingDateet.getText().toString().trim());

            } catch (ParseException e) {
                e.printStackTrace();
            }

            String str_flowering_date=submit_sdf.format(flowering_date_final);
            String str_sowing_date=submit_sdf.format(sowing_date_final);
            String str_harvesting_date=submit_sdf.format(harvesting_date_final);



            ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
            FarmDetailsUpdateSendData farmDetailsUpdateSendData=new FarmDetailsUpdateSendData();
            farmDetailsUpdateSendData.setComp_id("1");
            farmDetailsUpdateSendData.setFarm_id("1");
            farmDetailsUpdateSendData.setExp_flowering_date(str_flowering_date);
            farmDetailsUpdateSendData.setIrrigation_source(spinner_irri_source.getSelectedItem().toString());
            farmDetailsUpdateSendData.setIrrigation_type(spinner_irri_type.getSelectedItem().toString());
            farmDetailsUpdateSendData.setSoil_type(spinner_soil_type.getSelectedItem().toString());
            farmDetailsUpdateSendData.setPrevious_crop(spinner_perv_crop.getSelectedItem().toString());
            farmDetailsUpdateSendData.setSowing_date(str_sowing_date);
            farmDetailsUpdateSendData.setExp_harvest_date(str_harvesting_date);
            Call<StatusMsgModel> statusMsgModelCall=apiService.getMsgStatusForFarmDetailsUpdate(farmDetailsUpdateSendData);
            statusMsgModelCall.enqueue(new Callback<StatusMsgModel>(){

                @Override
                public void onResponse(Call<StatusMsgModel> call, Response<StatusMsgModel> response) {

                    StatusMsgModel statusMsgModel=response.body();
                    if(statusMsgModel.getStatus()==1) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Intent intent=new Intent(context,LandingActivity.class);
                        ActivityOptions options = null;
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
