package com.example.vlind_meeting;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


interface ResponseSurvey{

    @POST("/survey_insert")
    Call<ResponseBody> postSurvey(@Body SurveyRequest surveyRequest);

    @GET("/survey_select")
    Call<SurveyResponse> getSurvey(
            @Query("user_number") String user_number
    );
}