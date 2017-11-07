package com.cky.learnandroiddetails.FirstCodeSecondVersion.ParcelableTest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cuikangyuan on 2017/11/7.
 */

public class Person implements Parcelable {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private int age;

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>(){

        @Override
        public Person createFromParcel(Parcel source) {
            Person person = new Person();
            person.name = source.readString();
            person.age = source.readInt();
            return person;
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
