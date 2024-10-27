package com.zwl.studyviewpagerdemo.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.IntDef;
import androidx.viewpager.widget.ViewPager;

public class DirectionalViewPager extends ViewPager {

    public static final int SWIPE_BOTH = 0;    // 允许双向滑动
    public static final int SWIPE_LEFT = 1;    // 仅允许左滑
    public static final int SWIPE_RIGHT = 2;   // 仅允许右滑
    public static final int SWIPE_NONE = 3;    // 禁止所有滑动

    @IntDef({SWIPE_BOTH, SWIPE_LEFT, SWIPE_RIGHT, SWIPE_NONE})
    public @interface SwipeDirection {}

    private @SwipeDirection int swipeDirection = SWIPE_BOTH;
    private float initialXValue;

    public DirectionalViewPager(Context context) {
        super(context);
    }

    public DirectionalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSwipeDirection(@SwipeDirection int direction) {
        this.swipeDirection = direction;
    }

    public int getSwipeDirection() {
        return swipeDirection;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (swipeDirection == SWIPE_NONE) {
            return false; // 禁止所有滑动
        }

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            initialXValue = ev.getX();
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            float diffX = ev.getX() - initialXValue;
            if (!isSwipeAllowed(diffX)) {
                return false; // 阻止滑动
            }
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (swipeDirection == SWIPE_NONE) {
            return false; // 禁止滑动
        }

        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            float diffX = ev.getX() - initialXValue;
            if (!isSwipeAllowed(diffX)) {
                return false; // 阻止滑动
            }
        }

        return super.onTouchEvent(ev);
    }

    private boolean isSwipeAllowed(float diffX) {
        switch (swipeDirection) {
            case SWIPE_BOTH:
                return true; // 允许双向滑动
            case SWIPE_LEFT:
                return diffX > 0; // 仅允许左滑（从右到左滑动）
            case SWIPE_RIGHT:
                return diffX < 0; // 仅允许右滑（从左到右滑动）
            default:
                return false;
        }
    }
}