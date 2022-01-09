package com.example.vlind_meeting;

public class MsgReceivedRecyclerItem {
    private int mImgName;
    private String mMainText;
    private String mSubText;

    public MsgReceivedRecyclerItem(int mImgName, String mMainText, String mSubText) {
        this.mImgName = mImgName;
        this.mMainText= mMainText;
        this.mSubText = mSubText;
    }

    public int getImgName() {
        return mImgName;
    }

    public void setImgName(int imgName) {
        this.mImgName = imgName;
    }

    public String getMainText() {
        return mMainText;
    }

    public void setMainText(String mainText) {
        this.mMainText = mainText;
    }

    public String getSubText() {
        return mSubText;
    }

    public void setSubText(String subText) {
        this.mSubText = subText;
    }
}