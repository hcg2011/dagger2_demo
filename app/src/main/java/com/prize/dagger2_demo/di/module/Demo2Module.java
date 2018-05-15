package com.prize.dagger2_demo.di.module;

import dagger.Module;
import dagger.Provides;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/4/25 18:20
 */
@Module
public class Demo2Module {

    @Provides
    Demo2Bean provideDemo2Bean(){
        return new Demo2Bean("成功了吗？");
    }
}
