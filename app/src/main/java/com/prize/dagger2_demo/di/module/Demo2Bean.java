package com.prize.dagger2_demo.di.module;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/4/25 18:27
 */
public class Demo2Bean implements Parcelable{
    private String demo2;
    public int code ;
    private Demo3Bean demo3;

    protected Demo2Bean(Parcel in) {
        demo2 = in.readString();
        code = in.readInt();
        demo3 = in.readParcelable(Demo3Bean.class.getClassLoader());
    }

    public static final Creator<Demo2Bean> CREATOR = new Creator<Demo2Bean>() {
        @Override
        public Demo2Bean createFromParcel(Parcel in) {
            return new Demo2Bean(in);
        }

        @Override
        public Demo2Bean[] newArray(int size) {
            return new Demo2Bean[size];
        }
    };

    public Demo2Bean(String demo2) {
        this.demo2 = demo2;
    }

    public Demo2Bean() {
        super();
    }

    public String getDemo2() {
        return demo2;
    }

    public Demo2Bean setDemo2(String demo2) {
        this.demo2 = demo2;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Demo2Bean setCode(int code) {
        this.code = code;
        return this;
    }

    public Demo3Bean getDemo3() {
        return demo3;
    }

    public Demo2Bean setDemo3(Demo3Bean demo3) {
        this.demo3 = demo3;
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
        parcel.writeParcelable(demo3, i);
    }
}
