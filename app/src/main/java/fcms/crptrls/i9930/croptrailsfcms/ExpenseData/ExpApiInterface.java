package fcms.crptrls.i9930.croptrailsfcms.ExpenseData;

import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel.ExpenseData;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel.ExpenseSendData;
import fcms.crptrls.i9930.croptrailsfcms.RegisterURLS.AllUrls;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.ModelRecieveData;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.ModelSendData;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by hp on 03-07-2018.
 */

    public interface ExpApiInterface {
        @Multipart
        @POST("accounts/add_sv_exp")
        Call<ResponseBody> registerUser(@Part MultipartBody.Part image,
                                        @Part("comp_id") RequestBody comp_id,
                                        @Part("amount") RequestBody amount,
                                        @Part("sv_id") RequestBody sv_id,
                                        @Part("exp_date") RequestBody exp_date,
                                        @Part("comment") RequestBody comment,
                                        @Part("category_id") RequestBody category_id
                                        );


    @POST(AllUrls.EXPENSE_FETCH)
    Call<ExpenseData> getExpenseData(@Body ExpenseSendData expenseSendData);


}
