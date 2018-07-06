package fcms.crptrls.i9930.croptrailsfcms.Login;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fcms.crptrls.i9930.croptrailsfcms.Landing.LandingActivity;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.ApiInterface;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.ModelRecieveData;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.ModelSendData;
import fcms.crptrls.i9930.croptrailsfcms.R;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.Result;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.RetrofitClientInstance;
import fcms.crptrls.i9930.croptrailsfcms.VerifyArea.MapsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    TextView mapid;
    Context context;
    ImageView cloudicon;

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
        context=this;
        mapid=(TextView)findViewById(R.id.map_id);
        cloudicon=(ImageView)findViewById(R.id.cloud_icon);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        cloudicon.startAnimation(animation);

        final Dialog dialog = new Dialog(this, R.style.PauseDialog);



        mapid.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {



// Setting the title and layout for the dialog
                /*dialog.setTitle("Hello");
                dialog.setContentView(R.layout.pause_menu);
                dialog.show();*/
                Intent intent=new Intent(context,LandingActivity.class);
                ActivityOptions options =
                        null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    context.startActivity(intent, options.toBundle());
                }else{
                    startActivity(intent);
                }
            }
        });
        ApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
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
/*
                Log.e("TAG",response.body().toString()+"        "+response.message()+"      "+response.code());
                ModelRecieveData modelRecieveData=response.body();

//                Toast.makeText(LoginActivity.this, modelRecieveData.getResult().toString(), Toast.LENGTH_LONG).show();
                List<Result> result=modelRecieveData.getResult();

                String[] activity=new String[result.size()];

                for(int i=0;i<result.size();i++){
                    activity[i]=result.get(i).getActivity();
                    Log.e("TAG",activity[i]);
                }
*/


            }

            @Override
            public void onFailure(Call<ModelRecieveData> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Some Error", Toast.LENGTH_SHORT).show();
                Log.e("TAG",t.getMessage());
            }
        });

    }
}