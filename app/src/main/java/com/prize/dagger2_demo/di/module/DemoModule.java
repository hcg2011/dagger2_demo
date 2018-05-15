package com.prize.dagger2_demo.di.module;

import dagger.Module;
import dagger.Provides;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/4/25 18:20
 */
@Module
public class DemoModule {

    @Provides
    DemoBean provideDemoBean(){
        return new DemoBean();
    }
}
