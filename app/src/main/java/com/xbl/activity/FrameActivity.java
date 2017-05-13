package com.xbl.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by April on 2017/4/23.
 * 实现帧动画
 * 1.定义一个xml文件，放置animation-list节点
 * 2.为ImageView设置背景动画，并得到操控动画的AnimationDrawable对象
 */

public class FrameActivity extends Activity {
    private ImageView iv;
    //控制动画的对象
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        iv = (ImageView) findViewById(R.id.frame_iv);
        initAnimation();
    }

    public void initAnimation() {
//        //将刚才设置好的帧动画的文件设置到ImageView的背景上
//        iv.setBackgroundResource(R.drawable.frame);
//        //实例化动画操作的对象
//        animationDrawable = (AnimationDrawable) iv.getBackground();
        //通过drawable得到animationDrawable对象
        animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.frame);
        //设置背景
        iv.setBackgroundDrawable(animationDrawable);
    }

    public   void startOnClick(View view) {
        animationDrawable.start();
    }

    public void stopOnClick(View view) {
        animationDrawable.stop();

    }
}
