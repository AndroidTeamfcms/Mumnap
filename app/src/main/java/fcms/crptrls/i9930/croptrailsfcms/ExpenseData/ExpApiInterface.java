package fcms.crptrls.i9930.croptrailsfcms.ExpenseData;

import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel.ExpenseData;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel.ExpenseSendData;
import fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmDetailModel.AddVisitSendData;
import fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmDetailsUpdate.Model.FarmDetailsUpdateSendData;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Models.FetchFarmData;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Models.FetchFarmSendData;
import fcms.crptrls.i9930.croptrailsfcms.Login.model.Post;
import fcms.crptrls.i9930.croptrailsfcms.Map.ShowArea.Model.GetGpsCoordinates;
import fcms.crptrls.i9930.croptrailsfcms.RegisterURLS.AllUrls;
import fcms.crptrls.i9930.croptrailsfcms.Report.Model.SendFarmData;
import fcms.crptrls.i9930.croptrailsfcms.Report.Model.ViewFarmData;
import fcms.crptrls.i9930.croptrailsfcms.StatusMsgModel.StatusMsgModel;
import fcms.crptrls.i9930.croptrailsfcms.Map.VerifyArea.VerifyAreaModel.VerifySendData;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by hp on 03-07-2018.
 */

    public interface ExpApiInterface {
        @Multipart
        @POST("android/add_sv_exp")
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

    @POST(AllUrls.GPS_AREA)
    Call<StatusMsgModel> getMsgStatusForVerifyFarm(@Body VerifySendData verifySendData);

    @POST(AllUrls.FARM_DETAILS_UPDATE)
    Call<StatusMsgModel> getMsgStatusForFarmDetailsUpdate(@Body FarmDetailsUpdateSendData farmDetailsUpdateSendData);

    @POST("android/view_cluster_farms")
    Call<FetchFarmData>  fetchFarmDatafncn(@Body FetchFarmSendData fetchFarmSendData);


    @POST("android/supervisor_login")
    @FormUrlEncoded
    Call<Post> savePost(@Field("username") String username ,
                        @Field("password") String password ,
                        @Field("gps") String gps,
                        @Field("ip") String ip);

    @POST("android/view_cluster_farms")
    Call<StatusMsgModel>  getVisitMsgStatus(@Body AddVisitSendData addVisitSendData);

    @POST("android/farm_visit_reports")
    Call<ViewFarmData> viewFarmDataFunction(@Body SendFarmData sendFarmData);

    @POST("android/get_farm_gps")
    Call<GetGpsCoordinates>  getGpsCoordinates(@Body AddVisitSendData addVisitSendData);



}
