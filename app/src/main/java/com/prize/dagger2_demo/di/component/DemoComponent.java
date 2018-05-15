package com.prize.dagger2_demo.di.component;

import com.prize.dagger2_demo.di.module.Demo2Module;
import com.prize.dagger2_demo.di.module.DemoModule;

import dagger.Component;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/4/25 18:18
 */
@Component(modules = {DemoModule.class, Demo2Module.class})
public interface DemoComponent {
    DemoSubComponent.SubBuilder subComponent();
}
