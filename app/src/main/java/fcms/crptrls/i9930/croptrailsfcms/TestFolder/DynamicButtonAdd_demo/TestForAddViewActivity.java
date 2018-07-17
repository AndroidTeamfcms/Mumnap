package fcms.crptrls.i9930.croptrailsfcms.TestFolder.DynamicButtonAdd_demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fcms.crptrls.i9930.croptrailsfcms.R;

public class TestForAddViewActivity extends AppCompatActivity {

    private LinearLayout parentLinearLayout;
    TextView demoShowtext;
    EditText editText1;
    int i=0;
    final int x=0;
    List<EditText> allEds = new ArrayList<EditText>();
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


                //String[] strings = new String[](allEds.size());

                for(int i=0; i < allEds.size(); i++){
                    //Toast.makeText(TestForAddViewActivity.this, allEds.get(i).getText().toString(), Toast.LENGTH_SHORT).show();
                    Log.e("Tag",allEds.get(i).getText().toString());
                }

              /* for(int j=0;j<4;j++) {
                   editText1=(EditText) findViewById(j);
                   Toast.makeText(TestForAddViewActivity.this, editText1.getText().toString(), Toast.LENGTH_SHORT).show();
               }
               ArrayList<EditText> myEditTextList = traverseEditTexts(parentLinearLayout);



                Toast.makeText(TestForAddViewActivity.this, myEditTextList.size()+"", Toast.LENGTH_SHORT).show();

                for(int j=0;j<parentLinearLayout.getChildCount();j++){
                    if (parentLinearLayout.getChildAt(j) instanceof EditText){
                        Log.e("TAG",myEditTextList.get(j).getText().toString());
                    }
                }*/
            }



        });


        ArrayList<EditText> myEditTextList = new ArrayList<EditText>();

        for( int i = 0; i < parentLinearLayout.getChildCount(); i++ )
            if( parentLinearLayout.getChildAt( i ) instanceof EditText )
                myEditTextList.add( (EditText) parentLinearLayout.getChildAt( i ) );

    }

    @SuppressLint("ResourceType")
    public void onAddField(View v) {
        i++;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
         editText1=rowView.findViewById(R.id.number_edit_text);

         allEds.add(editText1);

        final Button delete_button=rowView.findViewById(R.id.delete_button);



         v.setId(i);
         v.setTag(R.id.delete_button,i);
        //parentLinearLayout.setTag(i);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);




        delete_button.setOnClickListener(new View.OnClickListener() {
            final int x=i;
            @Override
            public void onClick(View v) {
                i--;

//                int lay_no=(Integer) delete_button.getTag();
   //             Toast.makeText(TestForAddViewActivity.this, lay_no+"", Toast.LENGTH_SHORT).show();
                parentLinearLayout.removeView((View) v.getParent());

                delete_button.getTag(R.id.delete_button);
                //allEds.remove((View)v.getParent());

                // LinearLayout parent_new=(LinearLayout)findViewById(R.id.parent_linear_layout);
                /*int index=((ViewGroup)rowView.getParent()).indexOfChild(parentLinearLayout);
                parentLinearLayout.removeView((View) v.getParent());
                allEds.remove(x);*/
                //Toast.makeText(TestForAddViewActivity.this, index+"", Toast.LENGTH_SHORT).show();

                // x--;
               /* parentLinearLayout.removeView((View) v.getParent());
                allEds.remove(parentLinearLayout.getChildCount()-5);*/
             /*   if(allEds.size()==x){
                    Toast.makeText(TestForAddViewActivity.this, "True"+" "+i+" "+x, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TestForAddViewActivity.this, "false"+" "+i+" "+x, Toast.LENGTH_SHORT).show();

                }*/
                /*Toast.makeText(v.getContext(),
                        "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                        .show();*/
            }
        });

    }

    public void onDeleteFieldButt(View v){
        parentLinearLayout.removeView((View) v.getParent());
    }

   /* @SuppressLint("ResourceType")
    public void onDelete(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        editText1=v.findViewById(R.id.number_edit_text);
        Button delete_button=new Button(this);
        i--;
      *//*  if(Integer.valueOf((Integer) parentLinearLayout.getTag())==1) {
            Toast.makeText(this,"equal to 0", Toast.LENGTH_SHORT).show();
        }
          else  if(Integer.valueOf((Integer) parentLinearLayout.getTag())>1){
            Toast.makeText(this, "equal to 1", Toast.LENGTH_SHORT).show();
        }*//*
       *//* if(i+1==Integer.valueOf((Integer) parentLinearLayout.getTag())){
            Toast.makeText(this, "Can Delete", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Can't Delete", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, i+" "+parentLinearLayout.getTag(),Toast.LENGTH_SHORT).show();*//*
        //allEds.remove(((TableRow) v.getParent()).indexOfChild(v));

        LinearLayout r = (LinearLayout) ((ViewGroup) v.getParent());
        int position = r.indexOfChild(v);
        Toast.makeText(this, delete_button.getId()+"", Toast.LENGTH_SHORT).show();
        parentLinearLayout.removeView((View) v.getParent());
    }*/


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
