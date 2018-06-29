package fcms.crptrls.i9930.croptrailsfcms.TestRetrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by hp on 26-06-2018.
 */

public interface ApiInterface {

    @POST("send_farm_calendar_column_data_to_app")
    Call<ModelRecieveData> getsoildata(@Body ModelSendData modelSendData);

}
