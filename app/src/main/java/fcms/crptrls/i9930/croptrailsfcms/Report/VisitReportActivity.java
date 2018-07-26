package fcms.crptrls.i9930.croptrailsfcms.Report;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fcms.crptrls.i9930.croptrailsfcms.DataHandler.DataHandler;
import fcms.crptrls.i9930.croptrailsfcms.R;
import fcms.crptrls.i9930.croptrailsfcms.Report.Model.ViewFarmResult;

public class VisitReportActivity extends AppCompatActivity {
    Toolbar mActionBarToolbar;
    TextView Other2,Thinning2,SML2,CPC2,IPMMaterial2,IPMQuantity2,IPMDate2,
            IWMQuantity2,IWMMaterial2,IWMDate2,INMQuantity2,INMMaterial2,
            INMDate2,Other1,SML1,CPC1,IPMQuantity1,IPMMaterial1,IPMDate1,
            IWMQuantity1,IWMMaterial1,IWMDate1,INMQuantity1,INMMaterial1,
            INMDate1;
    String prop;
    ImageView i0,i1,i2,i3,i4,i5;
    String img="i";
    LinearLayout sixthLinear,fifthLinear,fourthLinear;
    TextView visitNumber;

    public Integer imgArr[]=  {


    };
    List<ViewFarmResult> viewFarmResults;

