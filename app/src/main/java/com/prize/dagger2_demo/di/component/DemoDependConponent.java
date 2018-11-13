package com.prize.dagger2_demo.di.component;

import android.content.Context;

import dagger.Component;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/9/27 11:28
 */
@Component(dependencies = DemoBaseConponent.class)
public interface DemoDependConponent {
    void inject(Context ctx);
}