package com.zwl.studyviewpagerdemo.viewpager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.zwl.studyviewpagerdemo.MyFragmentPagerAdapter;
import com.zwl.studyviewpagerdemo.R;
import com.zwl.studyviewpagerdemo.fragment.CarFragment;
import com.zwl.studyviewpagerdemo.fragment.FragmentUtil;
import com.zwl.studyviewpagerdemo.fragment.HomeFragment;
import com.zwl.studyviewpagerdemo.fragment.MineFragment;
import com.zwl.studyviewpagerdemo.fragment.PostFragment;
import com.zwl.studyviewpagerdemo.fragment.ServiceFragment;
import com.zwl.studyviewpagerdemo.viewpager.vp2.VerticalVp2Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwl
 * @describe TODO
 * @date on 2019-12-31
 */
public class VerticalActivity extends AppCompatActivity {

    private ViewPager mPageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);

        initView();
    }

    private void initView() {
        mPageView = findViewById(R.id.pager_view);
        MyFragmentPagerAdapter mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), onCreateFragments());
        mPageView.setAdapter(mAdapter);
        mPageView.setCurrentItem(1);
    }


    private List<Fragment> onCreateFragments() {
        final List<Fragment> fragments = new ArrayList<>();
        final FragmentManager manager = getSupportFragmentManager();
        fragments.add(FragmentUtil.findViewPagerFragmentOrCreate(manager, mPageView, CarFragment.class, 0));
        fragments.add(FragmentUtil.findViewPagerFragmentOrCreate(manager, mPageView, VerticalFragment.class, 1));
        fragments.add(FragmentUtil.findViewPagerFragmentOrCreate(manager, mPageView, MineFragment.class, 2));
        return fragments;
    }


}
