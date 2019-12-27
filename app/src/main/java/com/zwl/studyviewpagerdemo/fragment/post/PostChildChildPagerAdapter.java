package com.zwl.studyviewpagerdemo.fragment.post;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author zwl
 * @date on 2019-12-24
 */
public class PostChildChildPagerAdapter extends FragmentPagerAdapter {
    public int index;

    public PostChildChildPagerAdapter(FragmentManager fm, int index) {
        super(fm);
        this.index = index;
    }

    @Override
    public Fragment getItem(int position) {
        return PostChildChildFragment.newInstance(index, position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
