package com.prize.dagger2_demo.di.component;

import android.content.Context;

import com.prize.dagger2_demo.di.module.BaseModule;

import dagger.Component;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/24 14:24
 */
@Component(modules = BaseModule.class)
public interface ServiceComponent {
    void inject(Context ctx);
}
