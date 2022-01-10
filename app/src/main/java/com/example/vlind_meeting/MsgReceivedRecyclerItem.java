package com.example.vlind_meeting;

public class MsgReceivedRecyclerItem {
    String nickname;
    int resourceId;
    String soundState, soundInfo;
    int n=0;

    public MsgReceivedRecyclerItem(int resourceId, String nickname, String soundState, String soundInfo, int n) {
        this.nickname = nickname;
        this.resourceId = resourceId;
        this.soundState = soundState;
        this.soundInfo = soundInfo;
        this.n = n;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSoundState() { return soundState; }

    public String getSoundInfo() { return soundInfo; }

    public int getN() { return n; }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSoundState(String soundState) { this.soundState = soundState; }

    public void setSoundInfo(String soundInfo) { this.soundInfo = soundInfo; }

    public void setN(int n) { this.n = n; }

}
