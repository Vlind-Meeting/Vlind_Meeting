package com.example.vlind_meeting;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface ResponseProfile{

    @POST("/nickname_change")
    Call<ResponseBody> changeNickname(
            @Body ChangeNickname changeNickname
    );
    @POST("/password_change")
    Call<ResponseBody> changePassword(
            @Body ChangePassword changePassword
    );
}