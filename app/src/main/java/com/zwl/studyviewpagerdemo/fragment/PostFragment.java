package com.zwl.studyviewpagerdemo.fragment;

import android.util.Log;
import android.view.View;

import com.zwl.studyviewpagerdemo.lazy.LazyFragment;
import com.zwl.studyviewpagerdemo.MyViewPager;
import com.zwl.studyviewpagerdemo.R;
import com.zwl.studyviewpagerdemo.fragment.post.PostChildPagerAdapter;

/**
 * @author zwl
 * @describe 日报
 * @date on 2019-12-23
 */
public class PostFragment extends LazyFragment {
    MyViewPager myViewPager;

    PostChildPagerAdapter mPostChildPagerAdapter;

    @Override
    protected void initView(View rootView) {
        myViewPager = rootView.findViewById(R.id.post_viewpager);
        mPostChildPagerAdapter = new PostChildPagerAdapter(getChildFragmentManager());
        myViewPager.setAdapter(mPostChildPagerAdapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_post;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        Log.e("ViewPager", "PostFragment-onFragmentFirstVisible");
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
        Log.e("ViewPager", "PostFragment-onFragmentVisible");
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
        Log.e("ViewPager", "PostFragment-onFragmentHide");
    }
}
