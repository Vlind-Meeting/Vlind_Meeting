package com.example.vlind_meeting;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("number")
    public String number;

    @SerializedName("password")
    public String password;

    @SerializedName("filename")
    public String filename;

    @SerializedName("nickname")
    public String nickname;

    @SerializedName("name")
    public String name;

    public String getNumber() {
        return this.number;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName(){
        return this.name;
    }

    public String getFilename() {
        return this.filename;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
