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
public class PostChildChildFragment extends LazyFragment {

    private static final String KEY_DATA = "key_data";
    private static final String KEY_PARENT_DATA = "parent_key_data";
    private TextView childTV;
    private int index;
    private int parentIndex;


    public static Fragment newInstance(int parentIndex, int pos) {
        PostChildChildFragment fragment = new PostChildChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARENT_DATA, parentIndex);
        bundle.putInt(KEY_DATA, pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_post_child_child;
    }

    @Override
    protected void initView(View rootView) {
        childTV = rootView.findViewById(R.id.child_tv);
        index = getArguments().getInt(KEY_DATA);
        parentIndex = getArguments().getInt(KEY_PARENT_DATA);
        childTV.setText(getPosStr(index));
    }

    public String getPosStr(int position) {
        return "发布第" + (parentIndex+1) + "-" + (position + 1) + "个界面";
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        Log.e("ViewPager", parentIndex + "-" + index + "---PostChildChildFragment-onFragmentFirstVisible");
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
        Log.e("ViewPager", parentIndex + "-" + index + "---PostChildChildFragment-onFragmentVisible");
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
        Log.e("ViewPager", parentIndex + "-" + index + "---PostChildChildFragment-onFragmentHide");
    }
}
