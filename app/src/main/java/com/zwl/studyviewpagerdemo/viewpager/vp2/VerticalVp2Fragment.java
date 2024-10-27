package com.zwl.studyviewpagerdemo.viewpager.vp2;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.viewpager2.widget.ViewPager2;

import com.zwl.studyviewpagerdemo.R;
import com.zwl.studyviewpagerdemo.lazy.LazyFragment;
import com.zwl.studyviewpagerdemo.lazy.LazyVp2Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwl
 * @describe TODO
 * @date on 2019-12-31
 */
public class VerticalVp2Fragment extends LazyVp2Fragment {

    private ViewPager2 mVerticalViewPager;
    private Button mBtn;
    private boolean isCanLeft = true;//默认可以
    private VerticalVp2PageAdapter videoFragmentStateAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_vertical_vp2;
    }

    @Override
    protected void initView(View rootView) {
        mVerticalViewPager = rootView.findViewById(R.id.vertical_viewpager);
        mBtn = rootView.findViewById(R.id.btn_iscanleft);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCanLeft = !isCanLeft;
                setBtnTv();
            }
        });
        setBtnTv();

        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        videoFragmentStateAdapter = new VerticalVp2PageAdapter(getChildFragmentManager(), getLifecycle(), strings);
        mVerticalViewPager.setAdapter(videoFragmentStateAdapter);
    }

    public void setBtnTv() {
        if (mBtn != null) mBtn.setText("是否可以左滑:" + isCanLeft);
    }

    public boolean isCanSeeMine() {
        return isCanLeft;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        Log.e("ViewPager", "VerticalVp2Fragment-onFragmentFirstVisible");
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
        Log.e("ViewPager", "VerticalVp2Fragment-onFragmentVisible");
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
        Log.e("ViewPager", "VerticalVp2Fragment-onFragmentHide");
    }
}
