// DeliveryMethod.aidl
package com.prize.dagger2_demo;
import com.prize.dagger2_demo.di.module.DemoBean;
// Declare any non-default types here with import statements

interface DeliveryMethod {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   void setCallBackDatas(in DemoBean bean);
}
