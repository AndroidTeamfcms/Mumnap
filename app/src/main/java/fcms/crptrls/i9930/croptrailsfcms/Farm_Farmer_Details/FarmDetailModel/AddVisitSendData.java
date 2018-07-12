package fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmDetailModel;

public class AddVisitSendData {
 int[] activity_template_id;
 int[] material_id;
 String[] comment;
 String[] qty;
 int[] unit_id;
    String[] done_date;
    String[] is_prescribed;
    int farm_id;
    int comp_id;
    String approved_method;
    String visit_date;
    int visit_number;
    float effective_area;

    public void setVisit_number(int visit_number) {
        this.visit_number = visit_number;
    }

    int added_by;

    public int getFarm_id() {
        return farm_id;
    }

    public void setFarm_id(int farm_id) {
        this.farm_id = farm_id;
    }

    public int getComp_id() {
        return comp_id;
    }

    public void setComp_id(int comp_id) {
        this.comp_id = comp_id;
    }

    public String getApproved_method() {
        return approved_method;
    }

    public void setApproved_method(String approved_method) {
        this.approved_method = approved_method;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public int getVisit_number() {
        return visit_number;
    }

    public float getEffective_area() {
        return effective_area;
    }

    public void setEffective_area(float effective_area) {
        this.effective_area = effective_area;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }

    public int[] getActivity_template_id() {
        return activity_template_id;
    }

    public void setActivity_template_id(int[] activity_template_id) {
        this.activity_template_id = activity_template_id;
    }

    public int[] getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int[] material_id) {
        this.material_id = material_id;
    }

    public String[] getComment() {
        return comment;
    }

    public void setComment(String[] comment) {
        this.comment = comment;
    }

    public String[] getQty() {
        return qty;
    }

    public void setQty(String[] qty) {
        this.qty = qty;
    }

    public int[] getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int[] unit_id) {
        this.unit_id = unit_id;
    }

    public String[] getDone_date() {
        return done_date;
    }

    public void setDone_date(String[] done_date) {
        this.done_date = done_date;
    }

    public String[] getIs_prescribed() {
        return is_prescribed;
    }

    public void setIs_prescribed(String[] is_prescribed) {
        this.is_prescribed = is_prescribed;
    }




}
