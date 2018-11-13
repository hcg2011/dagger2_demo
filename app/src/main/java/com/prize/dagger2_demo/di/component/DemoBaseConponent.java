package com.prize.dagger2_demo.di.component;

import com.prize.dagger2_demo.activity.bottomNaviActivity;
import com.prize.dagger2_demo.di.module.Demo2Module;
import com.prize.dagger2_demo.di.module.DemoModule;

import dagger.Component;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/24 16:36
 */
@Component(modules = {DemoModule.class, Demo2Module.class})
public interface DemoBaseConponent {
    void inject(bottomNaviActivity.ScrollingActivity ctx);
}
