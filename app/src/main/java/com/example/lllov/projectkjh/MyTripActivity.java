package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MyTripActivity extends BaseActivity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytrip);

        Toolbar toolbar = new ToolBar(this).setBack().setToolbar();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<TripInfo> tripInfoArrayList = new ArrayList<>();
        tripInfoArrayList.add(new TripInfo(R.drawable.osaka, "오사카", "2015-2016"));
        tripInfoArrayList.add(new TripInfo(R.drawable.dokyo, "도쿄", "2016-2017"));

        MyTripAdapter myAdapter = new MyTripAdapter(tripInfoArrayList);

        mRecyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}
