package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.lllov.projectkjh.Adapter.PlaceGuidePagerAdapter;
import com.example.lllov.projectkjh.DTO.DTOInfo;
import com.example.lllov.projectkjh.DTO.DTOPlaceGuide;

import java.util.ArrayList;

public class PlaceGuideActivity extends BaseActivity {

    private PlaceGuidePagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteStatusBar();
        setContentView(R.layout.activity_place_guide);

        //툴바
        toolbar = new ToolBar(this).setBack().setToolbar();

        //
        ArrayList<DTOPlaceGuide> data = new ArrayList<>();
        /*
        ArrayList<DTOInfo> info1 = new ArrayList<>();
        ArrayList<DTOInfo> info2 = new ArrayList<>();
        ArrayList<DTOInfo> info3 = new ArrayList<>();
        info1.add(new DTOInfo("제목", "내용"));
        info2.add(new DTOInfo("제목", "내용"));
        info3.add(new DTOInfo("제목", "내용"));
        */
        ArrayList<DTOInfo> info = new ArrayList<>();
        info.add(new DTOInfo("OOO 여행 기초 정보", "여행을 떠나기 전 미리 알아두면 좋을 OOO의 기본적인 정보들"));
        info.add(new DTOInfo("월별로 알아보는 OOO의 날씨", "여행가기에 가장 좋은 시기는 언제? OOO의 월별 기온과 강우량"));
        info.add(new DTOInfo("OOO 대표 축제 BEST 5", "당신의 여행을 반짝이게 해줄 화려한 축제들"));
        data.add(new DTOPlaceGuide("p1.png", "정말 유용한\nOOO 정보와 팁", info));

        info = new ArrayList<>();
        info.add(new DTOInfo("OOO에서 꼭 들러야 할 명소 BEST 10", "OOO의 치명적인 아름다움을 간직한 대표 명소 총집합"));
        info.add(new DTOInfo("OOOO 이야기", "알고 가면 더욱 감동적인 OO의 역사"));
        info.add(new DTOInfo("1000년의 고도, OOO 성", "OOO 여행의 하이라이트, OOO 성 이렇게 즐기자"));
        data.add(new DTOPlaceGuide("p2.png", "볼거리, 즐길거리의\n모든 것", info));

        info = new ArrayList<>();
        info.add(new DTOInfo("OOO에서 꼭 먹어 봐야 할 음식 10", "지금까지 맛보지 못했던 미식 기행"));
        info.add(new DTOInfo("OO 맥주 가이드", "세계에서 맥주를 가장 사랑하는 나라! OO 맥주 정복"));
        info.add(new DTOInfo("OOO 추천 레스토랑", "여행자라면 반드시 가야할 구시가 주변 레스토랑"));
        data.add(new DTOPlaceGuide("p3.png", "OOO\n먹킷리스트", info));

        mSectionsPagerAdapter = new PlaceGuidePagerAdapter(getSupportFragmentManager(), data);

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }
}
