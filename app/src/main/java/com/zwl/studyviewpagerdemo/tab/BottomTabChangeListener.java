package com.zwl.studyviewpagerdemo.tab;

import android.view.View;

/**
 * @author zwl
 * @describe 底部tab改变接口
 * @date on 2019-12-23
 */
public interface BottomTabChangeListener {

    /**
     * tab选择回调
     *
     * @param view
     * @param position
     * @param tabInfo
     * @return 是否让这次点击生效
     */
    public boolean onTabSelect(View view, int position, BottomTab tabInfo);

    /**
     * tab重复选择回调
     *
     * @param view
     * @param position
     * @param tabInfo
     */
    public void onTabRepeatSelected(View view, int position, BottomTab tabInfo);
}
