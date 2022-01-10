package com.example.vlind_meeting;

import com.google.gson.annotations.SerializedName;

public class ChangeNickname {
    @SerializedName("number")
    public String number;

    @SerializedName("new_nickname")
    public String new_nickname;

    public String getNew_nickname() {
        return new_nickname;
    }

    public String getNumber() {
        return number;
    }

    public void setNew_nickname(String new_nickname) {
        this.new_nickname = new_nickname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ChangeNickname(String number, String new_nickname) {
        this.number = number;
        this.new_nickname = new_nickname;
    }

}
