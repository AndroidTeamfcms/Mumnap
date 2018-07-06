package fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExpenseData {

@SerializedName("data")
@Expose
private List<Datum> data = null;
@SerializedName("status")
@Expose
private Integer status;
@SerializedName("msg")
@Expose
private String msg;

public List<Datum> getData() {
return data;
}

public void setData(List<Datum> data) {
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