package com.example.vlind_meeting;

import com.google.gson.annotations.SerializedName;

public class JoinRequest {

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

    public JoinRequest(String name, String number, String nickname, String password, String gender) {
        this.name = name;
        this.number = number;
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
    }

}
