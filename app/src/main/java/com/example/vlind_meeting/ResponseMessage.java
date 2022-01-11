package com.example.vlind_meeting;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface ResponseMessage{

    @GET("/message_send")
    Call<List<SendResponse>> sendFunc(@Query("user_number") String user_number);

    @GET("/message_receive")
    Call<List<ReceiveResponse>> receiveFunc(@Query("user_number") String user_number);

    @POST("/match_success")
    Call<MatchResponse> successFunc(@Body MatchRequest matchRequest);

    @POST("/match_fail")
    Call<ResponseBody> failFunc(@Body MatchRequest matchRequest);


    @FormUrlEncoded
    @PUT("/retrofit/put/{id}")
    Call<ResponseBody> putFunc(@Path("id") String id, @Field("data") String data);

    @DELETE("/retrofit/delete/{id}")
    Call<ResponseBody> deleteFunc(@Path("id") String id);
}