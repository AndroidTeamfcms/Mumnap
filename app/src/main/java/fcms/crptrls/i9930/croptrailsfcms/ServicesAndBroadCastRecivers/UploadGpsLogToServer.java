package fcms.crptrls.i9930.croptrailsfcms.ServicesAndBroadCastRecivers;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fcms.crptrls.i9930.croptrailsfcms.DatsbaseHandler.DatabaseHandler;
import fcms.crptrls.i9930.croptrailsfcms.DatsbaseHandler.SaveGpsGetterSetter;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpApiInterface;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Units.Model.UnitDataAndStatus;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Units.Model.UnitSendData;
import fcms.crptrls.i9930.croptrailsfcms.Login.model.Post;
import fcms.crptrls.i9930.croptrailsfcms.ServicesAndBroadCastRecivers.Model.SendGpsArray;
import fcms.crptrls.i9930.croptrailsfcms.SharedPref.SharedPreferencesMethod;
import fcms.crptrls.i9930.croptrailsfcms.StatusMsgModel.StatusMsgModel;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by hp on 13-04-2018.
 */

public class UploadGpsLogToServer extends BroadcastReceiver {

    SaveGpsGetterSetter saveGpsGetterSetter;
    final List<SaveGpsGetterSetter> saveGpsGetterSetterList=new ArrayList<>();


    @Override
    public void onReceive(Context context, Intent intent) {



        DatabaseHandler db = new DatabaseHandler(context);
        List<SaveGpsGetterSetter> contacts = db.getallgpsCordinates();

            if(contacts.size()>0) {

                for (SaveGpsGetterSetter cn : contacts) {
                    String logall = "Id: " + cn.getId() + "    Latitude" + cn.getLati_cord() + " ,Longitude: " + cn.getLongi_cord() + "   Date" + cn.getEnter_date();
                    // Writing Contacts to log

                    saveGpsGetterSetter = new SaveGpsGetterSetter(cn.getId(), cn.getLati_cord(), cn.getLongi_cord(),cn.getSv_id(), cn.getEnter_date());
                    //getprofile.setYear(123);
                    saveGpsGetterSetterList.add(saveGpsGetterSetter);
                    Log.e("GpsCordinates in dball ", logall);
                    //id_count=cn.get_id();
                }
            }

        JSONObject contactsObj = new JSONObject();
        JSONArray contactsArray = new JSONArray();

        try {
            for (int i = 0; i < saveGpsGetterSetterList.size(); i++) {
                JSONObject contact = new JSONObject();
                contact.put("lat_cord",saveGpsGetterSetterList.get(i).getLati_cord());
                contact.put("long_cord",saveGpsGetterSetterList.get(i).getLongi_cord());
                contact.put("sv_id",saveGpsGetterSetterList.get(i).getSv_id());
                contact.put("enter_date",saveGpsGetterSetterList.get(i).getEnter_date());
                contactsArray.put(i, contact);
            }

            contactsObj.put("location_list", contactsArray);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Object jsonStr = null;
        try {
            jsonStr = contactsObj.get("location_list");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("CONTACTS", jsonStr.toString());


        ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
        SendGpsArray sendGpsArray=new SendGpsArray();
        //sendGpsArray.setJsonObject(contactsObj);
        sendGpsArray.setLocation_list(jsonStr);
        Call<StatusMsgModel> statusMsgModelCall=apiService.getStatusMsgforUploadGpsInBack(sendGpsArray);

        statusMsgModelCall.enqueue(new Callback<StatusMsgModel>() {
            @Override
            public void onResponse(Call<StatusMsgModel> call, Response<StatusMsgModel> response) {
                if(response!=null) {
                    StatusMsgModel statusMsgModel = response.body();
                    Log.e("ResponseForGps", statusMsgModel.getMsg());
                }
            }

            @Override
            public void onFailure(Call<StatusMsgModel> call, Throwable t) {
                Log.e("ResponseForGps",t.toString());

            }
        });
    }

}
