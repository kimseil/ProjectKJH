package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.Adapter.LocationGuideInfoAdapter;
import com.example.lllov.projectkjh.Adapter.LocationGuidePagerAdapter;
import com.example.lllov.projectkjh.DTO.DTOLocationGuide;
import com.example.lllov.projectkjh.DTO.LocationGuideInfoVO;
import com.example.lllov.projectkjh.DTO.LocationGuideVO;
import com.google.gson.GsonBuilder;

import org.parceler.Parcels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationGuideInfoActivity extends BaseActivity {
    Toolbar toolbar;

    TextView tvTitle;
    ImageView ivPicture;

    RecyclerView rvContent;
    LinearLayoutManager manager;
    LocationGuideInfoAdapter adapter;

    LocationGuideVO locationGuide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteStatusBar();
        setContentView(R.layout.activity_location_guide_info);

        //툴바
        toolbar = new ToolBar(this).setBack().setToolbar();

        //뷰 객체
        tvTitle = findViewById(R.id.tvTitle);
        ivPicture = findViewById(R.id.ivPicture);
        rvContent = findViewById(R.id.rvContent);

        //guide 정보를 intent로 받아옴
        Intent inIntent = getIntent();
        locationGuide = Parcels.unwrap(inIntent.getParcelableExtra("locationGuide"));

        //제목과 메인사진
        tvTitle.setText(locationGuide.getLocationGuide().getTitle());
        String imageUrl = locationGuide.getLocationGuide().getImageUrl();
        if(!TextUtils.isEmpty(imageUrl)) {
            Glide.with(this).load(imageUrl).into(ivPicture);
            ivPicture.setVisibility(View.VISIBLE);
        }

        //통신
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<LocationGuideInfoVO>> call = service.getLocationGuideInfoList(locationGuide.getLocationGuide().getId());

        call.enqueue(new Callback<ArrayList<LocationGuideInfoVO>>() {
            @Override
            public void onResponse(Call<ArrayList<LocationGuideInfoVO>> call, Response<ArrayList<LocationGuideInfoVO>> response) {
                ArrayList<LocationGuideInfoVO> data = response.body();
                Log.e("@@@@@@@@", new GsonBuilder().setPrettyPrinting().create().toJson(data));

                adapter = new LocationGuideInfoAdapter(data, LocationGuideInfoActivity.this);
                manager = new LinearLayoutManager(LocationGuideInfoActivity.this);
                rvContent.setLayoutManager(manager);
                rvContent.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<LocationGuideInfoVO>> call, Throwable t) {

            }
        });
    }
}
