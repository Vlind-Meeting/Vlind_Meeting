package com.example.vlind_meeting;

import java.util.ArrayList;

public interface MsgListener {
    public ArrayList<String> getSendNickNames();
    public ArrayList<String> getSendNumbers();
    public ArrayList<String> getReceiveNickNames();
    public ArrayList<String> getReceiveNumbers();
    public ArrayList<String> getReceiveFilenames();
}
