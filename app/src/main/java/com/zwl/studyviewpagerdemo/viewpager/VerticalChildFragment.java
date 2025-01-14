package com.zwl.studyviewpagerdemo.viewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.zwl.studyviewpagerdemo.R;
import com.zwl.studyviewpagerdemo.lazy.LazyFragment;


/**
 * @author zwl
 * @describe TODO
 * @date on 2020-01-05
 */
public class VerticalChildFragment extends LazyFragment {


    private static final String KEY_VIDEO_INDEX = "KEY_VIDEO_INDEX";

    private int mVideoIndex;
    private TextView mChildTV;


    public static Fragment newInstance(int position) {
        VerticalChildFragment fragment = new VerticalChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_VIDEO_INDEX, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.vertical_child_fragment;
    }


    @Override
    protected void initView(View rootView) {
        mVideoIndex = getArguments().getInt(KEY_VIDEO_INDEX);
        mChildTV = rootView.findViewById(R.id.child_tv);
        mChildTV.setText("mVideoIndex=" + mVideoIndex);
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        Log.e("ViewPager", "index=" + mVideoIndex + "  VerticalChildFragment-onFragmentFirstVisible");
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
        Log.e("ViewPager", "index=" + mVideoIndex + "  VerticalChildFragment-onFragmentVisible");
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
        Log.e("ViewPager", "index=" + mVideoIndex + "  VerticalChildFragment-onFragmentHide");
    }
}
