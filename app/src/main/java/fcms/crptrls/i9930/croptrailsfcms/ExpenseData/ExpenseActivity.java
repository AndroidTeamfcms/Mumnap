package fcms.crptrls.i9930.croptrailsfcms.ExpenseData;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseAdapter.ExpenseAdapter;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel.Datum;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel.ExpenseData;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel.ExpenseRegistorResponseData;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel.ExpenseSendData;
import fcms.crptrls.i9930.croptrailsfcms.R;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.ApiInterface;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.ModelRecieveData;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.ModelSendData;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.RetrofitClientInstance;
import fcms.crptrls.i9930.croptrailsfcms.Utilities.Utility;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseActivity extends AppCompatActivity {
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    ArrayList<String> encodedImageList;
    JSONObject jsonObject;
    String pictureImagePath = "";
    Context context;
    Bitmap myBitmap;
    ImageView img_expense_recycler;
    ExpenseAdapter expenseAdapter;
    RecyclerView expenseDataRecyclerView;
    LinearLayoutManager linearLayoutManager;
    List<Datum> result;
    Datum datum;
    Button democheck;
    EditText et_exp_date,et_exp_narration,et_exp_amount;
    String str_et_amount;
    String str_et_date;
    String str_et_narration;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.new_theme));
        }
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_expense);
        context=this;
        img_expense_recycler=(ImageView) findViewById(R.id.img_expense_recycler);
        expenseDataRecyclerView=(RecyclerView)findViewById(R.id.expence_recyclerview);
        democheck=(Button)findViewById(R.id.demoCheck);
        et_exp_amount=(EditText)findViewById(R.id.amount_et);
        et_exp_date=(EditText)findViewById(R.id.date_et_exp);
        et_exp_narration=(EditText)findViewById(R.id.comment_et);

        democheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute();
            }
        });

        fetchData();
        img_expense_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

    }

    private void fetchData() {

        ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
        ExpenseSendData expenseSendData=new ExpenseSendData();
        expenseSendData.setComp_id("0");
        expenseSendData.setSv_id("1");
        Call<ExpenseData> expenseDataCall=apiService.getExpenseData(expenseSendData);
        expenseDataCall.enqueue(new Callback<ExpenseData>(){

            @Override
            public void onResponse(Call<ExpenseData> call, Response<ExpenseData> response) {
                Log.e("TAG_DATA",response.body().toString()+"        "+response.message()+"      "+response.code());
                ExpenseData expenseData=response.body();
                //Datum datum=expenseData.getData();
                result=expenseData.getData();
               /* String[] comment=new String[result.size()];
                for(int i=0;i<result.size();i++){
                    comment[i]=result.get(i).getComment();
                    Log.e("TAG",comment[i]);
                }
                Log.e("Data",result.toString());
*/
                expenseAdapter = new ExpenseAdapter(result, context);
                expenseDataRecyclerView.setHasFixedSize(true);
                expenseDataRecyclerView.setAdapter(expenseAdapter);
                expenseAdapter.notifyDataSetChanged();

                linearLayoutManager= new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                expenseDataRecyclerView.setLayoutManager(linearLayoutManager);

            }

            @Override
            public void onFailure(Call<ExpenseData> call, Throwable t) {

            }
        });

    }


    public void register(String comp_id, String sv_id, String amount,String exp_date, String img_path,String comment, String category_id){

        try {
            // create upload service client
            ExpApiInterface service = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);

            // add another part within the multipart request
            RequestBody requestCompId =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), comp_id);
            // add another part within the multipart request
            RequestBody requestsv_id =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), sv_id);

            RequestBody requestAmount =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), amount);
            // add another part within the multipart request
            RequestBody requestexp_id =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), exp_date);

           /* RequestBody requestimg_url =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), img_url);*/
            RequestBody request_comment =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), comment);
            RequestBody requestcategory_id =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), category_id);

            MultipartBody.Part imagenPerfil = null;
            if(img_path!=null){
                File file = new File(img_path);

                File new_file=saveBitmapToFile(file);
                Log.i("Register","Nombre del archivo "+new_file.getName());
                // create RequestBody instance from file
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), new_file);
                // MultipartBody.Part is used to send also the actual file name
                imagenPerfil = MultipartBody.Part.createFormData("img_url", new_file.getName(), requestFile);
            }

            // finally, execute the request
            Call<ResponseBody> call = service.registerUser(imagenPerfil,requestCompId,requestAmount,requestsv_id,requestexp_id, request_comment,requestcategory_id);
            Response<ResponseBody> bodyResponse = call.execute();


            ResponseBody responseBody=bodyResponse.body();
           /* Log.e("TAG_REG_RESPONSE",bodyResponse.body().string());
            Log.e("TAG_REG_CODE",bodyResponse.code()+"");
            Log.e("TAG_REG_MESSAGE",bodyResponse.message());*/

            JSONObject jsonObject=new JSONObject(bodyResponse.body().string());
            String data=jsonObject.getString("img_url");
            Log.e("TAG_NEW_RESPONSE",data );


          /*  for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonobject = jsonArray.getJSONObject(i);
                String img_urlll=jsonobject.getString("img_url");
                Log.e("TAG_NEW_RESPONSE",img_urlll );

            }*/



