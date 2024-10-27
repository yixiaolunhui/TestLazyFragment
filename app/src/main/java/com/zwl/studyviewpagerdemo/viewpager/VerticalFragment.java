package com.zwl.studyviewpagerdemo.viewpager;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zwl.studyviewpagerdemo.R;
import com.zwl.studyviewpagerdemo.lazy.LazyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwl
 * @describe TODO
 * @date on 2019-12-31
 */
public class VerticalFragment extends LazyFragment {

    private VerticalViewPager mVerticalViewPager;
    private Button mBtn;
    private boolean isCanLeft = true;//默认可以
    private VideoFragmentPageAdapter videoFragmentStateAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_vertical;
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
                Activity act = getActivity();
                if (act instanceof VerticalActivity) {
                    if(isCanLeft){
                        ((VerticalActivity) act).setSwipeDirection(DirectionalViewPager.SWIPE_BOTH);
                    }else{
                        ((VerticalActivity) act).setSwipeDirection(DirectionalViewPager.SWIPE_LEFT);
                    }
                }
            }
        });
        setBtnTv();

        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        videoFragmentStateAdapter = new VideoFragmentPageAdapter(getChildFragmentManager(), strings);
        mVerticalViewPager.setAdapter(videoFragmentStateAdapter);
    }

    public void setBtnTv() {
        if (mBtn != null) mBtn.setText("是否可以左滑:" + isCanLeft);
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        Log.e("ViewPager", "VerticalFragment-onFragmentFirstVisible");
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
        Log.e("ViewPager", "VerticalFragment-onFragmentVisible");
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
        Log.e("ViewPager", "VerticalFragment-onFragmentHide");
    }
}
