package fcms.crptrls.i9930.croptrailsfcms.TestFolder.DynamicButtonAdd_demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fcms.crptrls.i9930.croptrailsfcms.R;

public class TestForAddViewActivity extends AppCompatActivity {

    private LinearLayout parentLinearLayout;
    TextView demoShowtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_for_add_view);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
        demoShowtext=(TextView)findViewById(R.id.demoShowtext);


        demoShowtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ArrayList<EditText> myEditTextList = new ArrayList<EditText>();

                for( int i = 0; i < parentLinearLayout.getChildCount(); i++ ) {
                    if (parentLinearLayout.getChildAt(i) instanceof EditText){
                        myEditTextList.add((EditText) parentLinearLayout.getChildAt(i));
                    }
                }*/

               ArrayList<EditText> myEditTextList = traverseEditTexts(parentLinearLayout);



                Toast.makeText(TestForAddViewActivity.this, myEditTextList.size()+"", Toast.LENGTH_SHORT).show();

                for(int j=0;j<parentLinearLayout.getChildCount();j++){
                    if (parentLinearLayout.getChildAt(j) instanceof EditText){
                        Log.e("TAG",myEditTextList.get(j).getText().toString());
                    }
                }
            }



        });


        ArrayList<EditText> myEditTextList = new ArrayList<EditText>();

        for( int i = 0; i < parentLinearLayout.getChildCount(); i++ )
            if( parentLinearLayout.getChildAt( i ) instanceof EditText )
                myEditTextList.add( (EditText) parentLinearLayout.getChildAt( i ) );



    }

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }


    private ArrayList<EditText> traverseEditTexts(ViewGroup v)
    {
        EditText invalid = null;
        ArrayList<EditText> myEditTextList = new ArrayList<EditText>();
        for (int i = 0; i < v.getChildCount(); i++)
        {
            Object child = v.getChildAt(i);
            if (child instanceof EditText)
            {
                EditText e = (EditText)child;
                myEditTextList.add( e );

               /* if(e.getText().length() == 0)    // Whatever logic here to determine if valid.
                {
                    return myEditTextList;   // Stops at first invalid one. But you could add this to a list.
                }*/
            }
            else if(child instanceof ViewGroup)
            {
                myEditTextList = traverseEditTexts((ViewGroup)child);  // Recursive call.
                if(myEditTextList != null)
                {
                    break;
                }
            }
        }
        return myEditTextList;
    }
}
