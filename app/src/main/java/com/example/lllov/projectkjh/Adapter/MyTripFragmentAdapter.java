package com.example.lllov.projectkjh.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lllov.projectkjh.Fragment_favorite;
import com.example.lllov.projectkjh.Fragment_mytrip;

public class MyTripFragmentAdapter extends FragmentStatePagerAdapter {
    Context mcontext;
    public MyTripFragmentAdapter(FragmentManager fm,Context context){
        super(fm);
        this.mcontext = context;

    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:return new Fragment_mytrip();
            case 1: return new Fragment_favorite();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
