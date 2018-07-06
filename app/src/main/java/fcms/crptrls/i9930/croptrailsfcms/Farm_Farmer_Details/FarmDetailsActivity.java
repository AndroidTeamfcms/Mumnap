package fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details;

import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import fcms.crptrls.i9930.croptrailsfcms.CustomTextView.CustomTextView;
import fcms.crptrls.i9930.croptrailsfcms.R;

public class FarmDetailsActivity extends AppCompatActivity {

    CustomTextView update_farm_details,sowing_details,add_visit;
    RelativeLayout rel_update_farm_details,rel_sowing_details,rel_add_visit;
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
        update_farm_details=(CustomTextView)findViewById(R.id.update_farmer_details);
        sowing_details=(CustomTextView)findViewById(R.id.sowing_details_card);
        add_visit=(CustomTextView)findViewById(R.id.add_visit);
        rel_update_farm_details=(RelativeLayout)findViewById(R.id.detail_full_layout);
        rel_sowing_details=(RelativeLayout)findViewById(R.id.sowing_full_layout_drop);
        rel_add_visit=(RelativeLayout)findViewById(R.id.rel_add_visit_drop);

        Onclick();

    }

    private void Onclick() {
        update_farm_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(rel_update_farm_details);
            }
        });

        sowing_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(rel_sowing_details);
            }
        });

        add_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(rel_add_visit);
            }
        });
    }


    public void animate(final RelativeLayout relativeLayout){

        //LinearLayout dialog   = (LinearLayout)findViewById(R.id.my_layout);
        if (relativeLayout.getVisibility() == View.GONE) {
            relativeLayout.setVisibility(LinearLayout.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
            animation.setDuration(1000);
            relativeLayout.setAnimation(animation);
            relativeLayout.animate();
            animation.start();
        }else{
            relativeLayout.setVisibility(LinearLayout.INVISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
            animation.setDuration(1000);
            relativeLayout.setAnimation(animation);
            relativeLayout.animate();
            animation.start();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
            relativeLayout.setVisibility(LinearLayout.GONE);
                }
            }, 1000);
        }
    }
}
