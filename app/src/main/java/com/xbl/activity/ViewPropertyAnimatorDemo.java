package com.xbl.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

/**
 * Created by April on 2017/5/10.
 * ViewPropertyAnimator的案例
 */

public class ViewPropertyAnimatorDemo extends Activity {
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpa);
        textView= (TextView) findViewById(R.id.view_tv);
    }
    public void doClick(View v){
        createViewPA();
    }
    private void createViewPA(){
        //最后实现的效果是三个动画同时进行
        //这个默认的是一个加速差值器
        textView.animate().alpha(0f).setDuration(3000);
        textView.animate().x(200f).y(200f).setDuration(3000);
        textView.animate().rotation(360).setDuration(3000).setInterpolator(new LinearInterpolator());
    }
}
