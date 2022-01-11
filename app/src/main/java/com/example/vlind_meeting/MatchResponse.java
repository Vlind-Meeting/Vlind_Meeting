package com.example.vlind_meeting;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MatchResponse implements Serializable {
    @SerializedName("send_number")
    public String send_number;

    @SerializedName("receive_number")
    public String receive_number;

    @SerializedName("n")
    public String n;

    public void setN(String n) {
        this.n = n;
    }

    public String getN() {
        return n;
    }

    public void setSend_number(String send_number) {
        this.send_number = send_number;
    }

    public String getSend_number() {
        return send_number;
    }

    public String getReceive_number() {
        return receive_number;
    }

    public void setReceive_number(String receive_number) {
        this.receive_number = receive_number;
    }
}

