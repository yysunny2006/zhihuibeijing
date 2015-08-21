package com.example.yangyang.zhihuibeijing.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.yangyang.zhihuibeijing.R;
import com.example.yangyang.zhihuibeijing.base.BasePager;
import com.example.yangyang.zhihuibeijing.base.impl.GovAffairsPager;
import com.example.yangyang.zhihuibeijing.base.impl.HomePager;
import com.example.yangyang.zhihuibeijing.base.impl.NewsCenterPager;
import com.example.yangyang.zhihuibeijing.base.impl.SettingPager;
import com.example.yangyang.zhihuibeijing.base.impl.SmartServicePager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by yangyang on 2015/8/17.
 */
public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.rg_group)
    private RadioGroup rgGroup;

    @ViewInject(R.id.vp_content)
    private ViewPager mViewPager;

    private ArrayList<BasePager> mPagerList;

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        rgGroup.check(R.id.rb_home);

        mPagerList = new ArrayList<BasePager>();
        mPagerList.add(new HomePager(mActivity));
        mPagerList.add(new NewsCenterPager(mActivity));
        mPagerList.add(new SmartServicePager(mActivity));
        mPagerList.add(new GovAffairsPager(mActivity));
        mPagerList.add(new SettingPager(mActivity));

        mViewPager.setAdapter(new ContentAdapter());

        //监听RadioGroup的选择事件
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        mViewPager.setCurrentItem(0, false);//去掉切换页面的动画
                        break;
                    case R.id.rb_news:
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_smartservice:
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_gov:
                        mViewPager.setCurrentItem(3, false);
                        break;
                    case R.id.rb_setting:
                        mViewPager.setCurrentItem(4, false);
                        break;
                }
            }
        });

       mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
               mPagerList.get(position).initData();
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });

        //手动初始化首页
        mPagerList.get(0).initData();
    }

    class ContentAdapter extends PagerAdapter{

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
            BasePager pager = mPagerList.get(position);
            container.addView(pager.mRootView);
            //pager.initData();不要在此处初始化数据，否则会预加载下一个页面
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    //获取新闻中心页面
    public NewsCenterPager getNewCenterPager(){
        return (NewsCenterPager) mPagerList.get(1);
    }

}
