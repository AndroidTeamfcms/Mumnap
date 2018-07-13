package fcms.crptrls.i9930.croptrailsfcms.DataHandler;

import java.util.List;

import fcms.crptrls.i9930.croptrailsfcms.Landing.Models.FetchFarmResult;
import fcms.crptrls.i9930.croptrailsfcms.Report.Model.ViewFarmResult;

/**
 * Created by hp on 8/28/2017.
 */
public class DataHandler {
    FetchFarmResult fetchFarmResult;


    List<ViewFarmResult> viewFarmResultList;

    public List<ViewFarmResult> getViewFarmResultList() {
        return viewFarmResultList;
    }

    public void setViewFarmResultList(List<ViewFarmResult> viewFarmResultList) {
        this.viewFarmResultList = viewFarmResultList;
    }

    public FetchFarmResult getFetchFarmResult() {
        return fetchFarmResult;
    }

    public void setFetchFarmResult(FetchFarmResult fetchFarmResult) {
        this.fetchFarmResult = fetchFarmResult;
    }

    private static DataHandler datahandler=null;
    public static DataHandler newInstance(){
        if(datahandler==null){
            datahandler=new DataHandler();
        }
        return datahandler;
    }
}
