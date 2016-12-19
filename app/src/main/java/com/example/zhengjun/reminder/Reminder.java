package com.example.zhengjun.reminder;

/**
 * Created by zhengjun on 16/12/20.
 */

public final class Reminder {
    private int mId;
    private String mContent;
    private int mInportance;

    public Reminder(int mId, String mContent, int mInportance) {
        this.mId = mId;
        this.mContent = mContent;
        this.mInportance = mInportance;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public int getmInportance() {
        return mInportance;
    }

    public void setmInportance(int mInportance) {
        this.mInportance = mInportance;
    }
}
