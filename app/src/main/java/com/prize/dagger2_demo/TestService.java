package com.prize.dagger2_demo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
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

    private TestAidlInterface.Stub mStub;
    private DeliveryMethod mMCallBack;
    private RemoteCallbackList<IInterface>  mList=new RemoteCallbackList();

    @Override
    public IBinder onBind(Intent arg0) {
        Log.d("hcg_test", "onBind——mStub!!!"+mStub);
        return mStub;
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
                IInterface item = mList.getBroadcastItem(0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mMCallBack.setCallBackDatas();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }, 3 * 1000);
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return startId;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        return super.onUnbind(intent);
    }

}
