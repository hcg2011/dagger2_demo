package com.prize.dagger2_demo.di.module;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/4/25 18:27
 */
public class DemoBean implements Parcelable {
    private String demo = "DemoBean";
    private Demo2Bean demo2;


    public DemoBean() {
    }

    public DemoBean(String demo) {
        this.demo = demo;
    }

    public DemoBean(Demo2Bean demo) {
        Log.d("hcg", "DemoBean.Demo2Bean=" + demo.getDemo2());
        this.demo2 = demo;
    }


    protected DemoBean(Parcel in) {
        demo = in.readString();
        demo2 = in.readParcelable(Demo2Bean.class.getClassLoader());
    }

    public static final Creator<DemoBean> CREATOR = new Creator<DemoBean>() {
        @Override
        public DemoBean createFromParcel(Parcel in) {
            return new DemoBean(in);
        }

        @Override
        public DemoBean[] newArray(int size) {
            return new DemoBean[size];
        }
    };

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public Demo2Bean getDemo2() {
        return demo2;
    }

    public void setDemo2(Demo2Bean demo2) {
        this.demo2 = demo2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(demo);
        parcel.writeParcelable(demo2, i);
    }
}
