package fcms.crptrls.i9930.croptrailsfcms.Landing.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchFarmData {

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("msg")
@Expose
private String msg;
@SerializedName("result")
@Expose
private List<FetchFarmResult> fetchFarmResultList = null;

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

public List<FetchFarmResult> getResult() {
return fetchFarmResultList;
}

public void setResult(List<FetchFarmResult> fetchFarmResultList) {
this.fetchFarmResultList = fetchFarmResultList;
}

}