    public boolean onOptionsItemSelected(MenuItem item) {
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
        setContentView(R.layout.activity_visit_report);


        TextView title = (TextView) findViewById(R.id.tittle);
        title.setText("Farm Visit Report");
        mActionBarToolbar = (Toolbar) findViewById(R.id.confirm_order_toolbar_layout);
        setSupportActionBar(mActionBarToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


        IPMDate1=(TextView)findViewById(R.id.IPMDate1);
        IPMMaterial1=(TextView)findViewById(R.id.IPMMaterial1);
        IPMQuantity1=(TextView)findViewById(R.id.IPMQuantity1);
        INMDate1=(TextView)findViewById(R.id.INMDate1);
        INMMaterial1=(TextView)findViewById(R.id.INMMaterial1);
        INMQuantity1=(TextView)findViewById(R.id.INMQuantity1);
        IWMDate1=(TextView)findViewById(R.id.IWMDate1);
        IWMQuantity1=(TextView)findViewById(R.id.IWMQuantity1);
        IWMMaterial1=(TextView)findViewById(R.id.IWMMaterial1);
        Other1=(TextView)findViewById(R.id.Other1);
        SML1=(TextView)findViewById(R.id.SML1);
        CPC1=(TextView)findViewById(R.id.CPC1);

        IWMDate2=(TextView)findViewById(R.id.IWMDate2);
        IWMQuantity2=(TextView)findViewById(R.id.IWMQuantity2);
        IWMMaterial2=(TextView)findViewById(R.id.IWMMaterial2);

        INMDate2=(TextView)findViewById(R.id.INMDate2);
        INMMaterial2=(TextView)findViewById(R.id.INMMaterial2);
        INMQuantity2=(TextView)findViewById(R.id.INMQuantity2);

        IPMDate2=(TextView)findViewById(R.id.IPMDate2);
        IPMMaterial2=(TextView)findViewById(R.id.IPMMaterial2);
        IPMQuantity2=(TextView)findViewById(R.id.IPMQuantity2);
        Other2=(TextView)findViewById(R.id.Other2);
        SML2=(TextView)findViewById(R.id.SML2);
        CPC2=(TextView)findViewById(R.id.CPC2);
        Thinning2=(TextView)findViewById(R.id.Thinning2);
        visitNumber=(TextView) findViewById(R.id.visitNumber);



        viewFarmResults= DataHandler.newInstance().getViewFarmResultList();
       /* Intent abc= this.getIntent();
        String myVisitNo=abc.getStringExtra("visit");*/
        visitNumber.setText(viewFarmResults.get(0).getVisitNumber());
        //Toast.makeText(this, "visitno="+myVisitNo, Toast.LENGTH_SHORT).show();
        for (int j = 0; j < viewFarmResults.size(); j++) {
            String isPrescribed=viewFarmResults.get(j).getIsPrescribed();
            String activityName=viewFarmResults.get(j).getActivityName();
            //INM
            if(isPrescribed.equals("Y")&&activityName.equals("INM")) {

                String doneDate = viewFarmResults.get(j).getDoneDate();
                Object mName = viewFarmResults.get(j).getMaterialName();
                String materialName = String.valueOf(mName);
                String quantity = viewFarmResults.get(j).getQty();
                INMDate2.setText(doneDate);
                INMMaterial2.setText(materialName);
                INMQuantity2.setText(quantity);
            }
            else if(isPrescribed.equals("N")&&activityName.equals("INM"))
            {
                String doneDate=viewFarmResults.get(j).getDoneDate();
                Object mName=viewFarmResults.get(j).getMaterialName();
                String materialName=String.valueOf(mName);
                String quantity=viewFarmResults.get(j).getQty();
                INMDate1.setText(doneDate);
                INMMaterial1.setText(materialName);
                INMQuantity1.setText(quantity);

            }
            //end of INM



            //IWM

            if(isPrescribed.equals("Y")&&activityName.equals("IWM")) {

                String doneDate = viewFarmResults.get(j).getDoneDate();
                Object mName = viewFarmResults.get(j).getMaterialName();
                String materialName = String.valueOf(mName);
                String quantity = viewFarmResults.get(j).getQty();
                IWMDate2.setText(doneDate);
                IWMMaterial2.setText(materialName);
                IWMQuantity2.setText(quantity);
            }
            else if(isPrescribed.equals("N")&&activityName.equals("IWM"))
            {
                String doneDate=viewFarmResults.get(j).getDoneDate();
                Object mName=viewFarmResults.get(j).getMaterialName();
                String materialName=String.valueOf(mName);
                String quantity=viewFarmResults.get(j).getQty();
                IWMDate1.setText(doneDate);
                IWMMaterial1.setText(materialName);
                IWMQuantity1.setText(quantity);

            }


            //end of IWM

            //IPM
            if(isPrescribed.equals("Y")&&activityName.equals("IPM")) {

                String doneDate = viewFarmResults.get(j).getDoneDate();
                Object mName = viewFarmResults.get(j).getMaterialName();
                String materialName = String.valueOf(mName);
                String quantity = viewFarmResults.get(j).getQty();
                IPMDate2.setText(doneDate);
                IPMMaterial2.setText(materialName);
                IPMQuantity2.setText(quantity);
            }
            else if(isPrescribed.equals("N")&&activityName.equals("IPM"))
            {
                String doneDate=viewFarmResults.get(j).getDoneDate();
                Object mName=viewFarmResults.get(j).getMaterialName();
                String materialName=String.valueOf(mName);
                String quantity=viewFarmResults.get(j).getQty();
                IPMDate1.setText(doneDate);
                IPMMaterial1.setText(materialName);
                IPMQuantity1.setText(quantity);

            }


            //end of IPM
            //CPC

            if(isPrescribed.equals("Y")&&activityName.equals("CPC")) {
                Object mName1=viewFarmResults.get(j).getMaterialName();
                String material_Name=String.valueOf(mName1);
                CPC2.setText(material_Name);
            }
            else if(isPrescribed.equals("N")&&activityName.equals("CPC"))
            {
                Object mName1=viewFarmResults.get(j).getMaterialName();
                String material_Name=String.valueOf(mName1);
                CPC1.setText(material_Name);


            }


            //end of CPC
            //SML

            if(isPrescribed.equals("Y")&&activityName.equals("SML")) {
                Object mName1=viewFarmResults.get(j).getMaterialName();
                String material_Name=String.valueOf(mName1);
                SML2.setText(material_Name);
            }
            else if(isPrescribed.equals("N")&&activityName.equals("SML"))
            {
                Object mName1=viewFarmResults.get(j).getMaterialName();
                String material_Name=String.valueOf(mName1);
                SML1.setText(material_Name);


            }



            //end of SML

            //Thinning


            if(isPrescribed.equals("Y")&&activityName.equals("Thinning")) {
                Object mName1=viewFarmResults.get(j).getMaterialName();
                String material_Name=String.valueOf(mName1);
                Thinning2.setText(material_Name);
            }
            else if(isPrescribed.equals("N")&&activityName.equals("Thinning"))
            {
                //

            }



            //end of thinning

            //other
            if(isPrescribed.equals("Y")&&activityName.equals("Other")) {
                Object mName1=viewFarmResults.get(j).getComment();
                String material_Name=String.valueOf(mName1);
                Other2.setText(material_Name);
            }
            else if(isPrescribed.equals("N")&&activityName.equals("Other"))
            {
                Object mName1=viewFarmResults.get(j).getComment();
                String material_Name=String.valueOf(mName1);
                Other1.setText(material_Name);


            }

            //end of other

        } //end of for
        //end of table work


    }
}
