package com.example.yangyang.zhihuibeijing.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.yangyang.zhihuibeijing.base.BasePager;

/**
 * Created by yangyang on 2015/8/18.
 */
public class SettingPager extends BasePager {

    public SettingPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        System.out.println("初始化设置数据...");

        tvTitle.setText("设置");
        btnMenu.setVisibility(View.GONE);
        setSlidingMenuEnable(false);

        TextView text = new TextView(mActivity);
        text.setText("设置");
        text.setTextColor(Color.RED);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);

        //向FrameLayout中动态添加布局
        flContent.addView(text);
    }

}
