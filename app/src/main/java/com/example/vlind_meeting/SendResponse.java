package com.example.vlind_meeting;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SendResponse implements Serializable {
    @SerializedName("nickname")
    public String nickname;

    @SerializedName("user_number")
    public String user_number;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }
}
