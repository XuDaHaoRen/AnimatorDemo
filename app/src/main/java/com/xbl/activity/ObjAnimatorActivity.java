package com.xbl.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by April on 2017/5/6.
 */

public class ObjAnimatorActivity extends Activity {
    private Button start_btn;
    private ImageView alpha_iv;
    private ImageView scaleX_iv;
    private ImageView scaleY_iv;
    private ImageView translationX_iv;
    private ImageView translationY_iv;
    private ImageView rotation_iv;
    private ImageView rotationX_iv;
    private ImageView rotationY_iv;
    private ImageView set_java_iv;
    private ImageView set_xml_iv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obj);
        initView();


    }

    private void initView() {
        alpha_iv = (ImageView) findViewById(R.id.obj_alpha_iv);
        scaleX_iv = (ImageView) findViewById(R.id.obj_scaleX_iv);
        scaleY_iv = (ImageView) findViewById(R.id.obj_scaleY_iv);
        translationX_iv = (ImageView) findViewById(R.id.obj_translationX_iv);
        translationY_iv = (ImageView) findViewById(R.id.obj_translationY_iv);
        rotation_iv = (ImageView) findViewById(R.id.obj_rotation_iv);
        rotationX_iv = (ImageView) findViewById(R.id.obj_rotationX_iv);
        rotationY_iv = (ImageView) findViewById(R.id.obj_rotationY_iv);
        set_java_iv= (ImageView) findViewById(R.id.obj_set_java_iv);
        set_xml_iv= (ImageView) findViewById(R.id.obj_set_xml_iv);

    }

    public void doClick(View v) {
        //startXmlAni();
        startJavaAni();
        createJavaSetAni();
        createXmlSetAni();

    }

    //启动所有java定义的属性的动画
    private void startJavaAni() {
        //透明度的改变
        createJavaAlphaAni();
        //旋转的动画
        createJavaRotationAni();
        //缩放的动画
        createJavaScaleAni();
        //平移动画
        createJavaTranslationAni();
    }

    //启动所有用xml文件定义的动画
    private void startXmlAni() {
        createXmlAlphaAni();
    }


    //透明动画
    private void createJavaAlphaAni() {
        //实例化对象
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(alpha_iv, "alpha", 1.0f, 0f, 1.0f);
        //设置持续时间
        objectAnimator.setDuration(3000);
        objectAnimator.start();


    }

    private void createXmlAlphaAni() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.alpha_animator);
        //给哪个对象添加动画效果
        animator.setTarget(alpha_iv);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d("TAG", "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("TAG", "onAnimationEnd");

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d("TAG", "onAnimationCancel");

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d("TAG", "onAnimationRepeat");

            }
        });
        animator.start();
    }

    //组合动画
    //向左边平移，在边旋转边平移回来，透明度不断变化
    private void createJavaSetAni() {
        //平移
        float currentX = translationX_iv.getTranslationX();//得到当前控件的X轴坐标
        ObjectAnimator tran = ObjectAnimator.ofFloat(set_java_iv, "translationX",
                currentX, -200f, currentX);
        //旋转
        ObjectAnimator rotate=ObjectAnimator.ofFloat(set_java_iv,"rotation",0f,360f);
        //透明度
        ObjectAnimator alpha=ObjectAnimator.ofFloat(set_java_iv,"alpha",1f,0f,1f);
        //缩放
        ObjectAnimator scale = ObjectAnimator.ofFloat(set_java_iv, "scaleX", 1.0f, 3.0f, 1.0f);
        //组合动画的类
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(rotate).with(scale).with(alpha).after(tran);
        animatorSet.setDuration(3000);
        animatorSet.start();

    }
    private void createXmlSetAni(){
        Animator animator=AnimatorInflater.loadAnimator(this,R.animator.set_animator);
        animator.setTarget(set_xml_iv);
        animator.start();
    }


    //旋转动画
    private void createJavaRotationAni() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(rotation_iv, "rotation", 0f, 360f);
        objectAnimator1.setDuration(2000);
        objectAnimator1.start();
        //效果为X轴为旋转轴，上下翻转
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(rotationX_iv, "rotationX", 0f, 360f);
        objectAnimator2.setDuration(2000);
        objectAnimator2.start();
        //效果为y轴为旋转轴，上下翻转
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(rotationY_iv, "rotationY", 0f, 360f);
        objectAnimator3.setDuration(2000);
        objectAnimator3.start();
    }

    //缩放动画
    private void createJavaScaleAni() {
        //缩放
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(scaleX_iv, "scaleX", 1.0f, 3.0f, 1.0f);
        objectAnimator1.setDuration(3000);
        objectAnimator1.start();
        //缩放
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(scaleY_iv, "scaleY", 1.0f, 3.0f, 1.0f);
        objectAnimator2.setDuration(3000);
        objectAnimator2.start();
    }

    //平移动画
    private void createJavaTranslationAni() {
        //平移
        float currentX = translationX_iv.getTranslationX();//得到当前控件的X轴坐标
        ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(translationX_iv, "translationX",
                currentX, -200f, currentX);
        objectAnimator5.setDuration(2000);
        objectAnimator5.start();
        float currentY = translationY_iv.getTranslationX();//得到当前控件的X轴坐标
        ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(translationY_iv, "translationY",
                currentY, -200f, currentX);
        objectAnimator6.setDuration(2000);
        objectAnimator6.start();
    }


}
