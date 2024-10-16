package com.zwl.studyviewpagerdemo.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * @author zwl
 * @describe 视频
 * @date on 2020-01-04
 */
public class VideoFragmentStateAdapter extends FragmentStateAdapter {

    private List<String> videoInfoList;

    public VideoFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity, List<String> data) {
        super(fragmentActivity);
        this.videoInfoList = data;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return VerticalChildFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return videoInfoList == null ? 0 : videoInfoList.size();
    }
}
