package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lllov.projectkjh.Adapter.PlaceInfoAdapter;

public class PlaceInfoActivity extends BaseActivity {
    RecyclerView rvPlaceInfo;
    LinearLayoutManager layoutManager;
    PlaceInfoAdapter adapter;
    TextView btnGuide, btnRestaurant, btnPlace;
    FloatingActionButton btnAddTravel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 삭제
        deleteStatusBar();
        setContentView(R.layout.activity_place_info);

        rvPlaceInfo = findViewById(R.id.rvPlaceInfo);
        layoutManager = new LinearLayoutManager(this);

        /*
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("추천여행지 " + i);
        }

        adapter = new PlaceInfoAdapter(list, this);
        */

        adapter = new PlaceInfoAdapter(this);
        rvPlaceInfo.setAdapter(adapter);
        rvPlaceInfo.setLayoutManager(layoutManager);

        btnGuide = findViewById(R.id.btnGuide);
        btnRestaurant = findViewById(R.id.btnRestaurant);
        btnPlace = findViewById(R.id.btnPlace);
        btnAddTravel = findViewById(R.id.btnAddTravel);

        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guide();
            }
        });
        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commend(0);
            }
        });
        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commend(1);
            }
        });
        btnAddTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTravel();
            }
        });
    }

    private void guide() {
        Intent intent = new Intent(PlaceInfoActivity.this, PlaceGuideActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    private void commend(int type) {
        Intent intent = new Intent(PlaceInfoActivity.this, PlaceRecommendActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    private void addTravel() {
        Intent intent = new Intent(PlaceInfoActivity.this, RegistrationTravelActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }
}
