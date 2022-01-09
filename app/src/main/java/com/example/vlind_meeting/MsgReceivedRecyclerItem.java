package com.example.vlind_meeting;

public class MsgReceivedRecyclerItem {
    String name;
    String message;
    int resourceId;

    public MsgReceivedRecyclerItem(int resourceId, String name, String message) {
        this.name = name;
        this.message= message;
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
