package com.prize.dagger2_demo.di.module;

import android.util.Log;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/4/25 18:27
 */
public class DemoBean {
    private String demo = "DemoBean";
    Demo2Bean demo2;


    public DemoBean() {
        Log.d("hcg", "DemoBean()");
    }

    public DemoBean(Demo2Bean demo) {
        Log.d("hcg", "DemoBean.Demo2Bean=" + demo.getDemo());
        this.demo2 = demo;
    }

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
}
