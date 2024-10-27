package com.zwl.studyviewpagerdemo.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author zwl
 * @describe 视频
 * @date on 2020-01-04
 */
public class VideoFragmentPageAdapter extends FragmentPagerAdapter {

    private List<String> videoInfoList;


    public VideoFragmentPageAdapter(FragmentManager childFragmentManager, List<String> data) {
        super(childFragmentManager);
        this.videoInfoList = data;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return VerticalChildFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return videoInfoList == null ? 0 : videoInfoList.size();
    }
}
