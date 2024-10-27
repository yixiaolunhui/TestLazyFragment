package com.zwl.studyviewpagerdemo.viewpager;

import static com.zwl.studyviewpagerdemo.viewpager.DirectionalViewPager.SWIPE_BOTH;

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
import com.zwl.studyviewpagerdemo.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwl
 * @describe TODO
 * @date on 2019-12-31
 */
public class VerticalActivity extends AppCompatActivity {

    private DirectionalViewPager mPageView;
    private MyFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);

        initView();
    }

    private void initView() {
        mPageView = findViewById(R.id.pager_view);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), onCreateFragments());
        mPageView.setAdapter(mAdapter);
        mPageView.setCurrentItem(1);
        setSwipeDirection(SWIPE_BOTH);
        mPageView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                updateSwipeDirection(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    private List<Fragment> onCreateFragments() {
        final List<Fragment> fragments = new ArrayList<>();
        final FragmentManager manager = getSupportFragmentManager();
        fragments.add(FragmentUtil.findViewPagerFragmentOrCreate(manager, mPageView, CarFragment.class, 0));
        fragments.add(FragmentUtil.findViewPagerFragmentOrCreate(manager, mPageView, VerticalFragment.class, 1));
        fragments.add(FragmentUtil.findViewPagerFragmentOrCreate(manager, mPageView, MineFragment.class, 2));
        return fragments;
    }

    private void updateSwipeDirection(int position) {
        if (position == 1) {
            mPageView.setSwipeDirection(vpDirection);
        } else {
            mPageView.setSwipeDirection(SWIPE_BOTH);
        }
    }

    int vpDirection = SWIPE_BOTH;

    public void setSwipeDirection(@DirectionalViewPager.SwipeDirection int value) {
        this.vpDirection = value;
        updateSwipeDirection(mPageView.getCurrentItem());
    }


}
