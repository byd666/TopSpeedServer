package com.byd.james.topspeedserver.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by james on 2016/12/21.
 */

public class AdFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fList;
    public AdFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fList=list;
    }

    @Override
    public Fragment getItem(int position) {
        return fList.get(position);
    }

    @Override
    public int getCount() {
        return fList!=null?fList.size():0;
    }
}
