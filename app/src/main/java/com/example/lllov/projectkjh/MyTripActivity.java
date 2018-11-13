package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.lllov.projectkjh.Adapter.MyTripFragmentAdapter;

/*==================================================================================================
 *
 *=================================================================================================*/
public class MyTripActivity extends BaseActivity {
    private MyTripFragmentAdapter myTripFragmentAdapter;
    private ViewPager mViewPager;
    private TabLayout tabs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytrip);

        Toolbar toolbar = new ToolBar(this).setBack().setToolbar();

        tabs = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.viewPager);
        myTripFragmentAdapter = new MyTripFragmentAdapter(getSupportFragmentManager(),this);
        mViewPager.setAdapter(myTripFragmentAdapter);

        tabs.addTab(tabs.newTab().setText("내 여행"),0,true);
        tabs.addTab(tabs.newTab().setText("찜한 장소"),1);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
