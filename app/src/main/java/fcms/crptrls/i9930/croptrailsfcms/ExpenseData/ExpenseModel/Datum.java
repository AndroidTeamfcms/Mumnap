package fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("sv_daily_exp_id")
    @Expose
    private String svDailyExpId;
    @SerializedName("comp_id")
    @Expose
    private String compId;
    @SerializedName("person_id")
    @Expose
    private String personId;
    @SerializedName("exp_date")
    @Expose
    private String expDate;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("doa")
    @Expose
    private String doa;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("amount")
    @Expose
    private String amount;


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSvDailyExpId() {
        return svDailyExpId;
    }

    public void setSvDailyExpId(String svDailyExpId) {
        this.svDailyExpId = svDailyExpId;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String  imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDoa() {
        return doa;
    }

    public void setDoa(String doa) {
        this.doa = doa;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

}