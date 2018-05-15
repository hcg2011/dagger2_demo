package com.prize.dagger2_demo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.prize.dagger2_demo.di.component.DaggerDemoComponent;
import com.prize.dagger2_demo.di.module.Demo2Bean;
import com.prize.dagger2_demo.di.module.DemoBean;

import javax.inject.Inject;

public class ScrollingActivity extends AppCompatActivity {

    @Inject
    DemoBean bean;

    @Inject
    Demo2Bean bean2;
    private EditText mEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DaggerDemoComponent.builder().build().subComponent().build().inject(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        mEt = (EditText) findViewById(R.id.et);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "bean="+bean.getDemo()+" bean2="+bean2.getDemo(), Snackbar.LENGTH_LONG)
                        .setAction("c", null).show();
                setAnima(mEt);
            }
        });

    }

    private void setAnima(final View view) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt( view.getLayoutParams().width,  view.getLayoutParams().width+300);
        valueAnimator.setDuration(2000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {

                int currentValue = (Integer) animator.getAnimatedValue();
                // 获得每次变化后的属性值
                System.out.println(currentValue);
                // 输出每次变化后的属性值进行查看
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.width = currentValue;
                view.requestLayout();
            }
        });
        valueAnimator.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
