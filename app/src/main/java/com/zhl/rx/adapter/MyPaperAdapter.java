package com.zhl.rx.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhl.rx.fragment.MainFragment;
import com.zhl.rx.fragment.OtherFragment;

import java.util.ArrayList;

/**
 * 描述：
 * Created by zhaohl on 2019-1-21.
 */
public class MyPaperAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mDatas ;

    public MyPaperAdapter(FragmentManager fm) {
        this(fm,null);
    }

    public MyPaperAdapter(FragmentManager fm,ArrayList<String> mDatas){
        super(fm);
        this.mDatas = mDatas;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new MainFragment();
                break;
            case 1:
            case 2:
            case 3:
                fragment = OtherFragment.newInstance(mDatas.get(position));
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mDatas==null?0:mDatas.size();
    }
}
