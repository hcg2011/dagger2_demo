package com.prize.dagger2_demo.di.component;

import com.prize.dagger2_demo.ScrollingActivity;
import com.prize.dagger2_demo.di.module.Demo2Module;

import dagger.Subcomponent;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/4/25 18:18
 */
@Subcomponent(modules = Demo2Module.class)
public interface DemoSubComponent {
    void inject(ScrollingActivity actvity);

    @Subcomponent.Builder
    interface SubBuilder {
        SubBuilder mainModule(Demo2Module subModule);

        DemoSubComponent build();
    }
}
