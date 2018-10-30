package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lllov.projectkjh.Adapter.PlaceInfoAdapter;
import com.example.lllov.projectkjh.DTO.DTOPlaceInfo;

import java.util.ArrayList;

public class PlaceInfoActivity extends BaseActivity {
    TextView tvLocation, tvIntro, tvTitle, tvContent, tvPlaceInfo;
    ImageView ivPicture, ivPicture2;

    LinearLayoutManager layoutManager;
    PlaceInfoAdapter adapter;
    RecyclerView rvPlaceInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);
        Toolbar toolbar = new ToolBar(this).setBack().setToolbar();

        tvLocation = findViewById(R.id.tvLocation);
        tvIntro = findViewById(R.id.tvIntro);
        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        tvPlaceInfo = findViewById(R.id.tvPlaceInfo);
        ivPicture = findViewById(R.id.ivPicture);
        ivPicture2 = findViewById(R.id.ivPicture2);

        rvPlaceInfo = findViewById(R.id.rvPlaceInfo);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        ArrayList<DTOPlaceInfo> data = new ArrayList<>();
        data.add(new DTOPlaceInfo("null", "해리포터 앤 더 포비든 저니", "해리포터의 호그와트 성과 금지된 숲을 모험해보는 4D 어트랙션(위저딩 월드 오브 해리포터)"));
        data.add(new DTOPlaceInfo("null", "미니언 메이헴", "미니언이 되어서 실험실을 탐험해 보는 시뮬레이션 어트랙션(미니언 파크)"));
        data.add(new DTOPlaceInfo("null", "어메이징 어드벤쳐 오브 스파이더맨 ", "건물을 넘어다니며 스파이더 맨과 모험해보는 4D 어트랙션(뉴욕)"));
        data.add(new DTOPlaceInfo("null", "할리우드 드림 더 라이드 백드롭", "역주행으로 최대의 스릴감을 느껴볼 수 있는 롤러코스터(할리우드)"));
        data.add(new DTOPlaceInfo("null", "죠스", "식인 상어 죠스를 피해 실감나게 탈출하는 보트 어트랙션(애머티 빌리지)"));
        data.add(new DTOPlaceInfo("null", "쥬라기 공원 더 라이드", "쥬라기 공원을 탐험하며 공룡을 만나는 보트 어트랙션(쥬라기 공원)"));
        data.add(new DTOPlaceInfo("null", "스페이스 판타지 더 라이드", "지구와 토성 등의 행성 사이로 우주를 여행하는 어트랙션(할리우드)"));
        adapter = new PlaceInfoAdapter(data, this);
        rvPlaceInfo.setLayoutManager(layoutManager);
        rvPlaceInfo.setAdapter(adapter);

        tvLocation.setText("유니버설 스튜디오 재팬");
        tvIntro.setText("가족 모두 즐길 수 있는 할리우드 영화 테마파크");
        tvTitle.setText("즐길거리가 다양한 대형 테마파크");
        tvContent.setText("유니버설 스튜디오 재팬은 다양한 할리우드 영화를 모티브로 만든 대규모 테마파크이다. 해리포터 시리즈, 스파이더맨, 미니언 파크 등 유명 영화 소재의 여러 라이드 어트랙션과 테마존, 각종 퍼레이드 및 공연을 즐길 수 있다.");
    }
}
