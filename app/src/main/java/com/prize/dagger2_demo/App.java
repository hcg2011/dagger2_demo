package com.prize.dagger2_demo;

import android.app.Application;
import android.content.Intent;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        onStartService();
    }

    private void onStartService() {
        Intent intent = new Intent(this, BindService.class);
        startService(intent);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
