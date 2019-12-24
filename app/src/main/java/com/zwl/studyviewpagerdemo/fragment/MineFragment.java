package com.zwl.studyviewpagerdemo.fragment;

import android.util.Log;
import android.view.View;

import com.zwl.studyviewpagerdemo.LazyFragment;
import com.zwl.studyviewpagerdemo.R;

/**
 * @author zwl
 * @describe 我的
 * @date on 2019-12-23
 */
public class MineFragment extends LazyFragment {
    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        Log.e("ViewPager","MineFragment-onFragmentFirstVisible");
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
        Log.e("ViewPager","MineFragment-onFragmentVisible");
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
        Log.e("ViewPager","MineFragment-onFragmentHide");
    }
}
