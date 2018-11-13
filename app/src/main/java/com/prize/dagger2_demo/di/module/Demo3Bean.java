package com.prize.dagger2_demo.di.module;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/4/25 18:27
 */
public class Demo3Bean implements Parcelable {
    private String demo2;
    public int code ;

    public Demo3Bean() {
    }

    public Demo3Bean(String demo) {
        this.demo2 = demo;
    }

    protected Demo3Bean(Parcel in) {
        demo2 = in.readString();
        code = in.readInt();
    }

    public static final Creator<Demo3Bean> CREATOR = new Creator<Demo3Bean>() {
        @Override
        public Demo3Bean createFromParcel(Parcel in) {
            return new Demo3Bean(in);
        }

        @Override
        public Demo3Bean[] newArray(int size) {
            return new Demo3Bean[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDemo2() {
        return demo2;
    }

    public Demo3Bean setDemo2(String demo2) {
        this.demo2 = demo2;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(demo2);
        parcel.writeInt(code);
    }
}
