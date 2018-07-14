package fcms.crptrls.i9930.croptrailsfcms.Login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {

@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("username")
@Expose
private String username;
@SerializedName("role_id")
@Expose
private String roleId;
@SerializedName("comp_id")
@Expose
private String compId;
@SerializedName("password")
@Expose
private String password;
@SerializedName("is_active")
@Expose
private String isActive;
@SerializedName("doa")
@Expose
private String doa;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getRoleId() {
return roleId;
}

public void setRoleId(String roleId) {
this.roleId = roleId;
}

public String getCompId() {
return compId;
}

public void setCompId(String compId) {
this.compId = compId;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public String getIsActive() {
return isActive;
}

public void setIsActive(String isActive) {
this.isActive = isActive;
}

public String getDoa() {
return doa;
}

public void setDoa(String doa) {
this.doa = doa;
}

}