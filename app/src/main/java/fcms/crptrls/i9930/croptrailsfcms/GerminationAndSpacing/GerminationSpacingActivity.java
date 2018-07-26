package fcms.crptrls.i9930.croptrailsfcms.GerminationAndSpacing;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import fcms.crptrls.i9930.croptrailsfcms.DataHandler.DataHandler;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpApiInterface;
import fcms.crptrls.i9930.croptrailsfcms.GerminationAndSpacing.Model.SendGerminationSpacingData;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Models.FetchFarmData;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Models.FetchFarmSendData;
import fcms.crptrls.i9930.croptrailsfcms.R;
import fcms.crptrls.i9930.croptrailsfcms.SharedPref.SharedPreferencesMethod;
import fcms.crptrls.i9930.croptrailsfcms.StatusMsgModel.StatusMsgModel;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GerminationSpacingActivity extends AppCompatActivity {

    Toolbar mActionBarToolbar;

    //sample1
    @BindView(R.id.configuredArea_text_input_et1)
    EditText configured_area_et1;
    @BindView(R.id.plant_to_plant_et1)
    EditText plant_to_plant_spacing_et1;
    @BindView(R.id.row_to_row_et1)
    EditText row_to_row_spacing_et1;
    @BindView(R.id.actual_population_et1)
    EditText actual_population_et1;
    @BindView(R.id.idealPopulationtv1)
    TextView ideal_population_tv1;
    @BindView(R.id.germination_text_input_tv1)
    TextView germiantion_tv1;
    @BindView(R.id.full_population_tv1)
    TextView full_popu_tv1;

    //sample2
    @BindView(R.id.configuredArea_text_input_et2)
    EditText configured_area_et2;
    @BindView(R.id.plant_to_plant_et2)
    EditText plant_to_plant_spacing_et2;
    @BindView(R.id.row_to_row_et2)
    EditText row_to_row_spacing_et2;
    @BindView(R.id.actual_population_et2)
    EditText actual_population_et2;
    @BindView(R.id.idealPopulationtv2)
    TextView ideal_population_tv2;
    @BindView(R.id.germination_text_input_tv2)
    TextView germiantion_tv2;
    @BindView(R.id.full_population_tv2)
    TextView full_popu_tv2;


    //sample3
    @BindView(R.id.configuredArea_text_input_et3)
    EditText configured_area_et3;
    @BindView(R.id.plant_to_plant_et3)
    EditText plant_to_plant_spacing_et3;
    @BindView(R.id.row_to_row_et3)
    EditText row_to_row_spacing_et3;
    @BindView(R.id.actual_population_et3)
    EditText actual_population_et3;
    @BindView(R.id.idealPopulationtv3)
    TextView ideal_population_tv3;
    @BindView(R.id.germination_text_input_tv3)
    TextView germiantion_tv3;
    @BindView(R.id.full_population_tv3)
    TextView full_popu_tv3;


    //sample4
    @BindView(R.id.configuredArea_text_input_et4)
    EditText configured_area_et4;
    @BindView(R.id.plant_to_plant_et4)
    EditText plant_to_plant_spacing_et4;
    @BindView(R.id.row_to_row_et4)
    EditText row_to_row_spacing_et4;
    @BindView(R.id.actual_population_et4)
    EditText actual_population_et4;
    @BindView(R.id.idealPopulationtv4)
    TextView ideal_population_tv4;
    @BindView(R.id.germination_text_input_tv4)
    TextView germiantion_tv4;
    @BindView(R.id.full_population_tv4)
    TextView full_popu_tv4;


    //sample5
    @BindView(R.id.configuredArea_text_input_et5)
    EditText configured_area_et5;
    @BindView(R.id.plant_to_plant_et5)
    EditText plant_to_plant_spacing_et5;
    @BindView(R.id.row_to_row_et5)
    EditText row_to_row_spacing_et5;
    @BindView(R.id.actual_population_et5)
    EditText actual_population_et5;
    @BindView(R.id.idealPopulationtv5)
    TextView ideal_population_tv5;
    @BindView(R.id.germination_text_input_tv5)
    TextView germiantion_tv5;
    @BindView(R.id.full_population_tv5)
    TextView full_popu_tv5;

    @BindView(R.id.add_more_img_butt)
    ImageView add_more_img;

    @BindView(R.id.hidden_linear_germi)
    LinearLayout hidden_lin_germi;

    @BindView(R.id.germi_full_layout)
    RelativeLayout germi_linear_layout;
    @BindView(R.id.submit_germi_data)
    TextView submit_germi_data;

    @BindView(R.id.avg_farm_popu_tv)
    TextView avg_farm_popu_tv;
    @BindView(R.id.avg_pp_spacing_tv)
    TextView avg_pp_spacing_tv;
    @BindView(R.id.avg_rr_spacing_tv)
    TextView avg_rr_spacing_tv;
    @BindView(R.id.avg_germination_tv)
    TextView avg_germi_tv;

    double flt_row_to_row_spacing1, flt_plant_to_plant_spacing1, flt_configured_area1;
    double flt_row_to_row_spacing2, flt_plant_to_plant_spacing2, flt_configured_area2;
    double flt_row_to_row_spacing3, flt_plant_to_plant_spacing3, flt_configured_area3;
    double flt_row_to_row_spacing4, flt_plant_to_plant_spacing4, flt_configured_area4;
    double flt_row_to_row_spacing5, flt_plant_to_plant_spacing5, flt_configured_area5;
    
    double actual_area=0;

    int int_actual_population1;
    int int_actual_population2;
    int int_actual_population3;
    int int_actual_population4;
    int int_actual_population5;

    Context context;


    public boolean onOptionsItemSelected(MenuItem item) {
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_germination_spacing);
        context = this;
        ButterKnife.bind(this);

        if(DataHandler.newInstance().getFetchFarmResult().getActualArea()!=null) {
            actual_area=Double.parseDouble(DataHandler.newInstance().getFetchFarmResult().getActualArea().toString());
        }
        TextView title = (TextView) findViewById(R.id.tittle);
        title.setText("Germination and Spacing");
        mActionBarToolbar = (Toolbar) findViewById(R.id.confirm_order_toolbar_layout);
        setSupportActionBar(mActionBarToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    CalculationforSample1();
        CalculationforSample2();
        CalculationforSample3();
        CalculationforSample4();
        CalculationforSample5();
        Add_more_sample();
        submit_germi_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckValidations();
            }
        });
    }

    private void CalculationforSample1() {
        configured_area_et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (!plant_to_plant_spacing_et1.getText().toString().equals("") && !row_to_row_spacing_et1.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing1 = Double.parseDouble(plant_to_plant_spacing_et1.getText().toString());
                    flt_row_to_row_spacing1 = Double.parseDouble(row_to_row_spacing_et1.getText().toString());
                    flt_configured_area1 = Double.parseDouble(s.toString());
                    double ideal_area = (flt_configured_area1 * 144) / (flt_plant_to_plant_spacing1 * flt_row_to_row_spacing1);
                    ideal_population_tv1.setText(String.valueOf(String.format("%.2f", ideal_area)));

                    if (!actual_population_et1.getText().toString().equals("")) {
                        int_actual_population1 = Integer.parseInt(actual_population_et1.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv1.getText().toString().trim());
                        double germination = int_actual_population1 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination *actual_area/ 100) / (flt_row_to_row_spacing1 * flt_plant_to_plant_spacing1);
                        germiantion_tv1.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv1.setText(String.valueOf(String.format("%.2f", full_population)));

                        rtoravgCheck();
                        ptopavgCheck();
                        avgGermiCheck();
                        avgFarmPopuCheck();
                    } else {
                        germiantion_tv1.setText(String.valueOf("0"));
                        full_popu_tv1.setText(String.valueOf("0"));

                    }

                } else {
                    ideal_population_tv1.setText("0");
                    germiantion_tv1.setText(String.valueOf("0"));
                    full_popu_tv1.setText(String.valueOf("0"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        row_to_row_spacing_et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et1.getText().toString().equals("") && !plant_to_plant_spacing_et1.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing1 = Double.parseDouble(plant_to_plant_spacing_et1.getText().toString());
                    flt_row_to_row_spacing1 = Double.parseDouble(s.toString());
                    flt_configured_area1 = Double.parseDouble(configured_area_et1.getText().toString());

                    double ideal_area = (flt_configured_area1 * 144) / (flt_plant_to_plant_spacing1 * flt_row_to_row_spacing1);
                    ideal_population_tv1.setText(String.valueOf(String.format("%.2f", ideal_area)));

                    if (!actual_population_et1.getText().toString().equals("")) {
                        int_actual_population1 = Integer.parseInt(actual_population_et1.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv1.getText().toString().trim());
                        double germination = int_actual_population1 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination * actual_area/ 100) / (flt_row_to_row_spacing1 * flt_plant_to_plant_spacing1);
                        germiantion_tv1.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv1.setText(String.valueOf((int)Math.round(full_population)/*String.format("%.2f", full_population))*/));

                    } else {
                        germiantion_tv1.setText(String.valueOf("0"));
                        full_popu_tv1.setText(String.valueOf("0"));
                    }


                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();
                } else {
                    ideal_population_tv1.setText("0");
                    germiantion_tv1.setText(String.valueOf("0"));
                    full_popu_tv1.setText(String.valueOf("0"));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        plant_to_plant_spacing_et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et1.getText().toString().equals("") && !row_to_row_spacing_et1.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing1 = Double.parseDouble(s.toString().trim());
                    flt_row_to_row_spacing1 = Double.parseDouble(row_to_row_spacing_et1.getText().toString());
                    flt_configured_area1 = Double.parseDouble(configured_area_et1.getText().toString());
                    double ideal_area = (flt_configured_area1 * 144) / (flt_plant_to_plant_spacing1 * flt_row_to_row_spacing1);
                    ideal_population_tv1.setText(String.valueOf(String.format("%.2f", ideal_area)));


                    if (!actual_population_et1.getText().toString().equals("")) {
                        int_actual_population1 = Integer.parseInt(actual_population_et1.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv1.getText().toString().trim());
                        double germination = int_actual_population1 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination * actual_area/ 100) / (flt_row_to_row_spacing1 * flt_plant_to_plant_spacing1);
                        germiantion_tv1.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv1.setText(String.valueOf(String.format("%.2f", full_population)));

                    } else {
                        germiantion_tv1.setText(String.valueOf("0"));
                        full_popu_tv1.setText(String.valueOf("0"));
                    }
                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();
                } else {
                    ideal_population_tv1.setText("0");
                    germiantion_tv1.setText(String.valueOf("0"));
                    full_popu_tv1.setText(String.valueOf("0"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actual_population_et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et1.getText().toString().equals("") && !row_to_row_spacing_et1.getText().toString().equals("") && s.length() > 0 && !plant_to_plant_spacing_et1.getText().toString().equals("") && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing1 = Double.parseDouble(plant_to_plant_spacing_et1.getText().toString());
                    flt_row_to_row_spacing1 = Double.parseDouble(row_to_row_spacing_et1.getText().toString());
                    flt_configured_area1 = Double.parseDouble(configured_area_et1.getText().toString());


                    int_actual_population1 = Integer.parseInt(s.toString().trim());
                    double ideal_population_from_tv = Double.parseDouble(ideal_population_tv1.getText().toString().trim());
                    double germination = int_actual_population1 / ideal_population_from_tv * 100.0;
                    double full_population = (43560.0 * 144 * germination * actual_area/ 100) / (flt_row_to_row_spacing1 * flt_plant_to_plant_spacing1);

                    germiantion_tv1.setText(String.valueOf(String.format("%.2f", germination)));
                    full_popu_tv1.setText(String.valueOf(String.format("%.2f", full_population)));

                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();

                } else {
                    germiantion_tv1.setText(String.valueOf("0"));
                    full_popu_tv1.setText(String.valueOf("0"));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void CalculationforSample2() {
        configured_area_et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (!plant_to_plant_spacing_et2.getText().toString().equals("") && !row_to_row_spacing_et2.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing2 = Double.parseDouble(plant_to_plant_spacing_et2.getText().toString());
                    flt_row_to_row_spacing2 = Double.parseDouble(row_to_row_spacing_et2.getText().toString());
                    flt_configured_area2 = Double.parseDouble(s.toString());
                    double ideal_area = (flt_configured_area2 * 144) / (flt_plant_to_plant_spacing2 * flt_row_to_row_spacing2);
                    ideal_population_tv2.setText(String.valueOf(String.format("%.2f", ideal_area)));

                    if (!actual_population_et2.getText().toString().equals("")) {
                        int_actual_population2 = Integer.parseInt(actual_population_et2.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv2.getText().toString().trim());
                        double germination = int_actual_population2 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination * actual_area/ 100) / (flt_row_to_row_spacing2 * flt_plant_to_plant_spacing2);
                        germiantion_tv2.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv2.setText(String.valueOf(String.format("%.2f", full_population)));
                    } else {
                        germiantion_tv2.setText(String.valueOf("0"));
                        full_popu_tv2.setText(String.valueOf("0"));
                    }

                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();

                } else {
                    ideal_population_tv2.setText("0");
                    germiantion_tv2.setText(String.valueOf("0"));
                    full_popu_tv2.setText(String.valueOf("0"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        row_to_row_spacing_et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et2.getText().toString().equals("") && !plant_to_plant_spacing_et2.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing2 = Double.parseDouble(plant_to_plant_spacing_et2.getText().toString());
                    flt_row_to_row_spacing2 = Double.parseDouble(s.toString());
                    flt_configured_area2 = Double.parseDouble(configured_area_et2.getText().toString());

                    double ideal_area = (flt_configured_area2 * 144) / (flt_plant_to_plant_spacing2 * flt_row_to_row_spacing2);
                    ideal_population_tv2.setText(String.valueOf(String.format("%.2f", ideal_area)));

                    if (!actual_population_et2.getText().toString().equals("")) {
                        int_actual_population2 = Integer.parseInt(actual_population_et2.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv2.getText().toString().trim());
                        double germination = int_actual_population2 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing2 * flt_plant_to_plant_spacing2);
                        germiantion_tv2.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv2.setText(String.valueOf(String.format("%.2f", full_population)));

                    } else {
                        germiantion_tv2.setText(String.valueOf("0"));
                        full_popu_tv2.setText(String.valueOf("0"));
                    }
                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();
                } else {
                    ideal_population_tv2.setText("0");
                    germiantion_tv2.setText(String.valueOf("0"));
                    full_popu_tv2.setText(String.valueOf("0"));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        plant_to_plant_spacing_et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et2.getText().toString().equals("") && !row_to_row_spacing_et2.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing2 = Double.parseDouble(s.toString().trim());
                    flt_row_to_row_spacing2 = Double.parseDouble(row_to_row_spacing_et2.getText().toString());
                    flt_configured_area2 = Double.parseDouble(configured_area_et2.getText().toString());
                    double ideal_area = (flt_configured_area2 * 144) / (flt_plant_to_plant_spacing2 * flt_row_to_row_spacing2);
                    ideal_population_tv2.setText(String.valueOf(String.format("%.2f", ideal_area)));


                    if (!actual_population_et2.getText().toString().equals("")) {
                        int_actual_population2 = Integer.parseInt(actual_population_et2.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv2.getText().toString().trim());
                        double germination = int_actual_population2 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing2 * flt_plant_to_plant_spacing2);
                        germiantion_tv2.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv2.setText(String.valueOf(String.format("%.2f", full_population)));

                    } else {
                        germiantion_tv2.setText(String.valueOf("0"));
                        full_popu_tv2.setText(String.valueOf("0"));
                    }
                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();
                } else {
                    ideal_population_tv2.setText("0");
                    germiantion_tv2.setText(String.valueOf("0"));
                    full_popu_tv2.setText(String.valueOf("0"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actual_population_et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et2.getText().toString().equals("") && !row_to_row_spacing_et2.getText().toString().equals("") && s.length() > 0 && !plant_to_plant_spacing_et2.getText().toString().equals("") && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing2 = Double.parseDouble(plant_to_plant_spacing_et2.getText().toString());
                    flt_row_to_row_spacing2 = Double.parseDouble(row_to_row_spacing_et2.getText().toString());
                    flt_configured_area2 = Double.parseDouble(configured_area_et2.getText().toString());


                    int_actual_population2 = Integer.parseInt(s.toString().trim());
                    double ideal_population_from_tv = Double.parseDouble(ideal_population_tv2.getText().toString().trim());
                    double germination = int_actual_population2 / ideal_population_from_tv * 100.0;
                    double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing2 * flt_plant_to_plant_spacing2);

                    germiantion_tv2.setText(String.valueOf(String.format("%.2f", germination)));
                    full_popu_tv2.setText(String.valueOf(String.format("%.2f", full_population)));
                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();

                } else {
                    germiantion_tv2.setText(String.valueOf("0"));
                    full_popu_tv2.setText(String.valueOf("0"));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void CalculationforSample3() {
        configured_area_et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (!plant_to_plant_spacing_et3.getText().toString().equals("") && !row_to_row_spacing_et3.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing3 = Double.parseDouble(plant_to_plant_spacing_et3.getText().toString());
                    flt_row_to_row_spacing3 = Double.parseDouble(row_to_row_spacing_et3.getText().toString());
                    flt_configured_area3 = Double.parseDouble(s.toString());
                    double ideal_area = (flt_configured_area3 * 144) / (flt_plant_to_plant_spacing3 * flt_row_to_row_spacing3);
                    ideal_population_tv3.setText(String.valueOf(String.format("%.2f", ideal_area)));

                    if (!actual_population_et3.getText().toString().equals("")) {
                        int_actual_population3 = Integer.parseInt(actual_population_et3.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv3.getText().toString().trim());
                        double germination = int_actual_population3 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing3 * flt_plant_to_plant_spacing3);
                        germiantion_tv3.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv3.setText(String.valueOf(String.format("%.2f", full_population)));
                    } else {
                        germiantion_tv3.setText(String.valueOf("0"));
                        full_popu_tv3.setText(String.valueOf("0"));
                    }

                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();
                } else {
                    ideal_population_tv3.setText("0");
                    germiantion_tv3.setText(String.valueOf("0"));
                    full_popu_tv3.setText(String.valueOf("0"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        row_to_row_spacing_et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et3.getText().toString().equals("") && !plant_to_plant_spacing_et3.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing3 = Double.parseDouble(plant_to_plant_spacing_et3.getText().toString());
                    flt_row_to_row_spacing3 = Double.parseDouble(s.toString());
                    flt_configured_area3 = Double.parseDouble(configured_area_et3.getText().toString());

                    double ideal_area = (flt_configured_area3 * 144) / (flt_plant_to_plant_spacing3 * flt_row_to_row_spacing3);
                    ideal_population_tv3.setText(String.valueOf(String.format("%.2f", ideal_area)));

                    if (!actual_population_et3.getText().toString().equals("")) {
                        int_actual_population3 = Integer.parseInt(actual_population_et3.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv3.getText().toString().trim());
                        double germination = int_actual_population3 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing3 * flt_plant_to_plant_spacing3);
                        germiantion_tv3.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv3.setText(String.valueOf(String.format("%.2f", full_population)));

                    } else {
                        germiantion_tv3.setText(String.valueOf("0"));
                        full_popu_tv3.setText(String.valueOf("0"));
                    }
                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();
                } else {
                    ideal_population_tv3.setText("0");
                    germiantion_tv3.setText(String.valueOf("0"));
                    full_popu_tv3.setText(String.valueOf("0"));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        plant_to_plant_spacing_et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et3.getText().toString().equals("") && !row_to_row_spacing_et3.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing3 = Double.parseDouble(s.toString().trim());
                    flt_row_to_row_spacing3 = Double.parseDouble(row_to_row_spacing_et3.getText().toString());
                    flt_configured_area3 = Double.parseDouble(configured_area_et3.getText().toString());
                    double ideal_area = (flt_configured_area3 * 144) / (flt_plant_to_plant_spacing3 * flt_row_to_row_spacing3);
                    ideal_population_tv3.setText(String.valueOf(String.format("%.2f", ideal_area)));


                    if (!actual_population_et3.getText().toString().equals("")) {
                        int_actual_population3 = Integer.parseInt(actual_population_et3.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv3.getText().toString().trim());
                        double germination = int_actual_population3 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing3 * flt_plant_to_plant_spacing3);
                        germiantion_tv3.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv3.setText(String.valueOf(String.format("%.2f", full_population)));

                    } else {
                        germiantion_tv3.setText(String.valueOf("0"));
                        full_popu_tv3.setText(String.valueOf("0"));
                    }

                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();
                } else {
                    ideal_population_tv3.setText("0");
                    germiantion_tv3.setText(String.valueOf("0"));
                    full_popu_tv3.setText(String.valueOf("0"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actual_population_et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et3.getText().toString().equals("") && !row_to_row_spacing_et3.getText().toString().equals("") && s.length() > 0 && !plant_to_plant_spacing_et3.getText().toString().equals("") && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing3 = Double.parseDouble(plant_to_plant_spacing_et3.getText().toString());
                    flt_row_to_row_spacing3 = Double.parseDouble(row_to_row_spacing_et3.getText().toString());
                    flt_configured_area3 = Double.parseDouble(configured_area_et3.getText().toString());


                    int_actual_population3 = Integer.parseInt(s.toString().trim());
                    double ideal_population_from_tv = Double.parseDouble(ideal_population_tv3.getText().toString().trim());
                    double germination = int_actual_population3 / ideal_population_from_tv * 100.0;
                    double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing3 * flt_plant_to_plant_spacing3);

                    germiantion_tv3.setText(String.valueOf(String.format("%.2f", germination)));
                    full_popu_tv3.setText(String.valueOf(String.format("%.2f", full_population)));

                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();
                } else {
                    germiantion_tv3.setText(String.valueOf("0"));
                    full_popu_tv3.setText(String.valueOf("0"));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void CalculationforSample4() {
        configured_area_et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (!plant_to_plant_spacing_et4.getText().toString().equals("") && !row_to_row_spacing_et4.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing4 = Double.parseDouble(plant_to_plant_spacing_et4.getText().toString());
                    flt_row_to_row_spacing4 = Double.parseDouble(row_to_row_spacing_et4.getText().toString());
                    flt_configured_area4 = Double.parseDouble(s.toString());
                    double ideal_area = (flt_configured_area4 * 144) / (flt_plant_to_plant_spacing4 * flt_row_to_row_spacing4);
                    ideal_population_tv4.setText(String.valueOf(String.format("%.2f", ideal_area)));

                    if (!actual_population_et4.getText().toString().equals("")) {
                        int_actual_population4 = Integer.parseInt(actual_population_et4.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv4.getText().toString().trim());
                        double germination = int_actual_population4 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing4 * flt_plant_to_plant_spacing4);
                        germiantion_tv4.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv4.setText(String.valueOf(String.format("%.2f", full_population)));
                    } else {
                        germiantion_tv4.setText(String.valueOf("0"));
                        full_popu_tv4.setText(String.valueOf("0"));
                    }
                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();

                } else {
                    ideal_population_tv4.setText("0");
                    germiantion_tv4.setText(String.valueOf("0"));
                    full_popu_tv4.setText(String.valueOf("0"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        row_to_row_spacing_et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et4.getText().toString().equals("") && !plant_to_plant_spacing_et4.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing4 = Double.parseDouble(plant_to_plant_spacing_et4.getText().toString());
                    flt_row_to_row_spacing4 = Double.parseDouble(s.toString());
                    flt_configured_area4 = Double.parseDouble(configured_area_et4.getText().toString());

                    double ideal_area = (flt_configured_area4 * 144) / (flt_plant_to_plant_spacing4 * flt_row_to_row_spacing4);
                    ideal_population_tv4.setText(String.valueOf(String.format("%.2f", ideal_area)));

                    if (!actual_population_et4.getText().toString().equals("")) {
                        int_actual_population4 = Integer.parseInt(actual_population_et4.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv4.getText().toString().trim());
                        double germination = int_actual_population4 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing4 * flt_plant_to_plant_spacing4);
                        germiantion_tv4.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv4.setText(String.valueOf(String.format("%.2f", full_population)));

                    } else {
                        germiantion_tv4.setText(String.valueOf("0"));
                        full_popu_tv4.setText(String.valueOf("0"));
                    }

                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();
                } else {
                    ideal_population_tv4.setText("0");
                    germiantion_tv4.setText(String.valueOf("0"));
                    full_popu_tv4.setText(String.valueOf("0"));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        plant_to_plant_spacing_et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et4.getText().toString().equals("") && !row_to_row_spacing_et4.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing4 = Double.parseDouble(s.toString().trim());
                    flt_row_to_row_spacing4 = Double.parseDouble(row_to_row_spacing_et4.getText().toString());
                    flt_configured_area4 = Double.parseDouble(configured_area_et4.getText().toString());
                    double ideal_area = (flt_configured_area4 * 144) / (flt_plant_to_plant_spacing4 * flt_row_to_row_spacing4);
                    ideal_population_tv4.setText(String.valueOf(String.format("%.2f", ideal_area)));


                    if (!actual_population_et4.getText().toString().equals("")) {
                        int_actual_population4 = Integer.parseInt(actual_population_et4.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv4.getText().toString().trim());
                        double germination = int_actual_population4 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing4 * flt_plant_to_plant_spacing4);
                        germiantion_tv4.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv4.setText(String.valueOf(String.format("%.2f", full_population)));

                    } else {
                        germiantion_tv4.setText(String.valueOf("0"));
                        full_popu_tv4.setText(String.valueOf("0"));
                    }

                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();
                } else {
                    ideal_population_tv4.setText("0");
                    germiantion_tv4.setText(String.valueOf("0"));
                    full_popu_tv4.setText(String.valueOf("0"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actual_population_et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et4.getText().toString().equals("") && !row_to_row_spacing_et4.getText().toString().equals("") && s.length() > 0 && !plant_to_plant_spacing_et4.getText().toString().equals("") && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing4 = Double.parseDouble(plant_to_plant_spacing_et4.getText().toString());
                    flt_row_to_row_spacing4 = Double.parseDouble(row_to_row_spacing_et4.getText().toString());
                    flt_configured_area4 = Double.parseDouble(configured_area_et4.getText().toString());


                    int_actual_population4 = Integer.parseInt(s.toString().trim());
                    double ideal_population_from_tv = Double.parseDouble(ideal_population_tv4.getText().toString().trim());
                    double germination = int_actual_population4 / ideal_population_from_tv * 100.0;
                    double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing4 * flt_plant_to_plant_spacing4);

                    germiantion_tv4.setText(String.valueOf(String.format("%.2f", germination)));
                    full_popu_tv4.setText(String.valueOf(String.format("%.2f", full_population)));

                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();
                } else {
                    germiantion_tv4.setText(String.valueOf("0"));
                    full_popu_tv4.setText(String.valueOf("0"));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void CalculationforSample5() {
        configured_area_et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (!plant_to_plant_spacing_et5.getText().toString().equals("") && !row_to_row_spacing_et5.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing5 = Double.parseDouble(plant_to_plant_spacing_et5.getText().toString());
                    flt_row_to_row_spacing5 = Double.parseDouble(row_to_row_spacing_et5.getText().toString());
                    flt_configured_area5 = Double.parseDouble(s.toString());
                    double ideal_area = (flt_configured_area5 * 144) / (flt_plant_to_plant_spacing5 * flt_row_to_row_spacing5);
                    ideal_population_tv5.setText(String.valueOf(String.format("%.2f", ideal_area)));

                    if (!actual_population_et5.getText().toString().equals("")) {
                        int_actual_population5 = Integer.parseInt(actual_population_et5.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv5.getText().toString().trim());
                        double germination = int_actual_population5 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing5 * flt_plant_to_plant_spacing5);
                        germiantion_tv5.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv5.setText(String.valueOf(String.format("%.2f", full_population)));
                    } else {
                        germiantion_tv5.setText(String.valueOf("0"));
                        full_popu_tv5.setText(String.valueOf("0"));
                    }

                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();

                } else {
                    ideal_population_tv5.setText("0");
                    germiantion_tv5.setText(String.valueOf("0"));
                    full_popu_tv5.setText(String.valueOf("0"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        row_to_row_spacing_et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et5.getText().toString().equals("") && !plant_to_plant_spacing_et5.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing5 = Double.parseDouble(plant_to_plant_spacing_et5.getText().toString());
                    flt_row_to_row_spacing5 = Double.parseDouble(s.toString());
                    flt_configured_area5 = Double.parseDouble(configured_area_et5.getText().toString());

                    double ideal_area = (flt_configured_area5 * 144) / (flt_plant_to_plant_spacing5 * flt_row_to_row_spacing5);
                    ideal_population_tv5.setText(String.valueOf(String.format("%.2f", ideal_area)));

                    if (!actual_population_et5.getText().toString().equals("")) {
                        int_actual_population5 = Integer.parseInt(actual_population_et5.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv5.getText().toString().trim());
                        double germination = int_actual_population5 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing5 * flt_plant_to_plant_spacing5);
                        germiantion_tv5.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv5.setText(String.valueOf(String.format("%.2f", full_population)));

                    } else {
                        germiantion_tv5.setText(String.valueOf("0"));
                        full_popu_tv5.setText(String.valueOf("0"));
                    }

                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();
                } else {
                    ideal_population_tv5.setText("0");
                    germiantion_tv5.setText(String.valueOf("0"));
                    full_popu_tv5.setText(String.valueOf("0"));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        plant_to_plant_spacing_et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et5.getText().toString().equals("") && !row_to_row_spacing_et5.getText().toString().equals("") && s.length() > 0 && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing5 = Double.parseDouble(s.toString().trim());
                    flt_row_to_row_spacing5 = Double.parseDouble(row_to_row_spacing_et5.getText().toString());
                    flt_configured_area5 = Double.parseDouble(configured_area_et5.getText().toString());
                    double ideal_area = (flt_configured_area5 * 144) / (flt_plant_to_plant_spacing5 * flt_row_to_row_spacing5);
                    ideal_population_tv5.setText(String.valueOf(String.format("%.2f", ideal_area)));


                    if (!actual_population_et5.getText().toString().equals("")) {
                        int_actual_population5 = Integer.parseInt(actual_population_et5.getText().toString().trim());
                        double ideal_population_from_tv = Double.parseDouble(ideal_population_tv5.getText().toString().trim());
                        double germination = int_actual_population5 / ideal_population_from_tv * 100.0;
                        double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing5 * flt_plant_to_plant_spacing5);
                        germiantion_tv5.setText(String.valueOf(String.format("%.2f", germination)));
                        full_popu_tv5.setText(String.valueOf(String.format("%.2f", full_population)));

                    } else {
                        germiantion_tv5.setText(String.valueOf("0"));
                        full_popu_tv5.setText(String.valueOf("0"));
                    }
                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();

                } else {
                    ideal_population_tv5.setText("0");
                    germiantion_tv5.setText(String.valueOf("0"));
                    full_popu_tv5.setText(String.valueOf("0"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actual_population_et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!configured_area_et5.getText().toString().equals("") && !row_to_row_spacing_et5.getText().toString().equals("") && s.length() > 0 && !plant_to_plant_spacing_et5.getText().toString().equals("") && !s.toString().trim().equals(".")) {
                    flt_plant_to_plant_spacing5 = Double.parseDouble(plant_to_plant_spacing_et5.getText().toString());
                    flt_row_to_row_spacing5 = Double.parseDouble(row_to_row_spacing_et5.getText().toString());
                    flt_configured_area5 = Double.parseDouble(configured_area_et5.getText().toString());


                    int_actual_population5 = Integer.parseInt(s.toString().trim());
                    double ideal_population_from_tv = Double.parseDouble(ideal_population_tv5.getText().toString().trim());
                    double germination = int_actual_population5 / ideal_population_from_tv * 100.0;
                    double full_population = (43560.0 * 144 * germination *actual_area / 100) / (flt_row_to_row_spacing5 * flt_plant_to_plant_spacing5);

                    germiantion_tv5.setText(String.valueOf(String.format("%.2f", germination)));
                    full_popu_tv5.setText(String.valueOf(String.format("%.2f", full_population)));

                    rtoravgCheck();
                    ptopavgCheck();
                    avgGermiCheck();
                    avgFarmPopuCheck();

                } else {
                    germiantion_tv5.setText(String.valueOf("0"));
                    full_popu_tv5.setText(String.valueOf("0"));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void Add_more_sample() {
        add_more_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(hidden_lin_germi, "add_more");
            }
        });
    }

    private void CheckValidations() {

        if (configured_area_et1.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(germi_linear_layout, "Please enter configured area of sample 1", Snackbar.LENGTH_LONG);
            snackbar.show();
            configured_area_et1.setError("Invalid area");
            configured_area_et1.getParent().requestChildFocus(configured_area_et1, configured_area_et1);
        } else if (plant_to_plant_spacing_et1.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(germi_linear_layout, "Please enter plant to plant spacing of sample 1", Snackbar.LENGTH_LONG);
            snackbar.show();
            plant_to_plant_spacing_et1.setError("Invalid R-R spacing");
            plant_to_plant_spacing_et1.getParent().requestChildFocus(plant_to_plant_spacing_et1, plant_to_plant_spacing_et1);
        } else if (row_to_row_spacing_et1.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(germi_linear_layout, "Please enter row to row spacing of sample 1", Snackbar.LENGTH_LONG);
            snackbar.show();
            row_to_row_spacing_et1.setError("Invalid P-P spacing");
            row_to_row_spacing_et1.getParent().requestChildFocus(row_to_row_spacing_et1, row_to_row_spacing_et1);
        } else if (actual_population_et1.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(germi_linear_layout, "Please enter actual population of sample 1", Snackbar.LENGTH_LONG);
            snackbar.show();
            actual_population_et1.setError("Invalid population");
            actual_population_et1.getParent().requestChildFocus(actual_population_et1, actual_population_et1);
        } else if (configured_area_et2.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(germi_linear_layout, "Please enter configured area of sample 2", Snackbar.LENGTH_LONG);
            snackbar.show();
            configured_area_et2.setError("Invalid area");
            configured_area_et2.getParent().requestChildFocus(configured_area_et2, configured_area_et2);
        } else if (plant_to_plant_spacing_et2.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(germi_linear_layout, "Please enter plant to plant spacing of sample 2", Snackbar.LENGTH_LONG);
            snackbar.show();
            plant_to_plant_spacing_et2.setError("Invalid R-R spacing");
            plant_to_plant_spacing_et2.getParent().requestChildFocus(plant_to_plant_spacing_et2, plant_to_plant_spacing_et2);
        } else if (row_to_row_spacing_et2.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(germi_linear_layout, "Please enter row to row spacing of sample 2", Snackbar.LENGTH_LONG);
            snackbar.show();
            row_to_row_spacing_et2.setError("Invalid P-P spacing");
            row_to_row_spacing_et2.getParent().requestChildFocus(row_to_row_spacing_et2, row_to_row_spacing_et2);
        } else if (actual_population_et2.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(germi_linear_layout, "Please enter actual population of sample 2", Snackbar.LENGTH_LONG);
            snackbar.show();
            actual_population_et2.setError("Invalid population");
            actual_population_et2.getParent().requestChildFocus(actual_population_et2, actual_population_et2);
        } else if (configured_area_et3.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(germi_linear_layout, "Please enter configured area of sample 3", Snackbar.LENGTH_LONG);
            snackbar.show();
            configured_area_et3.setError("Invalid area");
            configured_area_et3.getParent().requestChildFocus(configured_area_et3, configured_area_et3);
        } else if (plant_to_plant_spacing_et3.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(germi_linear_layout, "Please enter plant to plant spacing of sample 3", Snackbar.LENGTH_LONG);
            snackbar.show();
            plant_to_plant_spacing_et3.setError("Invalid R-R spacing");
            plant_to_plant_spacing_et3.getParent().requestChildFocus(plant_to_plant_spacing_et3, plant_to_plant_spacing_et3);
        } else if (row_to_row_spacing_et3.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(germi_linear_layout, "Please enter row to row spacing of sample 3", Snackbar.LENGTH_LONG);
            snackbar.show();
            row_to_row_spacing_et3.setError("Invalid P-P spacing");
            row_to_row_spacing_et3.getParent().requestChildFocus(row_to_row_spacing_et3, row_to_row_spacing_et3);
        } else if (actual_population_et3.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(germi_linear_layout, "Please enter actual population of sample 3", Snackbar.LENGTH_LONG);
            snackbar.show();
            actual_population_et3.setError("Invalid population");
            actual_population_et3.getParent().requestChildFocus(actual_population_et3, actual_population_et3);
        } else if (configured_area_et4.getText().toString().equals("") && plant_to_plant_spacing_et4.getText().toString().equals("") && row_to_row_spacing_et4.getText().toString().equals("") && actual_population_et4.getText().toString().equals("")) {
            if ((!configured_area_et5.getText().toString().equals("") || !plant_to_plant_spacing_et5.getText().toString().equals("") || !row_to_row_spacing_et5.getText().toString().equals("") || !actual_population_et5.getText().toString().equals(""))) {
                Snackbar snackbar = Snackbar.make(germi_linear_layout, "First fill out the 4th sample", Snackbar.LENGTH_LONG);
                snackbar.show();
                configured_area_et4.getParent().requestChildFocus(configured_area_et4, configured_area_et4);

            } else {
                SubmitGermiAsyncTask submitGermiAsyncTask = new SubmitGermiAsyncTask();
                submitGermiAsyncTask.execute("with3");
                //Toast.makeText(this, "Done with 3 samples", Toast.LENGTH_SHORT).show();
            }
        } else if (!configured_area_et4.getText().toString().equals("") || !plant_to_plant_spacing_et4.getText().toString().equals("") || !row_to_row_spacing_et4.getText().toString().equals("") || !actual_population_et4.getText().toString().equals("")) {


            if (!configured_area_et4.getText().toString().equals("") && !plant_to_plant_spacing_et4.getText().toString().equals("") && !row_to_row_spacing_et4.getText().toString().equals("") && !actual_population_et4.getText().toString().equals("")) {

                if ((configured_area_et5.getText().toString().equals("") && plant_to_plant_spacing_et5.getText().toString().equals("") && row_to_row_spacing_et5.getText().toString().equals("") && actual_population_et5.getText().toString().equals(""))) {
                    //Toast.makeText(this, "Done with 4 samples", Toast.LENGTH_SHORT).show();
                    SubmitGermiAsyncTask submitGermiAsyncTask = new SubmitGermiAsyncTask();
                    submitGermiAsyncTask.execute("with4");
                } else if (((!configured_area_et5.getText().toString().equals("") && !plant_to_plant_spacing_et5.getText().toString().equals("") && !row_to_row_spacing_et5.getText().toString().equals("") && !actual_population_et5.getText().toString().equals("")))) {
                    //Toast.makeText(this, "Done with 5 samples", Toast.LENGTH_SHORT).show();
                    SubmitGermiAsyncTask submitGermiAsyncTask = new SubmitGermiAsyncTask();
                    submitGermiAsyncTask.execute("with5");
                } else {
                    Snackbar snackbar = Snackbar.make(germi_linear_layout, "fill all sample 5 fields", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    configured_area_et5.getParent().requestChildFocus(configured_area_et5, configured_area_et5);

                }
            } else {
                Snackbar snackbar = Snackbar.make(germi_linear_layout, "fill all sample 4 fields", Snackbar.LENGTH_LONG);
                snackbar.show();
                configured_area_et4.getParent().requestChildFocus(configured_area_et4, configured_area_et4);
            }


        }
           /* if (configured_area_et4.getText().toString().equals("")) {
                configured_area_et4.setError("Invalid area");
                configured_area_et4.getParent().requestChildFocus(configured_area_et4, configured_area_et4);
            } else if (plant_to_plant_spacing_et4.getText().toString().equals("")) {

                plant_to_plant_spacing_et4.setError("Invalid R-R spacing");
                plant_to_plant_spacing_et4.getParent().requestChildFocus(plant_to_plant_spacing_et4, plant_to_plant_spacing_et4);
            } else if (row_to_row_spacing_et4.getText().toString().equals("")) {

                row_to_row_spacing_et4.setError("Invalid P-P spacing");
                row_to_row_spacing_et4.getParent().requestChildFocus(row_to_row_spacing_et4, row_to_row_spacing_et4);
            } else if (actual_population_et4.getText().toString().equals("")) {

                actual_population_et4.setError("Invalid population");
                actual_population_et4.getParent().requestChildFocus(actual_population_et4, actual_population_et4);
            }*/
   /* else

    {
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }*/


    }


    public void animate(final LinearLayout linearLayout, String from_where) {

        //LinearLayout dialog   = (LinearLayout)findViewById(R.id.my_layout);
        if (from_where.equals("add_more")) {
            if (linearLayout.getVisibility() == View.GONE) {
                add_more_img.setBackgroundResource(R.drawable.ic_remove_circle_outline_dark_grey_24dp);
                linearLayout.setVisibility(LinearLayout.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
                animation.setDuration(1000);
                linearLayout.setAnimation(animation);
                linearLayout.animate();
                animation.start();
            } else {
                add_more_img.setBackgroundResource(R.drawable.ic_add_circle_outline_dark_grey_black_24dp);
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


    class SubmitGermiAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            String fromwhere = strings[0];

            String[] sample_configured_area_arr= new String[0];
            String[] sample_spacing_ptp_arr= new String[0];
            String[] sample_spacing_rtr_arr= new String[0];
            String[] sample_ideal_pop_arr= new String[0];
            String[] sample_actual_pop_arr = new String[0];
            String[] sample_actual_total_pop_arr= new String[0];
            if (fromwhere.equals("with3")) {
                sample_configured_area_arr = new String[]{configured_area_et1.getText().toString(), configured_area_et2.getText().toString(), configured_area_et3.getText().toString()};
                sample_spacing_ptp_arr = new String[]{plant_to_plant_spacing_et1.getText().toString(), plant_to_plant_spacing_et2.getText().toString(), plant_to_plant_spacing_et3.getText().toString()};
                sample_spacing_rtr_arr = new String[]{row_to_row_spacing_et1.getText().toString(), row_to_row_spacing_et2.getText().toString(), row_to_row_spacing_et3.getText().toString()};
                sample_ideal_pop_arr = new String[]{ideal_population_tv1.getText().toString(), ideal_population_tv2.getText().toString(), ideal_population_tv3.getText().toString()};
                sample_actual_pop_arr = new String[]{actual_population_et1.getText().toString(), actual_population_et2.getText().toString(), actual_population_et3.getText().toString()};
                sample_actual_total_pop_arr = new String[]{full_popu_tv1.getText().toString(), full_popu_tv2.getText().toString(), full_popu_tv3.getText().toString()};



            }

            else if (fromwhere.equals("with4")) {
                sample_configured_area_arr = new String[]{configured_area_et1.getText().toString(), configured_area_et2.getText().toString(),
                        configured_area_et3.getText().toString(),configured_area_et4.getText().toString()};
                sample_spacing_ptp_arr = new String[]{plant_to_plant_spacing_et1.getText().toString(), plant_to_plant_spacing_et2.getText().toString(),
                        plant_to_plant_spacing_et3.getText().toString(),plant_to_plant_spacing_et4.getText().toString()};
                sample_spacing_rtr_arr = new String[]{row_to_row_spacing_et1.getText().toString(), row_to_row_spacing_et2.getText().toString(),
                        row_to_row_spacing_et3.getText().toString(),row_to_row_spacing_et4.getText().toString()};
                sample_ideal_pop_arr = new String[]{ideal_population_tv1.getText().toString(), ideal_population_tv2.getText().toString(),
                        ideal_population_tv3.getText().toString(),ideal_population_tv4.getText().toString()};
                sample_actual_pop_arr = new String[]{actual_population_et1.getText().toString(), actual_population_et2.getText().toString(),
                        actual_population_et3.getText().toString(),actual_population_et4.getText().toString()};
                sample_actual_total_pop_arr = new String[]{full_popu_tv1.getText().toString(), full_popu_tv2.getText().toString(),
                        full_popu_tv3.getText().toString(),full_popu_tv4.getText().toString()};

            }

            else{
                sample_configured_area_arr = new String[]{configured_area_et1.getText().toString(), configured_area_et2.getText().toString(),
                        configured_area_et3.getText().toString(),configured_area_et4.getText().toString(),configured_area_et5.getText().toString()};
                sample_spacing_ptp_arr = new String[]{plant_to_plant_spacing_et1.getText().toString(), plant_to_plant_spacing_et2.getText().toString(),
                        plant_to_plant_spacing_et3.getText().toString(),plant_to_plant_spacing_et4.getText().toString(),plant_to_plant_spacing_et5.getText().toString()};
                sample_spacing_rtr_arr = new String[]{row_to_row_spacing_et1.getText().toString(), row_to_row_spacing_et2.getText().toString(),
                        row_to_row_spacing_et3.getText().toString(),row_to_row_spacing_et4.getText().toString(),row_to_row_spacing_et5.getText().toString()};
                sample_ideal_pop_arr = new String[]{ideal_population_tv1.getText().toString(), ideal_population_tv2.getText().toString(),
                        ideal_population_tv3.getText().toString(),ideal_population_tv4.getText().toString(),ideal_population_tv5.getText().toString()};
                sample_actual_pop_arr = new String[]{actual_population_et1.getText().toString(), actual_population_et2.getText().toString(),
                        actual_population_et3.getText().toString(),actual_population_et4.getText().toString(),actual_population_et5.getText().toString()};
                sample_actual_total_pop_arr = new String[]{full_popu_tv1.getText().toString(), full_popu_tv2.getText().toString(),
                        full_popu_tv3.getText().toString(),full_popu_tv4.getText().toString(),full_popu_tv5.getText().toString()};
            }

            for (int i = 0; i < sample_actual_pop_arr.length; i++) {
                Log.e("conf_area" + i, sample_configured_area_arr[i]);
                Log.e("spacing_ptp" + i, sample_spacing_ptp_arr[i]);
                Log.e("spacing_rtr" + i, sample_spacing_rtr_arr[i]);
                Log.e("sample_ideal_pop_arr" + i, sample_ideal_pop_arr[i]);
                Log.e("sample_actual_pop_arr" + i, sample_actual_pop_arr[i]);
                Log.e("sample_total_pop" + i, sample_actual_total_pop_arr[i]);
            }

            Log.e("avg_germi",avg_germi_tv.getText().toString());
                Log.e("avg_farm_popu",avg_farm_popu_tv.getText().toString());
                Log.e("avg_pp_spacing",avg_pp_spacing_tv.getText().toString());
                Log.e("avg_rr_spacing",avg_rr_spacing_tv.getText().toString());
                Log.e("Farm_id",SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVFARMID));
                Log.e("Used_id",SharedPreferencesMethod.getString(context,SharedPreferencesMethod.SVUSERID));

                ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
                SendGerminationSpacingData sendGerminationSpacingData = new SendGerminationSpacingData();
                //Log.e("Comp and cluster id",comp_id+"  "+cluster_id);
                sendGerminationSpacingData.setFarm_id(SharedPreferencesMethod.getString(context, SharedPreferencesMethod.SVFARMID));
                sendGerminationSpacingData.setActual_pop(sample_actual_pop_arr);
                sendGerminationSpacingData.setActual_total_population(sample_actual_total_pop_arr);
                sendGerminationSpacingData.setAdded_by(SharedPreferencesMethod.getString(context, SharedPreferencesMethod.SVUSERID));
                sendGerminationSpacingData.setSpacing_ptp(sample_spacing_ptp_arr);
                sendGerminationSpacingData.setSpacing_rtr(sample_spacing_rtr_arr);
                sendGerminationSpacingData.setConfigured_area(sample_configured_area_arr);
                sendGerminationSpacingData.setIdeal_pop(sample_ideal_pop_arr);
                sendGerminationSpacingData.setAvg_germination(String.valueOf(avg_germi_tv.getText().toString()));
                sendGerminationSpacingData.setAvg_population(avg_farm_popu_tv.getText().toString());
                sendGerminationSpacingData.setAvg_spacing_ptp(avg_pp_spacing_tv.getText().toString());
                sendGerminationSpacingData.setAvg_spacing_rtr(avg_rr_spacing_tv.getText().toString());
                Call<StatusMsgModel> statusMsgModelCall = apiService.getStatusMsgForGerminationAndSpacing(sendGerminationSpacingData);
                statusMsgModelCall.enqueue(new Callback<StatusMsgModel>() {
                    @Override
                    public void onResponse(Call<StatusMsgModel> call, Response<StatusMsgModel> response) {

                        StatusMsgModel statusMsgModel=response.body();
                        Toast.makeText(context, statusMsgModel.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<StatusMsgModel> call, Throwable t) {

                    }
                });



            return null;
        }
    }


    public void ptopavgCheck(){
        if(!plant_to_plant_spacing_et1.getText().toString().equals("") && !plant_to_plant_spacing_et2.getText().toString().equals("") && !plant_to_plant_spacing_et3.getText().toString().equals("") &&
                !plant_to_plant_spacing_et4.getText().toString().equals("") && !plant_to_plant_spacing_et5.getText().toString().equals("")){
            avg_pp_spacing_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(plant_to_plant_spacing_et1.getText().toString()) + Double.parseDouble(plant_to_plant_spacing_et2.getText().toString())+
                    Double.parseDouble(plant_to_plant_spacing_et3.getText().toString())+Double.parseDouble(plant_to_plant_spacing_et4.getText().toString())+
                    Double.parseDouble(plant_to_plant_spacing_et5.getText().toString()))/5)));
        }else  if(!plant_to_plant_spacing_et1.getText().toString().equals("") && !plant_to_plant_spacing_et2.getText().toString().equals("") && !plant_to_plant_spacing_et3.getText().toString().equals("") &&
                !plant_to_plant_spacing_et4.getText().toString().equals("")){
            avg_pp_spacing_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(plant_to_plant_spacing_et1.getText().toString()) + Double.parseDouble(plant_to_plant_spacing_et2.getText().toString())+
                    Double.parseDouble(plant_to_plant_spacing_et3.getText().toString())+Double.parseDouble(plant_to_plant_spacing_et4.getText().toString()))/4)));
        }else  if(!plant_to_plant_spacing_et1.getText().toString().equals("") && !plant_to_plant_spacing_et2.getText().toString().equals("") && !plant_to_plant_spacing_et3.getText().toString().equals("")
                ){
            avg_pp_spacing_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(plant_to_plant_spacing_et1.getText().toString()) + Double.parseDouble(plant_to_plant_spacing_et2.getText().toString())+
                    Double.parseDouble(plant_to_plant_spacing_et3.getText().toString()))/3)));
        }else if(!plant_to_plant_spacing_et1.getText().toString().equals("") && !plant_to_plant_spacing_et2.getText().toString().equals("")
                ){
            avg_pp_spacing_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(plant_to_plant_spacing_et1.getText().toString()) +
                    Double.parseDouble(plant_to_plant_spacing_et2.getText().toString()))/2)));
        }else{
            avg_pp_spacing_tv.setText(String.valueOf(String.format("%.2f",Double.parseDouble(plant_to_plant_spacing_et1.getText().toString()))));
        }
    }

    public void rtoravgCheck(){
        if(!row_to_row_spacing_et1.getText().toString().equals("") && !row_to_row_spacing_et2.getText().toString().equals("") && !row_to_row_spacing_et3.getText().toString().equals("") &&
                !row_to_row_spacing_et4.getText().toString().equals("") && !row_to_row_spacing_et5.getText().toString().equals("")){
            avg_rr_spacing_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(row_to_row_spacing_et1.getText().toString()) + Double.parseDouble(row_to_row_spacing_et2.getText().toString())+
                    Double.parseDouble(row_to_row_spacing_et3.getText().toString())+Double.parseDouble(row_to_row_spacing_et4.getText().toString())+
                    Double.parseDouble(row_to_row_spacing_et5.getText().toString()))/5)));
        }else  if(!row_to_row_spacing_et1.getText().toString().equals("") && !row_to_row_spacing_et2.getText().toString().equals("") && !row_to_row_spacing_et3.getText().toString().equals("") &&
                !row_to_row_spacing_et4.getText().toString().equals("")){
            avg_rr_spacing_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(row_to_row_spacing_et1.getText().toString()) + Double.parseDouble(row_to_row_spacing_et2.getText().toString())+
                    Double.parseDouble(row_to_row_spacing_et3.getText().toString())+Double.parseDouble(row_to_row_spacing_et4.getText().toString()))/4)));
        }else  if(!row_to_row_spacing_et1.getText().toString().equals("") && !row_to_row_spacing_et2.getText().toString().equals("") && !row_to_row_spacing_et3.getText().toString().equals("")
                ){
            avg_rr_spacing_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(row_to_row_spacing_et1.getText().toString()) + Double.parseDouble(row_to_row_spacing_et2.getText().toString())+
                    Double.parseDouble(row_to_row_spacing_et3.getText().toString()))/3)));
        }else if(!row_to_row_spacing_et1.getText().toString().equals("") && !row_to_row_spacing_et2.getText().toString().equals("")
                ){
            avg_rr_spacing_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(row_to_row_spacing_et1.getText().toString())
                    + Double.parseDouble(row_to_row_spacing_et2.getText().toString()))/2)));
        }else{
            avg_rr_spacing_tv.setText(String.valueOf(String.format("%.2f",Double.parseDouble(row_to_row_spacing_et1.getText().toString()))));
        }
    }

    public void avgGermiCheck(){
        if(!germiantion_tv1.getText().toString().equals("0") && !germiantion_tv2.getText().toString().equals("0") && !germiantion_tv3.getText().toString().equals("0") &&
                !germiantion_tv4.getText().toString().equals("0") && !germiantion_tv5.getText().toString().equals("0")){
            avg_germi_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(germiantion_tv1.getText().toString()) + Double.parseDouble(germiantion_tv2.getText().toString())+
                    Double.parseDouble(germiantion_tv3.getText().toString())+Double.parseDouble(germiantion_tv4.getText().toString())+
                    Double.parseDouble(germiantion_tv5.getText().toString()))/5)));
        }else  if(!germiantion_tv1.getText().toString().equals("0") && !germiantion_tv2.getText().toString().equals("0") && !germiantion_tv3.getText().toString().equals("0") &&
                !germiantion_tv4.getText().toString().equals("0")){
            avg_germi_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(germiantion_tv1.getText().toString()) + Double.parseDouble(germiantion_tv2.getText().toString())+
                    Double.parseDouble(germiantion_tv3.getText().toString())+Double.parseDouble(germiantion_tv4.getText().toString()))/4)));
        }else  if(!germiantion_tv1.getText().toString().equals("0") && !germiantion_tv2.getText().toString().equals("0") && !germiantion_tv3.getText().toString().equals("0")
                ){
            avg_germi_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(germiantion_tv1.getText().toString()) + Double.parseDouble(germiantion_tv2.getText().toString())+
                    Double.parseDouble(germiantion_tv3.getText().toString()))/3)));
        }else if(!germiantion_tv1.getText().toString().equals("0") && !germiantion_tv2.getText().toString().equals("0")
                ){
            avg_germi_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(germiantion_tv1.getText().toString()) + Double.parseDouble(germiantion_tv2.getText().toString()))/2)));
        }else{
            avg_germi_tv.setText(String.valueOf(String.format("%.2f",Double.parseDouble(germiantion_tv1.getText().toString()))));
        }
    }

    public void avgFarmPopuCheck(){
        if(!full_popu_tv1.getText().toString().equals("0") && !full_popu_tv2.getText().toString().equals("0") && !full_popu_tv3.getText().toString().equals("0") &&
                !full_popu_tv4.getText().toString().equals("0") && !full_popu_tv5.getText().toString().equals("0")){
            avg_farm_popu_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(full_popu_tv1.getText().toString()) + Double.parseDouble(full_popu_tv2.getText().toString())+
                    Double.parseDouble(full_popu_tv3.getText().toString())+Double.parseDouble(full_popu_tv4.getText().toString())+
                    Double.parseDouble(full_popu_tv5.getText().toString()))/5)));
        }else  if(!full_popu_tv1.getText().toString().equals("0") && !full_popu_tv2.getText().toString().equals("0") && !full_popu_tv3.getText().toString().equals("0") &&
                !full_popu_tv4.getText().toString().equals("0")){
            avg_farm_popu_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(full_popu_tv1.getText().toString()) + Double.parseDouble(full_popu_tv2.getText().toString())+
                    Double.parseDouble(full_popu_tv3.getText().toString())+Double.parseDouble(full_popu_tv4.getText().toString()))/4)));
        }else  if(!full_popu_tv1.getText().toString().equals("0") && !full_popu_tv2.getText().toString().equals("0") && !full_popu_tv3.getText().toString().equals("0")
                ){
            avg_farm_popu_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(full_popu_tv1.getText().toString()) + Double.parseDouble(full_popu_tv2.getText().toString())+
                    Double.parseDouble(full_popu_tv3.getText().toString()))/3)));
        }else if(!full_popu_tv1.getText().toString().equals("0") && !full_popu_tv2.getText().toString().equals("0")
                ){
            avg_farm_popu_tv.setText(String.valueOf(String.format("%.2f",(Double.parseDouble(full_popu_tv1.getText().toString()) + Double.parseDouble(full_popu_tv2.getText().toString()))/2)));
        }else{
            avg_farm_popu_tv.setText(String.valueOf(String.format("%.2f",Double.parseDouble(full_popu_tv1.getText().toString()))));
        }
    }

}
