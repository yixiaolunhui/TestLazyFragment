package com.zwl.studyviewpagerdemo.viewpager.vp2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.zwl.studyviewpagerdemo.viewpager.VerticalChildFragment;

import java.util.List;

/**
 * @author zwl
 * @describe 视频
 * @date on 2020-01-04
 */
public class VerticalVp2PageAdapter extends FragmentStateAdapter {

    private List<String> videoInfoList;

    public VerticalVp2PageAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle,List<String> data) {
        super(fragmentManager,lifecycle);
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
