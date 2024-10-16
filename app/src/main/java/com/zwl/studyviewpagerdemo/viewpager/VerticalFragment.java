package com.zwl.studyviewpagerdemo.viewpager;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.viewpager2.widget.ViewPager2;

import com.zwl.studyviewpagerdemo.LazyFragment;
import com.zwl.studyviewpagerdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwl
 * @describe TODO
 * @date on 2019-12-31
 */
public class VerticalFragment extends LazyFragment {

    private ViewPager2 mVerticalViewPager;
    private Button mBtn;
    private boolean isCanLeft = true;//默认可以

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
            }
        });

        mVerticalViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);


            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                Log.e("ViewPager", "视频" + (position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        setBtnTv();
    }

    public void setBtnTv() {
        if (mBtn != null)
            mBtn.setText("是否可以左滑:" + isCanLeft);
    }

    public boolean isCanSeeMine() {
        return isCanLeft;
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

        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");

        mVerticalViewPager.setAdapter(new VideoFragmentStateAdapter(getActivity(), strings));

        mVerticalViewPager.setOffscreenPageLimit(strings.size() - 1);
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
        Log.e("ViewPager", "VerticalFragment-onFragmentHide");
    }
}
