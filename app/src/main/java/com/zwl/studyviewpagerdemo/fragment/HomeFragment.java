package com.zwl.studyviewpagerdemo.fragment;

import android.util.Log;
import android.view.View;

import com.zwl.studyviewpagerdemo.lazy.LazyFragment;
import com.zwl.studyviewpagerdemo.R;

/**
 * @author zwl
 * @describe 首页
 * @date on 2019-12-23
 */
public class HomeFragment extends LazyFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initView(View rootView) {

    }


    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        Log.e("ViewPager","HomeFragment-onFragmentFirstVisible");
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
        Log.e("ViewPager","HomeFragment-onFragmentVisible");
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
        Log.e("ViewPager","HomeFragment-onFragmentHide");
    }
}
