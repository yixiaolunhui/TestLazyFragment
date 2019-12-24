package com.zwl.studyviewpagerdemo.tab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.zwl.studyviewpagerdemo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zwl
 * @describe 底部菜单TAB工具类
 * @date on 2019-12-20
 */
public class BottomTabUtil {

    public static List<BottomTab> getLocalBottomTabs() {
        List<BottomTab> bottomTabList = new ArrayList<>();
        bottomTabList.add(new BottomTab(0, BottomTab.TAB_TYPE_LOCAL, false, "首页", "#000000", "#fbd415", R.drawable.ic_main_tab_home_unselect, R.drawable.ic_main_tab_home_selected));
        bottomTabList.add(new BottomTab(1, BottomTab.TAB_TYPE_LOCAL, false, "汽车", "#000000", "#fbd415", R.drawable.ic_main_tab_amoy_car_unselect, R.drawable.ic_main_tab_amoy_car_selected));
        bottomTabList.add(new BottomTab(2, BottomTab.TAB_TYPE_LOCAL, true, "发布", "#000000", "#fbd415", R.drawable.ic_tab_post, R.drawable.ic_tab_post));
        bottomTabList.add(new BottomTab(3, BottomTab.TAB_TYPE_LOCAL, false, "服务", "#000000", "#fbd415", R.drawable.ic_main_tab_service_unselect, R.drawable.ic_main_tab_service_selected));
        bottomTabList.add(new BottomTab(4, BottomTab.TAB_TYPE_LOCAL, false, "我的", "#000000", "#fbd415", R.drawable.ic_main_tab_mine_unselect, R.drawable.ic_main_tab_mine_selected));
        return bottomTabList;
    }

    public static List<BottomTab> getXYLocalBottomTabs() {
        List<BottomTab> bottomTabList = new ArrayList<>();
        bottomTabList.add(new BottomTab(0, BottomTab.TAB_TYPE_LOCAL, false, "闲鱼", "#000000", "#fbd415", R.drawable.ic_tabbar_home_nor, R.drawable.ic_tabbar_home_sel));
        bottomTabList.add(new BottomTab(1, BottomTab.TAB_TYPE_LOCAL, false, "鱼塘", "#000000", "#fbd415", R.drawable.ic_tabbar_group_nor, R.drawable.ic_tabbar_group_sel));
        bottomTabList.add(new BottomTab(2, BottomTab.TAB_TYPE_LOCAL, true, "发布", "#000000", "#fbd415", R.drawable.ic_tab_post, R.drawable.ic_tab_post));
        bottomTabList.add(new BottomTab(3, BottomTab.TAB_TYPE_LOCAL, false, "消息", "#000000", "#fbd415", R.drawable.ic_tabbar_message_nor, R.drawable.ic_tabbar_message_sel));
        bottomTabList.add(new BottomTab(4, BottomTab.TAB_TYPE_LOCAL, false, "我的", "#000000", "#fbd415", R.drawable.ic_tabbar_my_nor, R.drawable.ic_tabbar_my_sel));
        return bottomTabList;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取assets中json文件的数据
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = context.getAssets().open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }


    /**
     * 构建Drawable选择器
     *
     * @param checked   选中的图片
     * @param unchecked 未选中的图片
     * @return
     */
    public static StateListDrawable createDrawableSelector(Drawable checked, Drawable unchecked) {
        StateListDrawable stateList = new StateListDrawable();
        int state_selected = android.R.attr.state_selected;
        stateList.addState(new int[]{state_selected}, checked);
        stateList.addState(new int[]{-state_selected}, unchecked);
        return stateList;
    }


    /**
     * 构建颜色选择器
     *
     * @param checkedColor   选中的颜色
     * @param uncheckedColor 未选中颜色
     * @return
     */
    public static ColorStateList createColorSelector(int checkedColor, int uncheckedColor) {
        return new ColorStateList(
                new int[][]{new int[]{android.R.attr.state_selected},
                        new int[]{-android.R.attr.state_selected}},
                new int[]{checkedColor, uncheckedColor});
    }


    /**
     * 将文件转换成Drawable
     *
     * @param pathName pathName就是图片存放的绝对路径
     * @return
     */
    public static Drawable getDrawableByFile(String pathName) {
        return Drawable.createFromPath(pathName);
    }

    /**
     * dp转px
     *
     * @param dp dp值
     * @return px值
     */
    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param px px值
     * @return dp值
     */
    public static int px2dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

}
