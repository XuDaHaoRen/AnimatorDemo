package com.xbl.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by April on 2017/5/12.
 */

public class ShopCarActivity extends Activity {
    private List<String> list;
    private ListView shopcar_lv;
    private ImageView shopcar_iv;
    private int buyNum = 0;//购买数量
    private BadgeView badgeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcar);
        shopcar_lv = (ListView) findViewById(R.id.shopcar_lv);
        shopcar_iv = (ImageView) findViewById(R.id.shopcar_car_iv);
        badgeView = new BadgeView(this);
        badgeView.setTargetView(shopcar_iv);
        badgeView.setBackgroundColor(Color.RED);
        badgeView.setTextColor(Color.WHITE);
        badgeView.setTextSize(12);

        initData();
        shopcar_lv.setAdapter(new MyBaseAdapter());
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("我是商品" + i);
        }
    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(ShopCarActivity.this).inflate(R.layout.item_shopcar, null);
            TextView textView = (TextView) v.findViewById(R.id.item_shopcar_tv);
            Button btn = (Button) v.findViewById(R.id.item_shopcar_btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //小球是从Button发出的，所以要获取Button的x和y的坐标
                    int start_loc[] = new int[2];
                    //得到当前点击控件所在屏幕中的坐标
                    v.getLocationInWindow(start_loc);
                    //创建小球并实现小球的动画
                    createBallView(start_loc, createAniLayout());
                }
            });
            textView.setText(list.get(position));
            return v;
        }
    }

    /**
     * 创建小球的View，添加小球的View到动画层,并显示动画
     *
     * @param start_loc  start_loc[0] 小球开始运动的x轴坐标，start_loc[1]小球开始运动的y坐标
     * @param viewGroups 将小球的View添加到那个动画层
     */
    private void createBallView(int[] start_loc, ViewGroup viewGroups) {
        //小球这个view添加到那个位置
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);//设置小球的大小
        //设置小球的起始位置
        lp.leftMargin = start_loc[0];
        lp.topMargin = start_loc[1];
        ImageView imageView = new ImageView(ShopCarActivity.this);
        imageView.setLayoutParams(lp);
        imageView.setImageResource(R.drawable.cry);
        viewGroups.addView(imageView);
        initAni(start_loc, imageView);
    }

    //创建小球所在的动画层
    private ViewGroup createAniLayout() {
        //得到当前手机屏幕窗口最底层的View
        ViewGroup rootView = (ViewGroup) ShopCarActivity.this.getWindow().getDecorView();
        //构造动画层
        LinearLayout aniLayout = new LinearLayout(ShopCarActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);//设置动画层大小
        aniLayout.setLayoutParams(lp);
        aniLayout.setBackgroundResource(android.R.color.transparent);//将布局设置成透明

        //将动画层添加进去
        rootView.addView(aniLayout);
        return aniLayout;
    }

    /**
     * 为小球的View定义动画，并实现监听事件
     * 购买按钮被点击的时候显示小球，动画结束将小球隐藏
     *
     * @param start_loc 被点击的按钮的位置，小球的起始位置
     * @param v         小球的View
     */
    private void initAni(int start_loc[], final View v) {
        //构造动画
        //得到购物车图片的终点坐标
        int end_loc[] = new int[2];
        shopcar_iv.getLocationInWindow(end_loc);
        //实现动画
        //计算位移
        int x = end_loc[0] - start_loc[0];
        int y = end_loc[1] - start_loc[1];
        TranslateAnimation translateAnimationX = new TranslateAnimation(0, x, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        //动画结束时停留在最后一帧，不然会回到没有执行前的状态
        translateAnimationX.setFillAfter(true);
        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, y);
        translateAnimationY.setInterpolator(new LinearInterpolator());
        translateAnimationY.setFillAfter(true);
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(translateAnimationX);
        set.addAnimation(translateAnimationY);
        set.setDuration(800);
        v.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
                buyNum++;
                badgeView.setBadgeCount(buyNum);
                badgeView.setGravity(Gravity.TOP | Gravity.RIGHT);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}
