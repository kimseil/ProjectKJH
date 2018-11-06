package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.lllov.projectkjh.Adapter.MyTripAdapter;
import com.example.lllov.projectkjh.DTO.DTOTripInfo;

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

        ArrayList<DTOTripInfo> tripInfoArrayList = new ArrayList<>();
        tripInfoArrayList.add(new DTOTripInfo(R.drawable.praha, "프라하", "2018.12.27 ~ 2019.01.02"));
        tripInfoArrayList.add(new DTOTripInfo(R.drawable.paris, "파리", "2019.01.02 ~ 2019.01.07"));

        MyTripAdapter myAdapter = new MyTripAdapter(tripInfoArrayList);

        mRecyclerView.setAdapter(myAdapter);

    }
}
