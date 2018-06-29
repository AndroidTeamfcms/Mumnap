package fcms.crptrls.i9930.croptrailsfcms.TestRetrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hp on 26-06-2018.
 */

public class ModelRecieveData {

    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("status_msg")
    @Expose
    private String statusMsg;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }



    @Override
    public String toString() {
        return "RESPONSE{" +
               + '\'' +
                ", statusmsg='" + statusMsg + '\'' +
                ", status=" + status +
                '}';
    }


}