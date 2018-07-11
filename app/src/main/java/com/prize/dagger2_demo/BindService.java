package com.prize.dagger2_demo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BindService extends Service {

    private static TestAidlInterface mAidl;
    private static TestAidlInterface mAidl2;
    private static ServiceConnection mConnection;
    private static ServiceConnection mConnection2;
    private IBinder.DeathRecipient mRecipient;
    private RemoteCallbackList<IInterface> mList = new RemoteCallbackList();

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
        return Service.START_STICKY;
    }

    public void bindService(Context ctx) {
        setConnection();
        setConnection2();
    }

    private void setConnection() {
        if (mConnection == null)
            mConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    mAidl = TestAidlInterface.Stub.asInterface(iBinder);
                    try {
                        mAidl.asBinder().linkToDeath(mRecipient, 0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    Log.d("hcg_test", "onServiceConnected!");
                    // setTest();
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    mAidl = null;
                    Log.d("hcg_test", "onServiceDisconnected!");
                }
            };

        Intent mIntent = new Intent(this, TestService.class);
        this.bindService(mIntent, mConnection, BIND_AUTO_CREATE);

        mRecipient = () -> {

            boolean b = mAidl.asBinder().unlinkToDeath(mRecipient, 0);
            Log.d("hcg_test", "mRecipient!!!+" + mAidl + "   b=" + b);
            //            Intent intent = new Intent(ctx, TestService.class);
            //            ctx.bindService(intent, mConnection, BIND_AUTO_CREATE);
        };
    }

    private void setConnection2() {
        if (mConnection2 == null)
            mConnection2 = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    mAidl2 = TestAidlInterface.Stub.asInterface(iBinder);
                    Log.d("hcg_test", "onServiceConnected2!");
                    // setTest();
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    mAidl2 = null;
                    Log.d("hcg_test", "onServiceDisconnected2!");
                }
            };

        Intent mIntent = new Intent(this, Test2Service.class);
        this.bindService(mIntent, mConnection2, BIND_AUTO_CREATE);
    }


    public void setCallBack() throws RemoteException {
        mAidl.setCallBack(new DeliveryMethod.Stub() {
            @Override
            public void setCallBackDatas() throws RemoteException {
                Log.d("hcg_test", "setCallBackDatas!");
            }
        });

        mAidl2.setCallBack(new DeliveryMethod.Stub() {
            @Override
            public void setCallBackDatas() throws RemoteException {
                Log.d("hcg_test", "setCallBackDatas2!");
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
                //getDeliveryData();
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
        mAidl2.convertData(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mList != null) {
            mList.kill();
        }
    }
}
