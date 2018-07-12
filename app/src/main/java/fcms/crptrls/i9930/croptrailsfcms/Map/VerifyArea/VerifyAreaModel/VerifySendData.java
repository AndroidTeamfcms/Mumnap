package fcms.crptrls.i9930.croptrailsfcms.Map.VerifyArea.VerifyAreaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 06-07-2018.
 */

public class VerifySendData {
    String area;
    String comp_id;
    String farm_id;
   /* @SerializedName("lat")
    @Expose
    private List<LatData> latData = null;
    @SerializedName("lng")
    @Expose
    private List<LongData> longData = null;*/

    @SerializedName("lat")
    @Expose
    private String[] lat;
    @SerializedName("lng")
    @Expose
    private String[] lng;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getComp_id() {
        return comp_id;
    }

    public void setComp_id(String comp_id) {
        this.comp_id = comp_id;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public void setFarm_id(String farm_id) {
        this.farm_id = farm_id;
    }

    public String[] getLat() {
        return lat;
    }

    public void setLat(String[] lat) {
        this.lat = lat;
    }

    public String[] getLng() {
        return lng;
    }

    public void setLng(String[] lng) {
        this.lng = lng;
    }
}
