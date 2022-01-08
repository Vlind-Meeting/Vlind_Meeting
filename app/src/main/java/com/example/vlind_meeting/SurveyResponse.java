package com.example.vlind_meeting;

import com.google.gson.annotations.SerializedName;

public class SurveyResponse {

    @SerializedName("number")
    public String number;

    @SerializedName("password")
    public String password;

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

