package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.lllov.projectkjh.Adapter.LocationInfoAdapter;
import com.example.lllov.projectkjh.Adapter.PlaceRecommendAdapter;
import com.example.lllov.projectkjh.DTO.DTORecommned;
import com.example.lllov.projectkjh.DTO.LocationInfoVO;
import com.example.lllov.projectkjh.DTO.LocationVO;
import com.example.lllov.projectkjh.DTO.PlaceVO;
import com.google.gson.GsonBuilder;

import org.parceler.Parcels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceRecommendActivity extends BaseActivity {
    LinearLayoutManager layoutManager;
    RecyclerView rvRecommned;
    PlaceRecommendAdapter adapter;

    LocationVO location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_recommend);

        Toolbar toolBar = new ToolBar(this).setBack().setToolbar();

        Intent inIntent = getIntent();
        final int type = inIntent.getIntExtra("type", 1); // 1 - 관광지, 2 - 맛집
        location = Parcels.unwrap(inIntent.getParcelableExtra("location"));

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<PlaceVO>> call = service.getPlaceList(location.getId(), type, sUserId);
        Log.e("!!!!!!!!!!!!!", sUserId + "");

        call.enqueue(new Callback<ArrayList<PlaceVO>>() {
            @Override
            public void onResponse(Call<ArrayList<PlaceVO>> call, Response<ArrayList<PlaceVO>> response) {
                ArrayList<PlaceVO> data = response.body();

                Log.e("@@@@@@@@", new GsonBuilder().setPrettyPrinting().create().toJson(data));

                adapter = new PlaceRecommendAdapter(data, PlaceRecommendActivity.this);
                layoutManager = new LinearLayoutManager(PlaceRecommendActivity.this);
                rvRecommned = findViewById(R.id.rvCommend);
                rvRecommned.setLayoutManager(layoutManager);
                rvRecommned.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<PlaceVO>> call, Throwable t) {

            }
        });
    }
}
