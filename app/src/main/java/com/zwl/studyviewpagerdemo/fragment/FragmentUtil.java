package com.zwl.studyviewpagerdemo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

/**
 * @author zwl
 * @describe 创建Fragment的Tag
 * @date on 2019-12-23
 */
public class FragmentUtil {

    private FragmentUtil() {
    }


    public static String makeFragmentTag(Class<? extends Fragment> cls) {
        return makeFragmentTag(cls, 0);
    }

    /**
     * 创建Fragment的Tag
     *
     * @param cls
     * @param position
     * @return fragment:xxx:x
     */
    public static String makeFragmentTag(Class<? extends Fragment> cls, int position) {
        return "fragment:" + cls.getSimpleName() + ":" + position;
    }

    /**
     * 获取ViewPager中Fragment的tag
     *
     * @param viewPagerId
     * @param itemId
     * @return
     */
    public static String makeViewPagerFragmentTag(int viewPagerId, long itemId) {
        return "android:switcher:" + viewPagerId + ":" + itemId;
    }

    public static <T extends Fragment> T findFragmentOrCreate(FragmentManager manager, Class<T> fragmentClass) {
        return findFragmentOrCreate(manager, fragmentClass, null, 0);
    }

    public static <T extends Fragment> T findFragmentOrCreate(FragmentManager manager, Class<T> fragmentClass, Bundle args) {
        return findFragmentOrCreate(manager, fragmentClass, args, 0);
    }

    public static <T extends Fragment> T findFragmentOrCreate(FragmentManager manager, Class<T> fragmentClass, int position) {
        return findFragmentOrCreate(manager, fragmentClass, null, position);
    }

    public static <T extends Fragment> T findFragmentOrCreate(FragmentManager manager, Class<T> fragmentClass, Bundle args, int position) {
        return findFragmentOrCreate(manager, makeFragmentTag(fragmentClass, position), fragmentClass, args);
    }

    public static <T extends Fragment> T findViewPagerFragmentOrCreate(FragmentManager manager, ViewPager viewPager, Class<T> fragmentClass, int position) {
        return findViewPagerFragmentOrCreate(manager, viewPager.getId(), fragmentClass, null, position);
    }

    public static <T extends Fragment> T findViewPagerFragmentOrCreate(FragmentManager manager, ViewPager viewPager, Class<T> fragmentClass, Bundle args, int position) {
        return findViewPagerFragmentOrCreate(manager, viewPager.getId(), fragmentClass, args, position);
    }

    public static <T extends Fragment> T findViewPagerFragmentOrCreate(FragmentManager manager, int viewPagerId, Class<T> fragmentClass, int position) {
        return findViewPagerFragmentOrCreate(manager, viewPagerId, fragmentClass, null, position);
    }

    public static <T extends Fragment> T findViewPagerFragmentOrCreate(FragmentManager manager, int viewPagerId, Class<T> fragmentClass, Bundle args, int position) {
        return findFragmentOrCreate(manager, makeViewPagerFragmentTag(viewPagerId, position), fragmentClass, args);
    }

    private static <T extends Fragment> T findFragmentOrCreate(FragmentManager manager, String fragmentTag, Class<T> fragmentClass, Bundle args) {
        T t = (T) manager.findFragmentByTag(fragmentTag);
        if (t == null) {
            try {
                t = fragmentClass.newInstance();
                if (args != null) {
                    t.setArguments(args);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }
}
