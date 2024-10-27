package com.zwl.studyviewpagerdemo.fragment;

import android.util.Log;
import android.view.View;

import com.zwl.studyviewpagerdemo.lazy.LazyFragment;
import com.zwl.studyviewpagerdemo.R;

/**
 * @author zwl
 * @describe 教育
 * @date on 2019-12-23
 */
public class ServiceFragment extends LazyFragment {
    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_service;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        Log.e("ViewPager","ServiceFragment-onFragmentFirstVisible");
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
        Log.e("ViewPager","ServiceFragment-onFragmentVisible");
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
        Log.e("ViewPager","ServiceFragment-onFragmentHide");
    }
}
