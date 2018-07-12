package fcms.crptrls.i9930.croptrailsfcms.Splash;

import android.app.ActivityOptions;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import fcms.crptrls.i9930.croptrailsfcms.Landing.LandingActivity;
import fcms.crptrls.i9930.croptrailsfcms.Login.LoginActivity;
import fcms.crptrls.i9930.croptrailsfcms.R;
import fcms.crptrls.i9930.croptrailsfcms.SharedPref.SharedPreferencesMethod;

public class SplashActivity extends AppCompatActivity {

    private Thread mThread;
    private boolean isFinish = false;
    int t = 0;

    Context context;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    String languageset;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        context = this;
         imageView= (ImageView) findViewById(R.id.splash_logo);
        /*Glide.with(this).load(R.drawable.farmer).into(imageView);
        init();*/
        scheduleAlarm();
        StartAnimations();


    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    private void init() {
        context = this;
    }


    private Runnable mRunnable = new Runnable() {

        public void run() {
            mHandler.sendEmptyMessage(0);
        }
    };
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0 && (!isFinish)) {
                stop();
            }

            super.handleMessage(msg);
        }
    };



    @Override
    protected void onDestroy() {
        try {
            mThread.interrupt();
            mThread = null;

        } catch (Exception e) {
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                stop();
            }
        }, 3500);
        super.onResume();

    }

    @Override
    protected void onStop() {
        stop();
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }




    private void stop() {
        isFinish = true;
        if (t == 0) {
            if (SharedPreferencesMethod.getBoolean(context, SharedPreferencesMethod.SVLOGIN)) {
                Intent intent=new Intent(context,LandingActivity.class);
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
            }
            else{
                Intent intent=new Intent(context,LoginActivity.class);
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
            }
        }
    }

        @Override
        public void finish () {
            t = 1;
            super.finish();
        }

        @Override
        public boolean onTouchEvent (MotionEvent event){
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
               // stop();
            }
            return super.onTouchEvent(event);
        }

    private void StartAnimations() {
      /*  AnimationSet mAnimationSet = new AnimationSet(false);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        mAnimationSet.addAnimation(fadeInAnimation);
        mAnimationSet.addAnimation(fadeOutAnimation);
        imageView.startAnimation(mAnimationSet);*/

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.splash_animation);
        imageView.startAnimation(animation);

     /*   Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash_logo);
        iv.clearAnimation();
        iv.startAnimation(anim);*/
    }

    private void scheduleAlarm() {

    }

}
