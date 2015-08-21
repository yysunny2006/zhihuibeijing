package com.example.yangyang.zhihuibeijing.domain;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

/**
 * Created by yangyang on 2015/8/19.
 */

//网络分类信息的封装
//字段名必须和服务器返回的字段名一致，方便gson解析
public class NewsData {
    public int retcode;
    public ArrayList<NewsMenuData> data;

    //侧边栏数据对象
    public class NewsMenuData{
        public String id;
        public String title;
        public int type;
        public String url;

        public ArrayList<NewsTabData> children;

        @Override
        public String toString() {
            return "NewsMenuData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    //新闻页面下11个子页面的数据对象
    public class NewsTabData{
        public String id;
        public String title;
        public int type;
        public String url;

        @Override
        public String toString() {
            return "NewsTabData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "data=" + data +
                '}';
    }
}
