package com.example.vlind_meeting;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


interface ResponseSurvey{

    @POST("/survey_insert")
    Call<ResponseBody> insertSurvey(@Body SurveyRequest surveyRequest);

    @GET("/survey_match")
    Call<SurveyResponse> getSurvey(
            @Query("user_number") String user_number
    );
    @POST("/survey")
    Call<SurveyResponse> postSurvey(@Body SurveyRequest surveyRequest);

    @POST("/heart_num")
    Call<SurveyResponse> insertHeart(@Body HeartRequest heartRequest);
}