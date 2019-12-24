package com.zwl.studyviewpagerdemo.tab;


/**
 * @author zwl
 * @describe 底部tab数据对象
 * @date on 2019-12-21
 */
public class BottomTab {

    public static final int TAB_TYPE_LOCAL = 0;//本地类型
    public static final int TAB_TYPE_NET = 1;//网路类型
    //tab ID
    public int id;
    //tab type 0本地  1网络
    public int type;
    //是否是凸出的图片
    public boolean isBulgeImg = false;
    //tab 名字
    public String tabName;
    //tab 名字 默认颜色
    public String tabNameUnSelectColor;
    //tab 名字 选中颜色
    public String tabNameSelectColor;
    //tab 图片本地资源
    public int imageUnSelectedLocalRes;
    //tab 图片本地选中资源
    public int imageSelectedLocalRes;
    //tab 网络图片 默认
    public String imageUnSelectedUrl;
    //tab 网络图片 选中
    public String imageSelectedUrl;


    public BottomTab() {
    }

    public BottomTab(int id, int type, boolean isBulgeImg, String tabName, String tabNameUnSelectColor, String tabNameSelectColor, int imageUnSelectedLocalRes, int imageSelectedLocalRes) {
        this.id = id;
        this.type = type;
        this.isBulgeImg = isBulgeImg;
        this.tabName = tabName;
        this.tabNameUnSelectColor = tabNameUnSelectColor;
        this.tabNameSelectColor = tabNameSelectColor;
        this.imageUnSelectedLocalRes = imageUnSelectedLocalRes;
        this.imageSelectedLocalRes = imageSelectedLocalRes;
    }

    /**
     * 是否是本地的类型
     *
     * @return
     */
    public boolean isLocalType() {
        return type == 0;
    }


}