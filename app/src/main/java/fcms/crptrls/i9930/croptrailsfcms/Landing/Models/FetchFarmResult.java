package fcms.crptrls.i9930.croptrailsfcms.Landing.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchFarmResult {


    @SerializedName("farm_id")
    @Expose
    private String farmId;
    @SerializedName("lot_no")
    @Expose
    private String lotNo;
    @SerializedName("farmer_id")
    @Expose
    private String farmerId;
    @SerializedName("cluster_id")
    @Expose
    private String clusterId;
    @SerializedName("comp_id")
    @Expose
    private String compId;
    @SerializedName("addL1")
    @Expose
    private String addL1;
    @SerializedName("addL2")
    @Expose
    private String addL2;
    @SerializedName("village_or_city")
    @Expose
    private String villageOrCity;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("mandal_or_tehsil")
    @Expose
    private String mandalOrTehsil;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("exp_area")
    @Expose
    private String expArea;
    @SerializedName("actual_area")
    @Expose
    private String actualArea;
    @SerializedName("irrigation_source")
    @Expose
    private Object irrigationSource;
    @SerializedName("previous_crop")
    @Expose
    private Object previousCrop;
    @SerializedName("irrigation_type")
    @Expose
    private Object irrigationType;
    @SerializedName("soil_type")
    @Expose
    private Object soilType;
    @SerializedName("sowing_date")
    @Expose
    private String sowingDate;
    @SerializedName("exp_flowering_date")
    @Expose
    private String expFloweringDate;
    @SerializedName("actual_flowering_date")
    @Expose
    private String actualFloweringDate;
    @SerializedName("exp_harvest_date")
    @Expose
    private String expHarvestDate;
    @SerializedName("actual_harvest_date")
    @Expose
    private String actualHarvestDate;
    @SerializedName("seed_provided_on")
    @Expose
    private String seedProvidedOn;
    @SerializedName("qty_seed_provided")
    @Expose
    private String qtySeedProvided;
    @SerializedName("seed_unit_id")
    @Expose
    private String seedUnitId;
    @SerializedName("germination")
    @Expose
    private Object germination;
    @SerializedName("population")
    @Expose
    private Object population;
    @SerializedName("spacing_rtr")
    @Expose
    private String spacingRtr;
    @SerializedName("spacing_ptp")
    @Expose
    private String spacingPtp;
    @SerializedName("doa")
    @Expose
    private String doa;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("father_name")
    @Expose
    private String fatherName;

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getAddL1() {
        return addL1;
    }

    public void setAddL1(String addL1) {
        this.addL1 = addL1;
    }

    public String getAddL2() {
        return addL2;
    }

    public void setAddL2(String addL2) {
        this.addL2 = addL2;
    }

    public String getVillageOrCity() {
        return villageOrCity;
    }

    public void setVillageOrCity(String villageOrCity) {
        this.villageOrCity = villageOrCity;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMandalOrTehsil() {
        return mandalOrTehsil;
    }

    public void setMandalOrTehsil(String mandalOrTehsil) {
        this.mandalOrTehsil = mandalOrTehsil;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getExpArea() {
        return expArea;
    }

    public void setExpArea(String expArea) {
        this.expArea = expArea;
    }

    public String getActualArea() {
        return actualArea;
    }

    public void setActualArea(String actualArea) {
        this.actualArea = actualArea;
    }

    public Object getIrrigationSource() {
        return irrigationSource;
    }

    public void setIrrigationSource(Object irrigationSource) {
        this.irrigationSource = irrigationSource;
    }

    public Object getPreviousCrop() {
        return previousCrop;
    }

    public void setPreviousCrop(Object previousCrop) {
        this.previousCrop = previousCrop;
    }

    public Object getIrrigationType() {
        return irrigationType;
    }

    public void setIrrigationType(Object irrigationType) {
        this.irrigationType = irrigationType;
    }

    public Object getSoilType() {
        return soilType;
    }

    public void setSoilType(Object soilType) {
        this.soilType = soilType;
    }

    public String getSowingDate() {
        return sowingDate;
    }

    public void setSowingDate(String sowingDate) {
        this.sowingDate = sowingDate;
    }

    public String getExpFloweringDate() {
        return expFloweringDate;
    }

    public void setExpFloweringDate(String expFloweringDate) {
        this.expFloweringDate = expFloweringDate;
    }

    public String getActualFloweringDate() {
        return actualFloweringDate;
    }

    public void setActualFloweringDate(String actualFloweringDate) {
        this.actualFloweringDate = actualFloweringDate;
    }

    public String getExpHarvestDate() {
        return expHarvestDate;
    }

    public void setExpHarvestDate(String expHarvestDate) {
        this.expHarvestDate = expHarvestDate;
    }

    public String getActualHarvestDate() {
        return actualHarvestDate;
    }

    public void setActualHarvestDate(String actualHarvestDate) {
        this.actualHarvestDate = actualHarvestDate;
    }

    public String getSeedProvidedOn() {
        return seedProvidedOn;
    }

    public void setSeedProvidedOn(String seedProvidedOn) {
        this.seedProvidedOn = seedProvidedOn;
    }

    public String getQtySeedProvided() {
        return qtySeedProvided;
    }

    public void setQtySeedProvided(String qtySeedProvided) {
        this.qtySeedProvided = qtySeedProvided;
    }

    public String getSeedUnitId() {
        return seedUnitId;
    }

    public void setSeedUnitId(String seedUnitId) {
        this.seedUnitId = seedUnitId;
    }

    public Object getGermination() {
        return germination;
    }

    public void setGermination(Object germination) {
        this.germination = germination;
    }

    public Object getPopulation() {
        return population;
    }

    public void setPopulation(Object population) {
        this.population = population;
    }

    public String getSpacingRtr() {
        return spacingRtr;
    }

    public void setSpacingRtr(String spacingRtr) {
        this.spacingRtr = spacingRtr;
    }

    public String getSpacingPtp() {
        return spacingPtp;
    }

    public void setSpacingPtp(String spacingPtp) {
        this.spacingPtp = spacingPtp;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

}
