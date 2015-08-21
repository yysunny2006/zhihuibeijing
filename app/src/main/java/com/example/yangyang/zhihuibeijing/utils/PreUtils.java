package com.example.yangyang.zhihuibeijing.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yangyang on 2015/8/17.
 */
public class PreUtils {
    public static final String PREF_NAME = "config";

    public static boolean getBoolean(Context ctx, String key, boolean defaultValue){
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static boolean setBoolean(Context ctx, String key, boolean value){
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.edit().putBoolean(key, value).commit();
    }

}
