package com.example.lllov.projectkjh.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lllov.projectkjh.FavoriteFragment;
import com.example.lllov.projectkjh.MytripFragment;
/*==================================================================================================
 * 네비게이션의 내 여행 어댑터
 *=================================================================================================*/

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
