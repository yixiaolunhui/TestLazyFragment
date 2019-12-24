package com.zwl.studyviewpagerdemo.fragment.post;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author zwl
 * @date on 2019-12-24
 */
public class PostChildPagerAdapter extends FragmentPagerAdapter {
    public PostChildPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PostChildFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
