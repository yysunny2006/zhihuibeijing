package com.example.yangyang.zhihuibeijing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.yangyang.zhihuibeijing.utils.PreUtils;

import java.util.ArrayList;

/**
 * Created by yangyang on 2015/8/13.
 */
public class GuideActivity extends Activity {

    private static final int[] mImageIds = new int[] { R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private ViewPager vpGuide;
    private ArrayList<ImageView> mImageViewList;
    private Button btnStart;
    private LinearLayout llPointGroup;
    private View viewRedPoint;

    private int mPointWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        vpGuide = (ViewPager)findViewById(R.id.vp_guide);
        btnStart = (Button)findViewById(R.id.btn_start);
        llPointGroup = (LinearLayout)findViewById(R.id.ll_point_group);
        viewRedPoint = (View)findViewById(R.id.view_red_point);

        initView();

        vpGuide.setAdapter(new GuideAdapter());
        vpGuide.setOnPageChangeListener(new GuidePageListener());
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreUtils.setBoolean(GuideActivity.this, "is_user_guide_showed", true);
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    private void initView(){
        mImageViewList = new ArrayList<ImageView>();

        //初始化引导页
        for(int i = 0; i < mImageIds.length; i++){
            ImageView image = new ImageView(this);
            image.setBackgroundResource(mImageIds[i]);
            mImageViewList.add(image);
        }

        //初始化引导页的小圆点
        for(int i = 0; i < mImageIds.length; i++){
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            if(i > 0){
                params.leftMargin = 30;
            }

            point.setLayoutParams(params);

            llPointGroup.addView(point);
        }

        //获取视图树，对layout结束时间进行监听
        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        System.out.println("layout 结束");
                        llPointGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        mPointWidth = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();
                        System.out.println("原点距离：" + mPointWidth);
                    }
                }
        );

    }

    class GuideAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    class GuidePageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // System.out.println("当前位置:" + position + ";百分比:" + positionOffset
            // + ";移动距离:" + positionOffsetPixels);
            int len = (int) (mPointWidth * positionOffset) + position * mPointWidth;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewRedPoint.getLayoutParams();
            params.leftMargin = len;

            viewRedPoint.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
            if(position == mImageIds.length - 1){
                btnStart.setVisibility(View.VISIBLE);
            }else{
                btnStart.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

}


