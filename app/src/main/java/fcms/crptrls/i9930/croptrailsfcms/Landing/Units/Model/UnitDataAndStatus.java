package fcms.crptrls.i9930.croptrailsfcms.Landing.Units.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hp on 17-07-2018.
 */

public class UnitDataAndStatus {
    @SerializedName("data")
    @Expose
    private List<UnitData> data = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;

    public List<UnitData> getData() {
        return data;
    }

    public void setData(List<UnitData> data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
