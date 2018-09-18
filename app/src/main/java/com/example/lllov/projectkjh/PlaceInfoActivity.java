package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import java.util.ArrayList;

public class PlaceInfoActivity extends AppCompatActivity {
    RecyclerView rvPlaceInfo;
    LinearLayoutManager layoutManager;
    PlaceInfoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 삭제
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_place_info);

        Toolbar toolbar = new ToolBar(this).setTitle("Test").setBack().setToolbar();

        rvPlaceInfo = findViewById(R.id.rvPlaceInfo);
        layoutManager = new LinearLayoutManager(this);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("추천여행지 " + i);
        }

        adapter = new PlaceInfoAdapter(list, this);
        rvPlaceInfo.setAdapter(adapter);
        rvPlaceInfo.setLayoutManager(layoutManager);
    }
}
