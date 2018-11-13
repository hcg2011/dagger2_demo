package com.prize.dagger2_demo.di.module;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import dagger.MapKey;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/9/27 10:42
 */

public @interface Scopes {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface Dev {

    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface Master {

    }

    @MapKey
    @interface TestMap{
        //String value();
        int value();
    }
}
