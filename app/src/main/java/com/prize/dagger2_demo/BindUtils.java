package com.prize.dagger2_demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/7/5 17:58
 */
public class BindUtils {

    private TestAidlInterface mAidl;
    private ServiceConnection mConnection;
    private static BindUtils mInstance;

    public BindUtils getInstance(){
        synchronized (BindUtils.class) {
            if (mInstance == null)
                mInstance = new BindUtils();
        }
        return mInstance;
    }

    private void bindService(Context ctx) {
        if (mConnection == null)
            mConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    mAidl = TestAidlInterface.Stub.asInterface(iBinder);
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    mAidl = null;
                }
            };

        Intent mIntent = new Intent(ctx, TestService.class);
        ctx.bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    }

    private void setCallBack() throws RemoteException {
        mAidl.setCallBack(new DeliveryMethod.Stub() {
            @Override
            public void setCallBackDatas() throws RemoteException {
                Log.d("hcg_test", "setCallBackDatas!");
            }
        });
    }

    private void getDeliveryData() throws RemoteException {
        String s = mAidl.diliverData();
        Log.d("hcg_test", "s====" + s);
    }

    private void convertData() throws RemoteException {
        mAidl.convertData("kjasdhfkjaslfha!!!!");
    }


}
