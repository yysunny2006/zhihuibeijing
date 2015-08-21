package com.example.yangyang.zhihuibeijing.base.impl;

import android.app.Activity;
import android.widget.Toast;

import com.example.yangyang.zhihuibeijing.MainActivity;
import com.example.yangyang.zhihuibeijing.base.BaseMenuDetailPager;
import com.example.yangyang.zhihuibeijing.base.BasePager;
import com.example.yangyang.zhihuibeijing.base.menudetail.InteractMenuDetailPager;
import com.example.yangyang.zhihuibeijing.base.menudetail.NewsMenuDetailPager;
import com.example.yangyang.zhihuibeijing.base.menudetail.PhotoMenuDetailPager;
import com.example.yangyang.zhihuibeijing.base.menudetail.TopicMenuDetailPager;
import com.example.yangyang.zhihuibeijing.domain.NewsData;
import com.example.yangyang.zhihuibeijing.fragment.LeftMenuFragment;
import com.example.yangyang.zhihuibeijing.global.GlobalContants;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;

/**
 * Created by yangyang on 2015/8/18.
 */
public class NewsCenterPager extends BasePager{

    private NewsData mNewsData;
    private ArrayList<BaseMenuDetailPager> mPagers;//4个菜单详情页的集合

    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        System.out.println("初始化新闻中心数据...");

        tvTitle.setText("新闻");
        setSlidingMenuEnable(true);

        getDataFromServer();

    }

    //从服务器获取数据
    private  void getDataFromServer(){
        HttpUtils utils = new HttpUtils();

        //使用xutils发送请求
        utils.send(HttpRequest.HttpMethod.GET, GlobalContants.CATEGORIES_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo responseInfo) {
                        String result = (String) responseInfo.result;
                        System.out.println("返回结果：" + result);

                        parseData(result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
    }

    //解析网络数据
    protected  void parseData(String result){
        Gson gson = new Gson();
        mNewsData = gson.fromJson(result, NewsData.class);
        System.out.println("解析结果：" + mNewsData);

        //刷新侧边栏的数据
        MainActivity mainUi = (MainActivity) mActivity;
        LeftMenuFragment leftMenuFragment = mainUi.getLeftMenuFragment();
        leftMenuFragment.setMenuData(mNewsData);

        //准备4个菜单详情页
        mPagers = new ArrayList<BaseMenuDetailPager>();
        mPagers.add(new NewsMenuDetailPager(mActivity, mNewsData.data.get(0).children));
        mPagers.add(new TopicMenuDetailPager(mActivity));
        mPagers.add(new PhotoMenuDetailPager(mActivity));
        mPagers.add(new InteractMenuDetailPager(mActivity));

        setCurrentMenuDetailPager(0);
    }

    //设置当前菜单详情页
    public void setCurrentMenuDetailPager(int position){
        BaseMenuDetailPager pager = mPagers.get(position);
        flContent.removeAllViews();
        flContent.addView(pager.mRootView);

        //设置当前页的标题
        NewsData.NewsMenuData menuData = mNewsData.data.get(position);
        tvTitle.setText(menuData.title);

        pager.initData();
    }





}
