package com.zwl.studyviewpagerdemo.viewpager;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.zwl.studyviewpagerdemo.LazyFragment;
import com.zwl.studyviewpagerdemo.R;
import com.zwl.studyviewpagerdemo.fragment.CarFragment;
import com.zwl.studyviewpagerdemo.fragment.HomeFragment;
import com.zwl.studyviewpagerdemo.fragment.MineFragment;

/**
 * @author zwl
 * @describe TODO
 * @date on 2019-12-31
 */
public class VerticalActivity extends AppCompatActivity {

    private static final String TAG_CAR = "TAG_CAR";
    private static final String TAG_HOME = "TAG_HOME";
    private static final String TAG_MINE = "TAG_MINE";
    private PagerView mPageView;
    private VerticalFragment mHomeFragment;
    private CarFragment mCarFragment;
    private MineFragment mMineFragment;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);

        initView();
        initFragment(savedInstanceState);
        initPagerView();
    }

    private void initView() {
        mPageView = findViewById(R.id.pager_view);
    }
    private void initFragment(Bundle savedInstanceState) {
        final FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            mCarFragment = new CarFragment();
            mHomeFragment = new VerticalFragment();
            mMineFragment = new MineFragment();

            fm.beginTransaction()
                    .add(R.id.car_layout, mCarFragment, TAG_CAR)
                    .add(R.id.home_layout, mHomeFragment, TAG_HOME)
                    .add(R.id.mine_layout, mMineFragment, TAG_MINE)
                    .commit();
        } else {
            mCarFragment = (CarFragment) fm.findFragmentByTag(TAG_CAR);
            mHomeFragment = (VerticalFragment) fm.findFragmentByTag(TAG_HOME);
            mMineFragment = (MineFragment) fm.findFragmentByTag(TAG_MINE);
        }

        mCarFragment.setUserVisibleHint(false);
        mHomeFragment.setUserVisibleHint(false);
        mMineFragment.setUserVisibleHint(false);
    }

    private void initPagerView() {
        mPageView.setOnPageChangeListener(new PagerView.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (mCurrentFragment != null) {
                    mCurrentFragment.setUserVisibleHint(false);
                }
                switch (position) {
                    case 0:
                        mCurrentFragment = mCarFragment;
                        break;
                    case 1:
                        mCurrentFragment = mHomeFragment;
                        break;
                    case 2:
                        mCurrentFragment = mMineFragment;
                        break;
                }
                if (mCurrentFragment != null) {
                    mCurrentFragment.setUserVisibleHint(true);
                }

            }
        });
        mPageView.setOnScrollInterceptListener(new PagerView.OnScrollInterceptListener() {
            @Override
            public boolean interceptScroll(float pageOffset, int deltaX) {
                if (pageOffset >= 1) {
                    if (deltaX > 0) {
                        return !mHomeFragment.isCanSeeMine();
                    }
                }
                return false;
            }
        });
        mPageView.setCurrentItem(1);
    }


}
