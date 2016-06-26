package com.cky.learnandroiddetails;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cuikangyuan on 16/6/26.
 */
public class User implements Parcelable {

    public int userId;
    public String userName;
    public boolean isMale;

    public User(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    protected User(Parcel in) {

        userId = in.readInt();
        userName = in.readString();
        isMale = in.readInt() == 1;
    }

    //反序列化
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    //内容描述
    @Override
    public int describeContents() {
        return 0;
    }

    //序列化
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeInt(isMale ? 1 : 0);
    }
}
