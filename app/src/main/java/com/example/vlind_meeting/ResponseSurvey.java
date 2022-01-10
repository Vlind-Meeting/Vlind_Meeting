package com.example.vlind_meeting;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


interface ResponseSurvey{

    @POST("/survey")
    Call<SurveyResponse> postSurvey(@Body SurveyRequest surveyRequest);

}