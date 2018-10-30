package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lllov.projectkjh.Adapter.PlaceRecommendAdapter;
import com.example.lllov.projectkjh.DTO.DTORecommned;

import java.util.ArrayList;

public class PlaceRecommendActivity extends BaseActivity {
    LinearLayoutManager layoutManager;
    RecyclerView rvRecommned;
    PlaceRecommendAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_recommend);

        Toolbar toolBar = new ToolBar(this).setBack().setToolbar();

        rvRecommned = findViewById(R.id.rvCommend);
        layoutManager = new LinearLayoutManager(this);

        ArrayList<DTORecommned> data = new ArrayList<>();

        Intent inIntent = getIntent();
        if (inIntent.getIntExtra("type", 0) == 0) {
            data.add(new DTORecommned("이치란 도톤보리 점 본관", "레시피 선택이 가능한 오사카의 대표 라멘 맛집", "난바", "null"));
            data.add(new DTORecommned("카니도라쿠 도톤보리 본점", "코스로 즐기는 오사카 대표 게요리 식당", "난바", "null"));
            data.add(new DTORecommned("551호라이 에비스바시 본점", "60년 전통의 오사카 명물, 중국식 만두 전문점", "난바", "null"));
            data.add(new DTORecommned("원조 쿠시카츠 다루마 신세카이 총본점", "각종 꼬치 튀김을 맛볼 수 있는 원조 쿠시카츠 전문점", "", "null"));
            data.add(new DTORecommned("우오신 스시 본점", "빅사이즈 스시로 유명한 프렌차이즈 맛집", "우메다", "null"));
            data.add(new DTORecommned("이마이 도톤보리 본점", "오사카 3대 우동 맛집", "난바", "null"));
        } else {
            data.add(new DTORecommned("도톤보리", "화렿나 네온사인 속 맛집 탐험! 오사카 NO.1 관광 명소", "난바", "null"));
            data.add(new DTORecommned("유니버설 스튜디오 재팬", "가족 모두 즐길 수 있는 할리우드 영화 테마파크", "베이에어리어", "null"));
            data.add(new DTORecommned("오사카 성", "오사카의 상징으로 불리는 고궁", "", "null"));
            data.add(new DTORecommned("구로몬 시장", "다양한 먹거리가 있는 '오사카의 부엌'", "난바", "null"));
            data.add(new DTORecommned("신사이바시", "다양한 종류의 숍들이 있는 오사카 쇼핑의 중심지", "난바", "null"));
            data.add(new DTORecommned("난바", "오사카 여행의 시작과 끝", "난바", "null"));
            data.add(new DTORecommned("가이유칸", "다양한 해양 생물을 구경할 수 있는 세계 최대 규모의 아쿠아리움", "베이에어리어", "null"));
        }

        adapter = new PlaceRecommendAdapter(data, this);
        rvRecommned.setLayoutManager(layoutManager);
        rvRecommned.setAdapter(adapter);
    }
}
