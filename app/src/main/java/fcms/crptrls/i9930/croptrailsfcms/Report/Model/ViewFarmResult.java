package fcms.crptrls.i9930.croptrailsfcms.Report.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewFarmResult {

    @SerializedName("done_date")
    @Expose
    private String doneDate;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("is_prescribed")
    @Expose
    private String isPrescribed;
    @SerializedName("visit_date")
    @Expose
    private String visitDate;
    @SerializedName("visit_number")
    @Expose
    private String visitNumber;
    @SerializedName("effective_area")
    @Expose
    private String effectiveArea;
    @SerializedName("activity_name")
    @Expose
    private String activityName;
    @SerializedName("material_name")
    @Expose
    private Object materialName;
    @SerializedName("seq_num")
    @Expose
    private String seqNum;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("person_name")
    @Expose
    private Object personName;

    public String getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(String doneDate) {
        this.doneDate = doneDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getIsPrescribed() {
        return isPrescribed;
    }

    public void setIsPrescribed(String isPrescribed) {
        this.isPrescribed = isPrescribed;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getEffectiveArea() {
        return effectiveArea;
    }

    public void setEffectiveArea(String effectiveArea) {
        this.effectiveArea = effectiveArea;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Object getMaterialName() {
        return materialName;
    }

    public void setMaterialName(Object materialName) {
        this.materialName = materialName;
    }

    public String getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(String seqNum) {
        this.seqNum = seqNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Object getPersonName() {
        return personName;
    }

    public void setPersonName(Object personName) {
        this.personName = personName;
    }

}
