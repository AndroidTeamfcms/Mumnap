package fcms.crptrls.i9930.croptrailsfcms.Map.ShowArea.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatLng {

@SerializedName("lat")
@Expose
private String lat;
@SerializedName("lng")
@Expose
private String lng;

public String getLat() {
return lat;
}

public void setLat(String lat) {
this.lat = lat;
}

public String getLng() {
return lng;
}

public void setLng(String lng) {
this.lng = lng;
}

}