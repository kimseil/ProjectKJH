package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PlaceInfoActivity extends BaseActivity {
    RecyclerView rvPlaceInfo;
    LinearLayoutManager layoutManager;
    PlaceInfoAdapter adapter;
    TextView btnGuide;

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

        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guide();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    private void guide() {
        Intent intent = new Intent(PlaceInfoActivity.this, PlaceGuideActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }
}
