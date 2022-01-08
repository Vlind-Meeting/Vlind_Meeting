package com.example.vlind_meeting;

import com.google.gson.annotations.SerializedName;

public class SurveyRequest {

    @SerializedName("name")
    public String name;

    @SerializedName("number")
    public String number;

    @SerializedName("nickname")
    public String nickname;

    @SerializedName("password")
    public String password;

    @SerializedName("gender")
    public String gender;

    @SerializedName("filename")
    public String filename;

    @SerializedName("q1")
    public int q1;

    @SerializedName("q2")
    public int q2;

    @SerializedName("q3")
    public int q3;

    @SerializedName("q4")
    public int q4;

    @SerializedName("q5")
    public int q5;

    @SerializedName("q6")
    public int q6;

    @SerializedName("q7")
    public int q7;

    @SerializedName("q8")
    public int q8;

    @SerializedName("q9")
    public int q9;

    @SerializedName("q10")
    public String q10;



    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public SurveyRequest(String name, String number, String nickname, String password, String gender,
                         String filename, int q1, int q2, int q3, int q4, int q5, int q6,
                         int q7, int q8,int q9, String q10) {
        this.name = name;
        this.number = number;
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
        this.filename = filename;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.q7 = q7;
        this.q8 = q8;
        this.q9 = q9;
        this.q10 = q10;

    }

}

