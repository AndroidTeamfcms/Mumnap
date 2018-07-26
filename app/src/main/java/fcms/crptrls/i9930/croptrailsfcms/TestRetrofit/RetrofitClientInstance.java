package fcms.crptrls.i9930.croptrailsfcms.TestRetrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://spade.farm/app/index.php/farmCalendar/";
    private static final String NEW_BASE_URL="http://192.168.0.21/fcmsci/index.php/";
    private static final String BASE_URL_SERVER_URL = "http://35.154.153.223/fcmsci/index.php/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}