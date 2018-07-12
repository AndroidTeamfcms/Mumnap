package fcms.crptrls.i9930.croptrailsfcms.DataHandler;

import fcms.crptrls.i9930.croptrailsfcms.Landing.Models.FetchFarmResult;

/**
 * Created by hp on 8/28/2017.
 */
public class DataHandler {
    FetchFarmResult fetchFarmResult;

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
