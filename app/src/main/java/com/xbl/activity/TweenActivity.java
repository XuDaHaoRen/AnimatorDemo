package com.xbl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by April on 2017/4/23.
 */

public class TweenActivity extends Activity implements Animation.AnimationListener {
    private TextView translation_xml_tv;
    private TextView translation_java_tv;
    private ImageView scale_xml_iv;
    private ImageView scale_java_iv;
    private ImageView rotate_xml_iv;
    private ImageView rotate_java_iv;
    private ImageView alpha_xml_iv;
    private ImageView alpha_java_iv;
    private ImageView set_xml_iv;
    private ImageView set_java_iv;
    //平移动画的操作对象
    private Animation translationAnimation;
    //放大,缩小动画的操作对象
    private Animation toLargeAnimation;
    private Animation toSmallAnimation;
    //放大,缩小动画的操作对象
    private ScaleAnimation toLargeAnimation_java;
    private ScaleAnimation toSmallAnimation_java;
    private Animation rotateAnimationXml;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        translation_xml_tv = (TextView) findViewById(R.id.tween_translation_xml_tv);
        translation_java_tv = (TextView) findViewById(R.id.tween_translation_java_tv);
        scale_xml_iv = (ImageView) findViewById(R.id.tween_scale_xml_iv);
        scale_java_iv = (ImageView) findViewById(R.id.tween_scale_java_iv);
        rotate_xml_iv = (ImageView) findViewById(R.id.tween_rotate_xml_iv);
        rotate_java_iv = (ImageView) findViewById(R.id.tween_rotate_java_iv);
        alpha_xml_iv = (ImageView) findViewById(R.id.tween_alpha_xml_iv);
        alpha_java_iv = (ImageView) findViewById(R.id.tween_alpha_java_iv);
        set_xml_iv= (ImageView) findViewById(R.id.tween_set_xml_iv);
        set_java_iv= (ImageView) findViewById(R.id.tween_set_java_iv);


    }

    //为补间动画设置xml文件,并设置到控件上面
    private void translationAnimationXml() {
        //将xml文件加载到动画中
        translationAnimation = AnimationUtils.loadAnimation(TweenActivity.this,
                R.anim.translate_tween);
        translation_xml_tv.startAnimation(translationAnimation);
    }

    //按钮的点击事件开始动画
    public void tweenStartOnClick(View view) {
        translationAnimationXml();
        translationAnimationJava();
        createScaleXml();
        createScaleJava();
        createRotateXml();
        createRotateJava();
        createAlphaXml();
        createAlphaJava();
        createSetXml();
        createSetJava();


    }

    private void translationAnimationJava() {
        //通过构造方法指定xy坐标
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 300, 0, 0);
        //设置动画差值器(加速差值器)
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        //设置动画的持续时间
        translateAnimation.setDuration(1000);
        translation_java_tv.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("TAG", "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("TAG", "onAnimationEnd");

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //监听动画重复的时候
                Log.d("TAG", "onAnimationRepeat");

            }
        });

    }

    private void createScaleXml() {
        toLargeAnimation = AnimationUtils.loadAnimation(this, R.anim.to_large);
        toSmallAnimation = AnimationUtils.loadAnimation(this, R.anim.to_small);
        toSmallAnimation.setAnimationListener(this);
        toLargeAnimation.setAnimationListener(this);
        scale_xml_iv.startAnimation(toSmallAnimation);

    }

    private void createScaleJava() {
        //Animation.RELATIVE_TO_SELF 相对于自己坐标的的50%为中心点进行缩放，要是没有个方法会直接从x轴的0.5f缩放
        toLargeAnimation_java = new ScaleAnimation(0.2f, 1.0f, 0.2f, 1.0f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        toLargeAnimation_java.setDuration(500);
        toLargeAnimation_java.setInterpolator(new DecelerateInterpolator());
        toSmallAnimation_java = new ScaleAnimation(1.0f, 0.2f, 1.0f, 0.2f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        toSmallAnimation_java.setDuration(500);
        toSmallAnimation_java.setInterpolator(new AccelerateInterpolator());
        toSmallAnimation_java.setAnimationListener(this);
        toLargeAnimation_java.setAnimationListener(this);
        scale_java_iv.startAnimation(toSmallAnimation_java);


    }

    private void createRotateXml() {
        //实例化 Animation rotateAnimationXml
        rotateAnimationXml = AnimationUtils.loadAnimation(this, R.anim.rotate_tween);
        rotate_xml_iv.startAnimation(rotateAnimationXml);
    }

    private void createRotateJava() {
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotate_java_iv.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("tag1", "onAnimationStart");

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("tag1", "onAnimationEnd");

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d("tag1", "onAnimationRepeat");

            }
        });
    }

    private void createAlphaXml() {
        //为控件设置动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_tween);
        alpha_xml_iv.startAnimation(animation);
    }

    private void createAlphaJava() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alpha_java_iv.startAnimation(alphaAnimation);

    }
    private void createSetXml(){
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.set_tween);
        set_xml_iv.startAnimation(animation);
    }
    private void createSetJava(){
        //水平平移200
        TranslateAnimation translateAnimationX = new TranslateAnimation(0, 200, 0, 0);
        translateAnimationX.setDuration(2000);
        //垂直平移200
        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, 200);
        translateAnimationY.setDuration(2000);
        //透明度变化
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, .01f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setStartOffset(2000);
        //旋转变化  补间动画的缺陷：Animation.RELATIVE_TO_SELF旋转的时候会以图片最开始所在的坐标为中心点旋转，
        // 不是以最后到达的地方的中心点
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setStartOffset(2000);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new LinearInterpolator());
        //添加动画
        animationSet.addAnimation(translateAnimationX);
        animationSet.addAnimation(translateAnimationY);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        set_java_iv.startAnimation(animationSet);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    //当动画结束的时候实现对动画进行监听，进行动画的转换
    @Override
    public void onAnimationEnd(Animation animation) {
        Log.d("TAG", "onAnimationEnd");
        if (animation.hashCode() == toLargeAnimation.hashCode()) {
            scale_xml_iv.startAnimation(toSmallAnimation);
        } else if (animation.hashCode() == toSmallAnimation.hashCode()) {
            scale_xml_iv.startAnimation(toLargeAnimation);
        }
        if (animation.hashCode() == toLargeAnimation_java.hashCode()) {
            scale_java_iv.startAnimation(toSmallAnimation_java);
        } else if (animation.hashCode() == toSmallAnimation_java.hashCode()) {
            scale_java_iv.startAnimation(toLargeAnimation_java);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
