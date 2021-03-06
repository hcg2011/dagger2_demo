package com.prize.dagger2_demo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.prize.dagger2_demo.R;
import com.prize.dagger2_demo.dummy.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public class bottomNaviActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navi);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        testMethod();
    }

    private void testMethod() {
        Bundle extras = getIntent().getExtras();
        String test = (String) extras.get("test");
        Log.d("hcg", "onCreate_test:" + test);
       // CToast.makeText(this,"adsfadsfasdfasdf进程通信成功！！！", Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new MessageEvent().setTestmsg(this.getClass().getSimpleName() + test));
    }
}
