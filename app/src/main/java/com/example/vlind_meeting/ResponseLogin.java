package com.example.vlind_meeting;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


interface ResponseLogin{
    @POST("/join")
    Call<ResponseBody> postJoin(@Body JoinRequest loginRequest);

    @GET("/login")
    Call<LoginResponse> getLogin(
            @Query("user_number") String user_number
    );

    @GET("/match_check")
    Call<LoginMatchResponse> matchLogin(@Query("user_number") String user_number);

    @FormUrlEncoded
    @PUT("/retrofit/put/{id}")
    Call<ResponseBody> putFunc(@Path("id") String id, @Field("data") String data);

    @DELETE("/retrofit/delete/{id}")
    Call<ResponseBody> deleteFunc(@Path("id") String id);
}