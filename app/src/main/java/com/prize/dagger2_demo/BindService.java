package com.prize.dagger2_demo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BindService extends Service {

    private static TestAidlInterface mAidl;
    private static ServiceConnection mConnection;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        bindService(this);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    public void bindService(Context ctx) {
        if (mConnection == null)
            mConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    mAidl = TestAidlInterface.Stub.asInterface(iBinder);
                    Log.d("hcg_test", "onServiceConnected!");
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    mAidl = null;
                }
            };

        Intent mIntent = new Intent(ctx, TestService.class);
        ctx.bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    }

    public void setCallBack() throws RemoteException {
        mAidl.setCallBack(new DeliveryMethod.Stub() {
            @Override
            public void setCallBackDatas() throws RemoteException {
                Log.d("hcg_test", "setCallBackDatas!");
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(MessageEvent event) {
        dispatchMsg(event);
    }

    private void dispatchMsg(MessageEvent event) {
        String testmsg = event.getTestmsg();
        if (!TextUtils.isEmpty(testmsg)) {
            try {
                convertData(testmsg);
                getDeliveryData();
                setCallBack();
            } catch (RemoteException e) {
                Log.d("hcg_test", "RemoteException=" + e.getMessage());
            }
        }
    }

    public void getDeliveryData() throws RemoteException {
        String s = mAidl.diliverData();
        Log.d("hcg_test", "s====" + s);
    }

    public void convertData(String msg) throws RemoteException {
        mAidl.convertData(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
