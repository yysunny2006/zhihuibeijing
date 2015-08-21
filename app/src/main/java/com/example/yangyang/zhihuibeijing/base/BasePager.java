package com.example.yangyang.zhihuibeijing.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yangyang.zhihuibeijing.MainActivity;
import com.example.yangyang.zhihuibeijing.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by yangyang on 2015/8/18.
 */
public class BasePager {

    public Activity mActivity;
    public View mRootView; //布局对象

    public TextView tvTitle;
    public ImageButton btnMenu;
    public FrameLayout flContent;

    public BasePager(Activity activity){
        mActivity = activity;
        initViews();
    }

    //初始化布局
    public void initViews(){
        mRootView = View.inflate(mActivity, R.layout.base_pager, null);

        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        btnMenu = (ImageButton) mRootView.findViewById(R.id.btn_menu);
        flContent = (FrameLayout) mRootView.findViewById(R.id.fl_content);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSlidingMenu();
            }
        });

    }

    //初始化数据
    public void initData(){

    }

    //切换SlidingMenu的状态
    protected void toggleSlidingMenu(){
        MainActivity mainUi = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUi.getSlidingMenu();
        slidingMenu.toggle();//切换状态，显示时隐藏，隐藏时显示
    }

    //设置侧边栏开启或关闭
    public void setSlidingMenuEnable(boolean enable){
        MainActivity mainUi = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUi.getSlidingMenu();

        if(enable){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }










}
