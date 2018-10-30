package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lllov.projectkjh.Adapter.LocationInfoAdapter;

public class LocationInfoActivity extends BaseActivity {
    RecyclerView rvLocationInfo;
    LinearLayoutManager layoutManager;
    LocationInfoAdapter adapter;
    TextView btnGuide, btnRestaurant, btnLocation;
    FloatingActionButton btnAddTravel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 삭제
        deleteStatusBar();
        setContentView(R.layout.activity_location_info);

        rvLocationInfo = findViewById(R.id.rvLocationInfo);
        layoutManager = new LinearLayoutManager(this);

        /*
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("추천여행지 " + i);
        }

        adapter = new LocationInfoAdapter(list, this);
        */

        adapter = new LocationInfoAdapter(this);
        rvLocationInfo.setAdapter(adapter);
        rvLocationInfo.setLayoutManager(layoutManager);

        btnGuide = findViewById(R.id.btnGuide);
        btnRestaurant = findViewById(R.id.btnRestaurant);
        btnLocation = findViewById(R.id.btnLocation);
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
        btnLocation.setOnClickListener(new View.OnClickListener() {
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
        Intent intent = new Intent(LocationInfoActivity.this, LocationGuideActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    private void commend(int type) {
        Intent intent = new Intent(LocationInfoActivity.this, PlaceRecommendActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    private void addTravel() {
        Intent intent = new Intent(LocationInfoActivity.this, RegistrationTravelActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }
}
