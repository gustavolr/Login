package com.example.gustavor.login.models;

/**
 * Created by gustavor on 19/06/2017.
 */

public class ItemCompra {

    public int mId;
    public int mListId;
    public String mItemName;
    public int mQtd;
    public boolean comprado;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmListId() {
        return mListId;
    }

    public void setmListId(int mListId) {
        this.mListId = mListId;
    }

    public String getmItemName() {
        return mItemName;
    }

    public void setmItemName(String mItemName) {
        this.mItemName = mItemName;
    }

    public int getmQtd() {
        return mQtd;
    }

    public void setmQtd(int mQtd) {
        this.mQtd = mQtd;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }
}
