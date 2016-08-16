package com.cky.learnandroiddetails;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/15.
 */

public class Contact {

    private String mName;
    private Boolean mOnline;


    public Contact(String mName, Boolean mOnline) {
        this.mName = mName;
        this.mOnline = mOnline;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Boolean getmOnline() {
        return mOnline;
    }

    public void setmOnline(Boolean mOnline) {
        this.mOnline = mOnline;
    }

    public static ArrayList<Contact> createList(int num) {

        ArrayList<Contact> contacts = new ArrayList<Contact>();

        for (int i = 1; i <= num; i++) {

            contacts.add(new Contact("Name " + i, i > num / 2));
        }

        return contacts;
    }
}
