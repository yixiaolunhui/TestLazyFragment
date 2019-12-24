package com.zwl.studyviewpagerdemo.tab;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.zwl.studyviewpagerdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwl
 * @describe 底部tab布局
 * @date on 2019-12-21
 */
public class BottomTabLayout extends LinearLayout implements View.OnTouchListener {
    private Context mContext;
    //底部tab数据
    private List<BottomTab> mBottomTabs = new ArrayList<>();
    //底部所有itemViews
    private List<View> mBottomTabViews = new ArrayList<>();
    //显示隐藏动画的时间值
    private int scrollDuration = 300;
    //tab切换回调接口
    private BottomTabChangeListener mBottomTabChangeListener;
    //当前选择的tab index
    private int currentTabIndex;
    //凸出的图片尺寸
    private int tabBulgeImgSize;
    private float tabSize = 25;

    public BottomTabLayout(Context context) {
        this(context, null);
    }

    public BottomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setClipChildren(false);
        tabBulgeImgSize = BottomTabUtil.dp2px(context, 60);
    }


    /**
     * 设置tab数据
     *
     * @param mBottomTabs
     */
    public void setTabData(List<BottomTab> mBottomTabs) {
        if (mBottomTabs == null || mBottomTabs.size() == 0) {
            return;
        }
        removeAllViews();
        //清除所有的子view
        this.mBottomTabViews.clear();
        this.mBottomTabs = mBottomTabs;
        addTabViews();
    }

    /**
     * 添加tabView
     */
    private void addTabViews() {
        if (mBottomTabs == null || mBottomTabs.size() == 0) return;
        for (int i = 0; i < mBottomTabs.size(); i++) {
            BottomTab bottomTab = mBottomTabs.get(i);
            View tabView = getTabView(bottomTab, i);
            tabView.setTag(i);
            LayoutParams params;
            if (getOrientation() == HORIZONTAL) {
                if (bottomTab.isBulgeImg) {
                    TextView tab_item_name = tabView.findViewById(R.id.tab_item_name);
                    ImageView tab_item_icon = tabView.findViewById(R.id.tab_item_icon);
                    RelativeLayout tab_item_bg = tabView.findViewById(R.id.tab_item_bg);

                    ViewGroup.LayoutParams layoutParams = tab_item_icon.getLayoutParams();
                    layoutParams.width = tabBulgeImgSize;
                    layoutParams.height = tabBulgeImgSize;
                    tab_item_icon.setLayoutParams(layoutParams);


                    tab_item_name.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    params = new LayoutParams(0, tabBulgeImgSize + tab_item_name.getMeasuredHeight()+tab_item_bg.getPaddingBottom()+tab_item_bg.getPaddingTop(), 1.0f);
                    params.setMargins(0, 0, 0,  tab_item_name.getMeasuredHeight()+tab_item_bg.getPaddingBottom()+tab_item_bg.getPaddingTop()
                            );

                    ViewGroup view = (ViewGroup) getParent();
                    if (view != null) {
                        view.setClipChildren(false);
                    }
                } else {
                    params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
                }
                setGravity(Gravity.CENTER_VERTICAL);
            } else {
                params = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1.0f);
                setGravity(Gravity.CENTER_HORIZONTAL);
            }
            addView(tabView, i, params);
            mBottomTabViews.add(tabView);
        }

        setCurrentTab(0);
    }

    /**
     * 设置margin
     *
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }


    /**
     * 获取Tab View
     */
    private View getTabView(BottomTab bottomTab, final int index) {
        final View tabView = LayoutInflater.from(getContext()).inflate(R.layout.tab_item_view, null);
        //设置tab点击事件 其中多加了一个重复点击的回调 为了有的需求是点击多次也要刷新的奇葩需求  比如我们
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                if (currentTabIndex != position) {
                    boolean select = true;
                    if (mBottomTabChangeListener != null) {
                        select = mBottomTabChangeListener.onTabSelect(tabView, position, mBottomTabs.get(index));
                    }
                    if (select) {
                        setCurrentTab(position);
                    }
                } else {
                    if (mBottomTabChangeListener != null) {
                        mBottomTabChangeListener.onTabRepeatSelected(tabView, position, mBottomTabs.get(index));
                    }
                }
            }
        });
        tabView.setOnTouchListener(this);
        return tabView;
    }

    /**
     * 设置选中
     *
     * @param currentTab
     */
    public void setCurrentTab(int currentTab) {
        this.currentTabIndex = currentTab;
        updateTabState(currentTab);
    }

    /**
     * 更新tab状态
     *
     * @param currentTab
     */
    private void updateTabState(int currentTab) {
        for (int index = 0; index < mBottomTabs.size(); index++) {
            boolean isSelect = (index == currentTab);
            View bottomTabView = mBottomTabViews.get(index);
            BottomTab mBottomTab = mBottomTabs.get(index);
            ImageView tab_item_icon = bottomTabView.findViewById(R.id.tab_item_icon);
            final TextView tab_item_name = bottomTabView.findViewById(R.id.tab_item_name);

            tab_item_name.setTextColor(isSelect ? Color.parseColor(mBottomTab.tabNameSelectColor)
                    : Color.parseColor(mBottomTab.tabNameUnSelectColor));

            if (mBottomTab.isLocalType()) {
                tab_item_name.setText(mBottomTabs.get(index).tabName);
                tab_item_icon.setImageResource(isSelect ? mBottomTab.imageSelectedLocalRes : mBottomTab.imageUnSelectedLocalRes);
            } else {
                final int finalIndex = index;
                Glide.with(getContext()).load(isSelect ? mBottomTab.imageSelectedUrl : mBottomTab.imageUnSelectedUrl)
                        .apply(new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter())
                        .override(mBottomTab.isBulgeImg ? tabBulgeImgSize : BottomTabUtil.dp2px(getContext(), tabSize),
                                mBottomTab.isBulgeImg ? tabBulgeImgSize : BottomTabUtil.dp2px(getContext(), tabSize))
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                tab_item_name.setText(mBottomTabs.get(finalIndex).tabName);
                                return false;
                            }
                        })
                        .into(tab_item_icon);
            }
        }
    }

    /**
     * 设置ViewPager
     *
     * @param viewPager
     */
    public void setUpWithViewPager(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentTab(position);
            }
        });
    }


    /**
     * 设置底部小红点的数量
     *
     * @param index
     * @param num
     */
    public void setTabNum(int index, int num) {
        for (int i = 0; i < mBottomTabViews.size(); i++) {
            TextView mTabNum = (TextView) mBottomTabViews.get(i).findViewById(R.id.tab_item_num);
            if (index == i && num > 0) {
                mTabNum.setVisibility(VISIBLE);
                mTabNum.setText(num >= 100 ? "99+" : String.valueOf(num));
            }
        }
    }

    /**
     * 是否显示消息小圆点
     *
     * @param index
     * @param hasMsg
     */
    public void showTabMsg(int index, boolean hasMsg) {
        for (int i = 0; i < mBottomTabViews.size(); i++) {
            TextView mTabNum = (TextView) mBottomTabViews.get(i).findViewById(R.id.tab_item_num);
            View mTabMsg = mBottomTabViews.get(i).findViewById(R.id.tab_item_msg);
            if (index == i) {
                if (hasMsg) {
                    mTabNum.setVisibility(GONE);
                    mTabMsg.setVisibility(VISIBLE);
                } else {
                    mTabNum.setVisibility(GONE);
                    mTabMsg.setVisibility(GONE);
                }
            }

        }
    }

    /**
     * 清除对应位置的小红点的数量(包括数字和小红点）
     *
     * @param index
     */
    public void clearTabNumAndMsg(int index) {
        if (index >= mBottomTabViews.size() || mBottomTabViews == null || index < 0) return;
        TextView mTabNum = (TextView) mBottomTabViews.get(index).findViewById(R.id.tab_item_num);
        View mTabMsg = mBottomTabViews.get(index).findViewById(R.id.tab_item_msg);
        mTabNum.setText("");
        mTabNum.setVisibility(GONE);
        mTabMsg.setVisibility(GONE);
    }


    /**
     * 清除所有的小红点的数量(包括数字和小红点）
     */
    public void clearAllTabNumAndMsg() {
        for (int i = 0; i < mBottomTabViews.size(); i++) {
            TextView mTabNum = (TextView) mBottomTabViews.get(i).findViewById(R.id.tab_item_num);
            View mTabMsg = mBottomTabViews.get(i).findViewById(R.id.tab_item_msg);
            mTabNum.setText("");
            mTabNum.setVisibility(GONE);
            mTabMsg.setVisibility(GONE);
        }
    }


    /**
     * 隐藏tab
     */
    public void hideTabLayout() {
        if (getOrientation() == HORIZONTAL) {
            smoothScroll(this, getMeasuredHeight(), scrollDuration, "translationY");
        } else {
            smoothScroll(this, -getMeasuredWidth(), scrollDuration, "translationX");
        }
    }

    /**
     * 显示tab
     */
    public void showTabLayout() {
        if (getOrientation() == HORIZONTAL) {
            smoothScroll(this, 0, scrollDuration, "translationY");
        } else {
            smoothScroll(this, 0, scrollDuration, "translationX");
        }

    }

    /**
     * 显示隐藏动画
     *
     * @param target
     * @param delta
     * @param duration
     */
    private void smoothScroll(Object target, float delta, int duration, String trans) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(target, trans, delta);
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }

    /**
     * 每个tab触摸处理、比如按下效果 暂不处理
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    /**
     * 设置回调实现
     *
     * @param mOnTabChangeListener
     */
    public void setOnTabChangeListener(BottomTabChangeListener mOnTabChangeListener) {
        this.mBottomTabChangeListener = mOnTabChangeListener;
    }
}
