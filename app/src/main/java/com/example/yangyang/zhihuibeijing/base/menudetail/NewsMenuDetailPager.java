package com.example.yangyang.zhihuibeijing.base.menudetail;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangyang.zhihuibeijing.R;
import com.example.yangyang.zhihuibeijing.base.BaseMenuDetailPager;
import com.example.yangyang.zhihuibeijing.base.TabDetailPager;
import com.example.yangyang.zhihuibeijing.domain.NewsData;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

/**
 * Created by yangyang on 2015/8/19.
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager {

    private ViewPager mViewPager;
    private ArrayList<TabDetailPager> mPagerList;
    private ArrayList<NewsData.NewsTabData> mNewsTabData;

    private TabPageIndicator mIndicator;

    public NewsMenuDetailPager(Activity activity, ArrayList<NewsData.NewsTabData> children) {
        super(activity);
        mNewsTabData = children;
    }

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.news_menu_detail, null);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_menu_detail);
        mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        ViewUtils.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        mPagerList = new ArrayList<TabDetailPager>();

        //初始化页面数据
        for(int i = 0; i < mNewsTabData.size(); i++){
            TabDetailPager pager = new TabDetailPager(mActivity, mNewsTabData.get(i));
            mPagerList.add(pager);
        }

        mViewPager.setAdapter(new MenuDetailPager());
        mIndicator.setViewPager(mViewPager);//将viewpager和indicator关联起来，必须在viewpager设置adpter后可用
    }

    @OnClick(R.id.btn_next)
    public void nextPage(View view){
        int currentItem = mViewPager.getCurrentItem();
        mViewPager.setCurrentItem(++currentItem);
    }

    class MenuDetailPager extends PagerAdapter{

        //重写此方法，返回页面标题，用于viewpagerIndicator的页签显示
        @Override
        public CharSequence getPageTitle(int position) {
            return mNewsTabData.get(position).title;
        }

        @Override
        public int getCount() {
            return mPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager pager = mPagerList.get(position);
            container.addView(pager.mRootView);
            pager.initData();
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
