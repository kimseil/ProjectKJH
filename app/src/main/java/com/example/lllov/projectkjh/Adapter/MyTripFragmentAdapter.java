package com.example.lllov.projectkjh.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.FavoritePlaceVO;
import com.example.lllov.projectkjh.DTO.ResponseScheduleVO;
import com.example.lllov.projectkjh.FavoriteFragment;
import com.example.lllov.projectkjh.MytripFragment;

import java.util.ArrayList;

public class MyTripFragmentAdapter extends FragmentStatePagerAdapter {

    public MyTripFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MytripFragment();
            case 1:
                return new FavoriteFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
