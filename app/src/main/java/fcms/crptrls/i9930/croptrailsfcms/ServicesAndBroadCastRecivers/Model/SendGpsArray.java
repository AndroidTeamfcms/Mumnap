package fcms.crptrls.i9930.croptrailsfcms.ServicesAndBroadCastRecivers.Model;

import org.json.JSONObject;

import java.util.List;

import fcms.crptrls.i9930.croptrailsfcms.DatsbaseHandler.SaveGpsGetterSetter;

/**
 * Created by hp on 23-07-2018.
 */

public class SendGpsArray {
   JSONObject jsonObject;
   Object location_list;

    public Object getLocation_list() {
        return location_list;
    }

    public void setLocation_list(Object location_list) {
        this.location_list = location_list;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


}
