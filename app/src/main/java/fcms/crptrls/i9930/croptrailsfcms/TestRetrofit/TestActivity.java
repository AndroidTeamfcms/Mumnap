package fcms.crptrls.i9930.croptrailsfcms.TestRetrofit;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import fcms.crptrls.i9930.croptrailsfcms.R;

public class TestActivity extends AppCompatActivity {

    LinearLayout layout;
    LinearLayout main_linear;
    int j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

          layout = (LinearLayout)findViewById(R.id.my_layout);
        Button button=(Button)findViewById(R.id.demo_button);
        main_linear=(LinearLayout)findViewById(R.id.main_linear);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //animate();

                addButton(j);
                j++;
               /* Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

                if (layout.getVisibility() == View.INVISIBLE) {

                    layout.startAnimation(slideDown);
                    layout.setVisibility(View.VISIBLE);
                  //  Toast.makeText(TestActivity.this, "Here but Not happening", Toast.LENGTH_SHORT).show();
                }
                else{
                   // Toast.makeText(TestActivity.this, "In else", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

    }

    private void addButton(int i) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity= Gravity.CENTER;
        params.topMargin=20;
        params.width=200;

        Button btn = new Button(this);
        btn.setId(i);
        final int id_ = btn.getId();
        btn.setText("Visit " + id_);
        btn.setBackgroundColor(Color.rgb(70, 80, 90));
        btn.setPadding(10,10,10,10);
        btn.setWidth(70);
        main_linear .addView(btn, params);
        btn = ((Button) findViewById(id_));
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public void animate(){

        LinearLayout dialog   = (LinearLayout)findViewById(R.id.my_layout);
        if (layout.getVisibility() == View.GONE) {
            dialog.setVisibility(LinearLayout.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
            animation.setDuration(500);
            dialog.setAnimation(animation);
            dialog.animate();
            animation.start();
        }else{
            dialog.setVisibility(LinearLayout.GONE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
            animation.setDuration(500);
            dialog.setAnimation(animation);
            dialog.animate();
            animation.start();
        }
    }
}
