package com.prize.dagger2_demo.dummy;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/26 17:37
 */
public class CustomEditText extends AppCompatEditText implements LifecycleObserver {

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        setText("_________ onCreate");
        Log.d("hcg_test","_________ onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        setText("_________ onPause");
        Log.d("hcg_test","_________ onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        setText("_________ onStart");
        Log.d("hcg_test","_________ onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
        setText("_________ onDestory");
        Log.d("hcg_test","_________ onDestory");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        setText("_________ onResume");
        Log.d("hcg_test","_________ onResume");
    }

}
