package com.xbl.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void mainClick(View v){
        switch (v.getId()){
            case R.id.main_fram_btn:
                Intent intent=new Intent(MainActivity.this,FrameActivity.class);
                startActivity(intent);
                break;
            case R.id.main_tween_btn:
                Intent intent2=new Intent(MainActivity.this,TweenActivity.class);
                startActivity(intent2);
                break;
            case R.id.main_obj_btn:
                Intent intent3=new Intent(MainActivity.this,ObjAnimatorActivity.class);
                startActivity(intent3);
                break;
            case R.id.main_property_btn:
                Intent intent4=new Intent(MainActivity.this,ViewPropertyAnimatorDemo.class);
                startActivity(intent4);
                break;
            case R.id.main_shopcar_btn:
                Intent intent5=new Intent(MainActivity.this,ShopCarActivity.class);
                startActivity(intent5);
                break;
        }
    }
}
