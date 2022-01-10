package com.example.vlind_meeting;

public class MsgSentRecyclerItem {
    String nickname;
    int resourceId;

    public MsgSentRecyclerItem(int resourceId, String nickname) {
        this.nickname = nickname;
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
