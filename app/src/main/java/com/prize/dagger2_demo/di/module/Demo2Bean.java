package com.prize.dagger2_demo.di.module;

import android.util.Log;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/4/25 18:27
 */
public class Demo2Bean {
    private String demo2 = "Demo2Bean哈哈！";

    public Demo2Bean() {
        Log.d("hcg","Demo2Bean!");
    }


    public Demo2Bean(String demo) {
        this.demo2 = demo;
    }

    public String getDemo() {
        return demo2;
    }

    public void setDemo(String demo) {
        this.demo2 = demo;
    }
}
