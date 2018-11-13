package com.prize.dagger2_demo.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.prize.dagger2_demo.DeliveryMethod;
import com.prize.dagger2_demo.TestAidlInterface;
import com.prize.dagger2_demo.di.component.DaggerServiceComponent;
import com.prize.dagger2_demo.di.module.Demo2Bean;
import com.prize.dagger2_demo.di.module.DemoBean;

import javax.inject.Inject;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/7/5 15:06
 */
public class Test2Service extends Service {
    String s1;
    private TestAidlInterface.Stub mStub;
    private IBinder.DeathRecipient mRecipient;
    @Inject
    public RemoteCallbackList<IInterface> mList;

    @Override
    public IBinder onBind(Intent arg0) {
        Log.d("hcg_test", "onBind——mStub2 !!!" + mStub);
        return mStub;
    }

    public void testTime(String s) {
        s1 = s;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("hcg_test", "TestService2——onCreate!!!");
        DaggerServiceComponent.builder().build().inject(this);
        mStub = new TestAidlInterface.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void convertData(String msg) throws RemoteException {
                Log.d("hcg_test", "convertData2=" + msg);
            }

            @Override
            public void convertDemo(DemoBean bean) throws RemoteException {

            }

            @Override
            public String diliverData() throws RemoteException {
                Log.d("hcg_test", "diliverData2!!!");
                return "diliveryData success!!!";
            }

            @Override
            public void setCallBack(DeliveryMethod callback) throws RemoteException {
                mList.register(callback);
                mList.beginBroadcast();
                DeliveryMethod item = (DeliveryMethod) mList.getBroadcastItem(0);
                try {
                    Parcelable.Creator<Demo2Bean> creator = Demo2Bean.CREATOR;

                    item.setCallBackDatas(new DemoBean(new Demo2Bean()));
                    Log.d("hcg_test", "Test2Service!_bindService2-1 !!!");
                    //Intent mIntent = new Intent(Test2Service.this, TestService.class);
                    //Test2Service.this.bindService(mIntent, connection, BIND_AUTO_CREATE);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                mList.finishBroadcast();
            }
        };

        mRecipient = () -> {
            boolean b = mStub.asBinder().unlinkToDeath(mRecipient, 0);
            Log.d("hcg_test", "mRecipient2!!!+" + mStub + "   b=" + b);
            //            Intent intent = new Intent(ctx, TestService.class);
            //            ctx.bindService(intent, mConnection, BIND_AUTO_CREATE);
        };

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                aidlInterface = TestAidlInterface.Stub.asInterface(iBinder);
                try {
                    aidlInterface.asBinder().linkToDeath(mRecipient, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.d("hcg_test", "onServiceConnected!2");
                // setTest();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                aidlInterface = null;
                Log.d("hcg_test", "onServiceDisconnected!2");
            }
        };
    }

    TestAidlInterface aidlInterface;
    ServiceConnection connection;

    @Override
    public void onDestroy() {
        mList.kill();
        super.onDestroy();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        return super.onUnbind(intent);
    }

}
