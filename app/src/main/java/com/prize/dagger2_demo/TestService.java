package com.prize.dagger2_demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/7/5 15:06
 */
public class TestService extends Service {
    String s1;
    private TestAidlInterface.Stub mStub;
    private IBinder.DeathRecipient mRecipient;
    private RemoteCallbackList<IInterface>  mList=new RemoteCallbackList();

    @Override
    public IBinder onBind(Intent arg0) {
        Log.d("hcg_test", "onBind——mStub!!!"+mStub);
        try {
            mStub.asBinder().linkToDeath(mRecipient, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return mStub;
    }

    public void testTime(String s) {
        s1 = s;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("hcg_test", "TestService——onCreate!!!");
        mStub = new TestAidlInterface.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void convertData(String msg) throws RemoteException {
                Log.d("hcg_test", "convertData=" + msg);
            }

            @Override
            public String diliverData() throws RemoteException {
                Log.d("hcg_test", "diliverData!!!");
                return "diliveryData success!!!";
            }

            @Override
            public void setCallBack(DeliveryMethod callback) throws RemoteException {
                mList.register(callback);
                mList.beginBroadcast();
                int count = mList.getRegisteredCallbackCount();
                Log.d("hcg_test", "setCallBack!!!count" + count);
                for (int i = 0; i < count; i++) {
                    DeliveryMethod item = (DeliveryMethod) mList.getBroadcastItem(i);
                    item.setCallBackDatas();
                }
                mList.finishBroadcast();
            }
        };

        mRecipient = () -> {
            boolean b = mStub.asBinder().unlinkToDeath(mRecipient, 0);
            Log.d("hcg_test", "mRecipient!!!+"+mStub+"   b="+b);
            //            Intent intent = new Intent(ctx, TestService.class);
            //            ctx.bindService(intent, mConnection, BIND_AUTO_CREATE);
        };
    }

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
