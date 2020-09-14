package com.example.xm2.utiles;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.List;

public class FragmentUtils {
    private static final String TAG = FragmentUtils.class.getSimpleName();

    /**
     * 获取界面中的某一个fragment
     *
     * @param clazz
     * @param context
     * @return
     */
    public static Fragment getFragment(Class<?> clazz, AppCompatActivity context) {
        List<Fragment> fragments = context.getSupportFragmentManager().getFragments();
        if (fragments != null && fragments.size() > 0) {
//            Fragment navHostFragment = (Fragment) fragments.get(0);
//            List<Fragment> childfragments = navHostFragment.getChildFragmentManager().getFragments();
            for (int j = 0; j < fragments.size(); j++) {
                Fragment fragment = fragments.get(j);
                if (fragment.getClass().isAssignableFrom(clazz)) {
                    Log.i(TAG, "getFragment1: " + fragment);
                    return fragment;
                }
            }
        }
        return null;
    }
}