/*
            Map version = om.readValue(bodyResponse.byteStream(), Map.class);
            String status = (String)version.get("status");
*/

            /*
             ExpenseRegistorResponseData expenseRegistorResponseData= (ExpenseRegistorResponseData) bodyResponse.body();*/
            datum=new Datum();
            datum.setAmount(str_et_amount);
            datum.setCategoryId("0");
            datum.setComment(str_et_narration);
            datum.setCompId("0");
            datum.setDoa("12/1234/12");
            datum.setExpDate(str_et_date);
            datum.setImgUrl(data);
            datum.setIsActive("Y");
            datum.setPersonId("1");
            datum.setSvDailyExpId("1");
            result.add(datum);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    scrollMyListViewToBottom();
                    //expenseAdapter.notifyItemInserted(result.size() - 1);
                    expenseAdapter.notifyDataSetChanged();
                    /*etcomment.setText("");
                    progressDialog.dismiss();*/
                    // Stuff that updates the UI

                }
            });

            /*StaticValues.code  = bodyResponse.code();
            StaticValues.mensaje  = bodyResponse.message();
            ResponseBody errorBody = bodyResponse.errorBody();
            StaticValues.mensajeCodigo  = errorBody==null
                    ?null
                    :Utilities.mensajeCodigoDeLaRespuestaJSON(bodyResponse.errorBody().byteStream());
            Log.i("Register","Code "+StaticValues.code);
            Log.i("Register","mensaje "+StaticValues.mensaje);
            Log.i("Register","mensajeCodigo "+StaticValues.mensaje);*/
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
            }
            /*case 1:{
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }*/
        }
    }



    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(ExpenseActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(ExpenseActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //  intent.setType("image/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }


    private void cameraIntent() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
        File file = new File(pictureImagePath);
        Uri outputFileUri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    private void onCaptureImageResult(Intent data) {
        File imgFile = new File(pictureImagePath);
        if (imgFile.exists()) {
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            myBitmap = getResizedBitmap(myBitmap, 720, 1080);
            if (myBitmap != null) {
                //ImageUploadToServerFunction();
                //register("0","3","2018/07/03",pictureImagePath,"Hello","0");
                }
            else{
                    Toast.makeText(context, "Some Error Ocuurred try again later", Toast.LENGTH_SHORT).show();
                }
            img_expense_recycler.setImageBitmap(myBitmap);

                // Exif.setText(ReadExif(imgFile.getAbsolutePath()));

            }
        }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
         pictureImagePath = cursor.getString(columnIndex);
        cursor.close();
        myBitmap = BitmapFactory.decodeFile(pictureImagePath);
        myBitmap = getResizedBitmap(myBitmap, 720, 1080);
        if (myBitmap != null) {
            //ImageUploadToServerFunction();
            //register("0","3","2018/07/03",picturePath,"Hello","0");

        }
        else {
                Toast.makeText(context, "Some Error Ocuurred try again later", Toast.LENGTH_SHORT).show();
            }
        img_expense_recycler.setImageBitmap(myBitmap);
        }


    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }




    public File saveBitmapToFile(File file){
        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE=75;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100 , outputStream);

            return file;
        } catch (Exception e) {
            return null;
        }
    }


    private class AsyncTaskRunner extends AsyncTask<String, Void, String> {
        public AsyncTaskRunner() {
            super();
        }

        @Override
        protected String doInBackground(String... params) {
             str_et_amount=et_exp_amount.getText().toString().trim();
             str_et_date=et_exp_date.getText().toString().trim();
             str_et_narration=et_exp_narration.getText().toString().trim();

            register("0","1",str_et_amount,str_et_date,pictureImagePath,str_et_narration,"0");
        //public void register(String comp_id, String sv_id, String amount,String exp_date, String img_path,String comment, String category_id){





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


    private void scrollMyListViewToBottom() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        //linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        expenseDataRecyclerView.setLayoutManager(linearLayoutManager);
    }

}
