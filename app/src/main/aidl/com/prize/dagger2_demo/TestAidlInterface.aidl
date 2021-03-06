// TestAidlInterface.aidl
package com.prize.dagger2_demo;
import com.prize.dagger2_demo.DeliveryMethod;
import com.prize.dagger2_demo.di.module.DemoBean;
//import com.prize.dagger2_demo.di.module.Demo2Bean;
// Declare any non-default types here with import statements

interface TestAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void convertData(String msg);

    void convertDemo(in DemoBean bean);

    String diliverData();

    void setCallBack(DeliveryMethod callback);
}
