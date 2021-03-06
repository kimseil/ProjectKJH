package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.example.lllov.projectkjh.Adapter.MyTripFragmentAdapter;
import com.example.lllov.projectkjh.Adapter.PlaceInfoAdapter;
import com.example.lllov.projectkjh.DTO.PlaceInfoVO;
import com.example.lllov.projectkjh.DTO.ResponseScheduleVO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*==================================================================================================
 * 네비게이션에서 내 여행 메뉴 클릭 시 나오는 화면의 activity
 * 내 여행, 찜한 장소 라는 두 개의 탭으로 이루어져 있음
 * 탭의 내용은 뷰패이저로 구성
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
        myTripFragmentAdapter = new MyTripFragmentAdapter(getSupportFragmentManager());
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
