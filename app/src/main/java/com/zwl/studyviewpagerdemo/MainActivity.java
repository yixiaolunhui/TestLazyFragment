package com.zwl.studyviewpagerdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.zwl.studyviewpagerdemo.fragment.CarFragment;
import com.zwl.studyviewpagerdemo.fragment.FragmentUtil;
import com.zwl.studyviewpagerdemo.fragment.HomeFragment;
import com.zwl.studyviewpagerdemo.fragment.MineFragment;
import com.zwl.studyviewpagerdemo.fragment.PostFragment;
import com.zwl.studyviewpagerdemo.fragment.ServiceFragment;
import com.zwl.studyviewpagerdemo.tab.BottomTab;
import com.zwl.studyviewpagerdemo.tab.BottomTabChangeListener;
import com.zwl.studyviewpagerdemo.tab.BottomTabLayout;
import com.zwl.studyviewpagerdemo.tab.BottomTabUtil;
import com.zwl.studyviewpagerdemo.tab.GsonUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomTabLayout mBottomTabLayout;
    private PermissionInterceptor mPermissionInterceptor;
    private MyViewPager mViewPager;
    private MyFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPermissionInterceptor = new PermissionInterceptor(this);
        checkPermission();
    }

    /**
     * 权限申请检查
     */
    public void checkPermission() {
        String[] permissions = new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        mPermissionInterceptor.setPermissions(permissions)
                .isForcePermission(true)
                .setPermissionListener(new PermissionInterceptor.OnPermissionResult() {
                    @Override
                    public void onPermissionAllAllow() {
                        initView();
                        initTab();
                    }

                    @Override
                    public void onPermissionReject(String[] permissions) {
                    }
                })
                .checkPermission();

    }

    private void initView() {
        mBottomTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setEnableScroll(true);
        mBottomTabLayout.setUpWithViewPager(mViewPager);
        mBottomTabLayout.setOnTabChangeListener(new BottomTabChangeListener() {
            @Override
            public boolean onTabSelect(View view, int position, BottomTab tabInfo) {
                Toast.makeText(MainActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
                mViewPager.setCurrentItem(position, true);
                return true;
            }

            @Override
            public void onTabRepeatSelected(View view, int position, BottomTab tabInfo) {
                Toast.makeText(MainActivity.this, "重复点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });


        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), onCreateFragments());
        mViewPager.setAdapter(mAdapter);


    }

    private void initTab() {

        //模拟网络数据
        String tabJson = BottomTabUtil.getJson(this, "tab.json");
        List<BottomTab> bottomTabList = GsonUtil.fromJsonToList(tabJson, BottomTab[].class);
        mBottomTabLayout.setTabData(bottomTabList);

        //本地数据
        mBottomTabLayout.setTabData(BottomTabUtil.getXYLocalBottomTabs());
        mBottomTabLayout.setTabNum(1, 100);
        mBottomTabLayout.showTabMsg(4, true);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPermissionInterceptor.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * Fragment数据
     *
     * @return
     */
    private List<Fragment> onCreateFragments() {
        final List<Fragment> fragments = new ArrayList<>();
        final FragmentManager manager = getSupportFragmentManager();
        fragments.add(FragmentUtil.findViewPagerFragmentOrCreate(manager, mViewPager, HomeFragment.class, 0));
        fragments.add(FragmentUtil.findViewPagerFragmentOrCreate(manager, mViewPager, CarFragment.class, 1));
        fragments.add(FragmentUtil.findViewPagerFragmentOrCreate(manager, mViewPager, PostFragment.class, 2));
        fragments.add(FragmentUtil.findViewPagerFragmentOrCreate(manager, mViewPager, ServiceFragment.class, 3));
        fragments.add(FragmentUtil.findViewPagerFragmentOrCreate(manager, mViewPager, MineFragment.class, 4));
        return fragments;
    }
}
