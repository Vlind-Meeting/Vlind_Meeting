package com.example.vlind_meeting;

import com.google.gson.annotations.SerializedName;

public class SurveyResponse {

    @SerializedName("nickname1")
    public String nickname1;

    @SerializedName("filename1")
    public String filename1;

    @SerializedName("nickname2")
    public String nickname2;

    @SerializedName("filename2")
    public String filename2;

    @SerializedName("nickname3")
    public String nickname3;

    @SerializedName("filename3")
    public String filename3;


    public String getNickname1() {
        return nickname1;
    }

    public String getFilename1() {
        return filename1;
    }

    public String getNickname2() {
        return nickname2;
    }

    public String getFilename2() {
        return filename2;
    }

    public String getNickname3() {
        return nickname3;
    }

    public String getFilename3() {
        return filename3;
    }

    public void setNickname1(String nickname1) {
        this.nickname1 = nickname1;
    }

    public void setFilename1(String filename1) {
        this.filename1 = filename1;
    }

    public void setNickname2(String nickname2) {
        this.nickname2 = nickname2;
    }

    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

    public void setNickname3(String nickname3) {
        this.nickname3 = nickname3;
    }

    public void setFilename3(String filename3) {
        this.filename3 = filename3;
    }

}

