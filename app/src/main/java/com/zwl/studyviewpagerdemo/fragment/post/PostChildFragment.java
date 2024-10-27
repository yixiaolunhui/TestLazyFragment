package com.zwl.studyviewpagerdemo.fragment.post;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.zwl.studyviewpagerdemo.lazy.LazyFragment;
import com.zwl.studyviewpagerdemo.MyViewPager;
import com.zwl.studyviewpagerdemo.R;

/**
 * @author zwl
 * @date on 2019-12-24
 */
public class PostChildFragment extends LazyFragment {

    private static final String KEY_DATA = "key_data";
    private MyViewPager myViewPager;
    private PostChildChildPagerAdapter mPostChildChildPagerAdapter;
    private int index;


    public static Fragment newInstance(int arg) {
        PostChildFragment fragment = new PostChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_DATA, arg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_post_child;
    }

    @Override
    protected void initView(View rootView) {
        index = getArguments().getInt(KEY_DATA);
        myViewPager = rootView.findViewById(R.id.child_vp);
        mPostChildChildPagerAdapter = new PostChildChildPagerAdapter(getChildFragmentManager(), index);
        myViewPager.setAdapter(mPostChildChildPagerAdapter);


        myViewPager.setBackgroundColor(getColor(index));
    }

    private int getColor(int index) {
        int color;
        switch (index) {
            case 0:
                color = Color.GREEN;
                break;
            case 1:
                color = Color.RED;
                break;
            case 2:
                color = Color.GRAY;
                break;
            default:
                color = Color.BLUE;
        }
        return color;
    }


    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        Log.e("ViewPager", index + "---PostChildFragment-onFragmentFirstVisible");
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
        Log.e("ViewPager", index + "---PostChildFragment-onFragmentVisible");
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
        Log.e("ViewPager", index + "---PostChildFragment-onFragmentHide");
    }
}
