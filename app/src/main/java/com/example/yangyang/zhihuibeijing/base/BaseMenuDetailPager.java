package com.example.yangyang.zhihuibeijing.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by yangyang on 2015/8/19.
 */
public abstract class BaseMenuDetailPager {
    public Activity mActivity;

    public View mRootView;

    public BaseMenuDetailPager(Activity activity){
        mActivity = activity;
        mRootView = initViews();
    }

    public abstract View initViews();

    public void initData(){

    }
}
