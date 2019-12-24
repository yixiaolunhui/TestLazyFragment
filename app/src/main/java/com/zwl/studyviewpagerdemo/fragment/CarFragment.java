package com.zwl.studyviewpagerdemo.fragment;

import android.util.Log;
import android.view.View;

import com.zwl.studyviewpagerdemo.LazyFragment;
import com.zwl.studyviewpagerdemo.R;

/**
 * @author zwl
 * @describe 管理
 * @date on 2019-12-23
 */
public class CarFragment extends LazyFragment {
    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_car;
    }
    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        Log.e("ViewPager","CarFragment-onFragmentFirstVisible");
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
        Log.e("ViewPager","CarFragment-onFragmentVisible");
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
        Log.e("ViewPager","CarFragment-onFragmentHide");
    }
}
