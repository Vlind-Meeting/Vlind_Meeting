package com.example.vlind_meeting;

import com.google.gson.annotations.SerializedName;

public class HeartRequest {

    @SerializedName("heart_num")
    public int heart_num;

    @SerializedName("send_number")
    public String send_number;

    @SerializedName("receive_number")
    public String receive_number;


    public String getSendNumber() {
        return send_number;
    }

    public int getHeartNum() {
        return heart_num;
    }

    public String getReceiveNumber(){
        return receive_number;
    }

    public void setSendNumber(String send_number) {
        this.send_number = send_number;
    }
    public void setHeartNum(int heart_num) {this.heart_num = heart_num;}
    public void setReceiveNumber(String receive_number) {
        this.receive_number = receive_number;
    }

    public HeartRequest(String send_number, String receive_number, int heart_num) {
        this.send_number = send_number;
        this.heart_num = heart_num;
        this.receive_number = receive_number;
    }
}
