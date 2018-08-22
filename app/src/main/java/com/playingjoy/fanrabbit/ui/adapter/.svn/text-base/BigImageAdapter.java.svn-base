package com.playingjoy.fanrabbit.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.playingjoy.fanrabbit.ui.fragment.comm.BigImageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ly
 * @date 2017/12/26
 */

public class BigImageAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> mFragmentList;

    public BigImageAdapter(FragmentManager fm, List<String> types) {
        super(fm);
        updateData(types);
    }

    public void updateData(List<String> dataList) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0, size = dataList.size(); i < size; i++) {
            fragments.add(BigImageFragment.newInstance(dataList.get(i)));
        }
        setFragmentList(fragments);
    }

    private void setFragmentList(ArrayList<Fragment> fragmentList) {
        if (this.mFragmentList != null) {
            mFragmentList.clear();
        }
        this.mFragmentList = fragmentList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.mFragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
}