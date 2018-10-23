package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class PlaceGuideActivity extends BaseActivity {

    private PlaceGuideAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteStatusBar();
        setContentView(R.layout.activity_place_guide);

        //툴바
        toolbar = new ToolBar(this).setBack().setToolbar();

        ArrayList<DTOPlaceGuide> data = new ArrayList<>();

        data.get(0).setPicture("p1.png");
        data.get(0).setTitle("title1");
        ArrayList<DTOInfo> info = new ArrayList<>();
        info.get(0).setTitle("제목");
        info.get(0).setContent("내용");
        data.get(0).setInfo(info);

        data.get(1).setPicture("p1.png");
        data.get(1).setTitle("title1");
        info = new ArrayList<>();
        info.get(0).setTitle("제목");
        info.get(0).setContent("내용");
        data.get(1).setInfo(info);

        data.get(2).setPicture("p1.png");
        data.get(2).setTitle("title1");
        info = new ArrayList<>();
        info.get(0).setTitle("제목");
        info.get(0).setContent("내용");
        data.get(2).setInfo(info);
        mSectionsPagerAdapter = new PlaceGuideAdapter(getSupportFragmentManager(), data);

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}
