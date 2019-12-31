package com.zwl.studyviewpagerdemo.viewpager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zwl.studyviewpagerdemo.R;

/**
 * @author zwl
 * @date on 2019-12-31
 */
public class VerticalPager extends RecyclerView.Adapter<VerticalPager.ViewHolder> {
    private boolean isCanLeft;

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.v_vp_tv);
        }
    }

    @NonNull
    @Override
    public VerticalPager.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical_viewpager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalPager.ViewHolder holder, int position) {
        holder.tv.setText("视频" + (position + 1));
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
