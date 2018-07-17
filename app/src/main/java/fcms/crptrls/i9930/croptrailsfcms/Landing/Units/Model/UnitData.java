package fcms.crptrls.i9930.croptrailsfcms.Landing.Units.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 17-07-2018.
 */

public class UnitData {

    @SerializedName("unit_id")
    @Expose
    private String unitId;
    @SerializedName("unit")
    @Expose
    private String unit;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
