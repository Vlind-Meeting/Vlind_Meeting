package com.example.vlind_meeting;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
    @SerializedName("user_name")
    public String user_name;

    @SerializedName("user_number")
    public String user_number;

    @SerializedName("user_filename")
    public String user_filename;

    @SerializedName("user_nickname")
    public String user_nickname;

    @SerializedName("user_password")
    public String user_password;


    public String getUserName() {
        return user_name;
    }

    public String getUserNumber() {
        return user_number;
    }

    public String getFileName() { return user_filename; }

    public String getUserNickname() {
        return user_nickname;
    }

    public String getUserPassword() {
        return user_password;
    }

}
