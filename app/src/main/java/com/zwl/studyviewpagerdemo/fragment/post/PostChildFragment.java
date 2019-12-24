package com.zwl.studyviewpagerdemo.fragment.post;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.zwl.studyviewpagerdemo.LazyFragment;
import com.zwl.studyviewpagerdemo.R;

/**
 * @author zwl
 * @date on 2019-12-24
 */
public class PostChildFragment extends LazyFragment {

    private static final String KEY_DATA = "key_data";
    private TextView childTV;
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
        childTV = rootView.findViewById(R.id.child_tv);
        index = getArguments().getInt(KEY_DATA);
        childTV.setText(getPosStr(index));
    }

    public String getPosStr(int position) {
        return "发布第" + (position + 1) + "个界面";
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
