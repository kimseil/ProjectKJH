package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.lllov.projectkjh.Adapter.AddPlaceAdapter;
import com.example.lllov.projectkjh.DTO.DTOAddPlace;

import java.util.ArrayList;

public class AddPlaceActivity extends BaseActivity {

    AddPlaceAdapter favoriteAdapter, recommendAdapter;
    LinearLayoutManager favoriteManager, recommnedManager;
    RecyclerView rvFavorite, rvRecommend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        Toolbar toolbar = new ToolBar(this).setBack().setToolbar();

        favoriteManager = new LinearLayoutManager(this);
        recommnedManager = new LinearLayoutManager(this);
        rvFavorite = findViewById(R.id.rvFavorite);
        rvRecommend = findViewById(R.id.rvRecommend);

        ArrayList<DTOAddPlace> recommendData = new ArrayList<>();
        recommendData.add(new DTOAddPlace("null", "스위소텔 난카이 오사카", "난바"));
        recommendData.add(new DTOAddPlace("null", "도톤보리", "난바"));
        recommendData.add(new DTOAddPlace("null", "이치란 도톤보리 점 본관", "난바"));
        recommendData.add(new DTOAddPlace("null", "오사카 도큐 REI 호텔", "우메다"));
        recommendData.add(new DTOAddPlace("null", "유니버설 스튜디오 재팬", "베이에어리어"));
        recommendData.add(new DTOAddPlace("null", "카니도라쿠 도톤보리 본점", "난바"));
        recommendData.add(new DTOAddPlace("null", "551호라이 에비스바시 본점", "난바"));
        recommendData.add(new DTOAddPlace("null", "호텔 일 그란데 우메다", "우메다"));
        recommendData.add(new DTOAddPlace("null", "오사카 성", ""));
        recommendData.add(new DTOAddPlace("null", "호텔 WBF 남바 이나리", "난바"));
        recommendAdapter = new AddPlaceAdapter(recommendData, this);
        rvRecommend.setLayoutManager(recommnedManager);
        rvRecommend.setAdapter(recommendAdapter);

        ArrayList<DTOAddPlace> favoriteData = new ArrayList<>();
        favoriteData.add(new DTOAddPlace("null", "오사카 도큐 REI 호텔", "우메다"));
        favoriteData.add(new DTOAddPlace("null", "유니버설 스튜디오 재팬", "베이에어리어"));
        favoriteData.add(new DTOAddPlace("null", "카니도라쿠 도톤보리 본점", "난바"));
        favoriteAdapter = new AddPlaceAdapter(favoriteData, this);
        rvFavorite.setLayoutManager(favoriteManager);
        rvFavorite.setAdapter(favoriteAdapter);
    }
}
