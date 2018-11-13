package com.prize.dagger2_demo.di.module;

import android.os.RemoteCallbackList;
import android.util.ArrayMap;

import dagger.Module;
import dagger.Provides;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/24 14:20
 */
@Module
public class BaseModule {

    @Provides
    ArrayMap<String, String> provideParm() {
        return new ArrayMap();
    }

    @Provides
    RemoteCallbackList provideRemoteCallBack() {
        return new RemoteCallbackList();
    }
}
