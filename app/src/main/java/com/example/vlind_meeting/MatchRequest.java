package com.example.vlind_meeting;

import com.google.gson.annotations.SerializedName;

public class MatchRequest {

    @SerializedName("send_number")
    public String send_number;

    @SerializedName("receive_number")
    public String receive_number;

    public String getReceiver_number() {
        return receive_number;
    }

    public String getSend_number() {
        return send_number;
    }

    public void setReceiver_number(String receiver_number) {
        this.receive_number = receiver_number;
    }

    public void setSend_number(String send_number) {
        this.send_number = send_number;
    }
    public MatchRequest (String send_number, String receive_number){
        this.receive_number = receive_number;
        this.send_number = send_number;
    }
}


