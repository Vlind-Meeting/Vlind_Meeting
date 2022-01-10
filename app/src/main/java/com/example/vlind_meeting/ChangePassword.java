package com.example.vlind_meeting;

import com.google.gson.annotations.SerializedName;

public class ChangePassword {
    @SerializedName("number")
    public String number;

    @SerializedName("new_password")
    public String new_password;

    public String getNumber() {
        return number;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public ChangePassword(String number, String new_nickname) {
        this.number = number;
        this.new_password = new_nickname;
    }

}