package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.Adapter.PlaceInfoAdapter;
import com.example.lllov.projectkjh.Adapter.PlaceRelevantAdapter;
import com.example.lllov.projectkjh.DTO.PlaceInfoVO;
import com.example.lllov.projectkjh.DTO.PlaceVO;
import com.google.gson.GsonBuilder;

import org.parceler.Parcels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceInfoActivity extends BaseActivity {
    TextView tvTitle, tvIntro, tvText;
    ImageView ivPicture;

    PlaceRelevantAdapter placeRelevantAdapter;
    PlaceInfoAdapter placeInfoAdapter;
    RecyclerView rvContent, rvPlaceRelevant;

    PlaceVO place;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);
        Toolbar toolbar = new ToolBar(this).setBack().setMap().setToolbar();

        tvTitle = findViewById(R.id.tvTitle);
        tvIntro = findViewById(R.id.tvIntro);
        tvText = findViewById(R.id.tvText);
        ivPicture = findViewById(R.id.ivPicture);
        rvContent = findViewById(R.id.rvContent);
        rvPlaceRelevant = findViewById(R.id.rvPlaceRelevant);

        Intent inIntent = getIntent();
        place = Parcels.unwrap(inIntent.getParcelableExtra("place"));
        String title = place.getPlace().getTitle();
        String intro = place.getPlace().getIntro();
        String imageUrl = place.getPlace().getImageUrl();
        int type = place.getPlace().getType();

        tvText.setText(type/100 == 2?"":"주요장소");

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<PlaceInfoVO>> call1 = service.getPlaceInfoList(place.getPlace().getId());

        call1.enqueue(new Callback<ArrayList<PlaceInfoVO>>() {
            @Override
            public void onResponse(Call<ArrayList<PlaceInfoVO>> call, Response<ArrayList<PlaceInfoVO>> response) {
                ArrayList<PlaceInfoVO> data = response.body();
                Log.e("@@@g@@@@@", new GsonBuilder().setPrettyPrinting().create().toJson(data));
                LinearLayoutManager layoutManager = new LinearLayoutManager(PlaceInfoActivity.this);

                placeInfoAdapter = new PlaceInfoAdapter(data, PlaceInfoActivity.this);
                rvContent.setLayoutManager(layoutManager);
                rvContent.setAdapter(placeInfoAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<PlaceInfoVO>> call, Throwable t) {

            }
        });

        Call<ArrayList<PlaceInfoVO>> call2 = service.getPlaceRelevantList(place.getPlace().getId());

        call2.enqueue(new Callback<ArrayList<PlaceInfoVO>>() {
            @Override
            public void onResponse(Call<ArrayList<PlaceInfoVO>> call, Response<ArrayList<PlaceInfoVO>> response) {
                ArrayList<PlaceInfoVO> data = response.body();
                Log.e("@@@@@@@@", new GsonBuilder().setPrettyPrinting().create().toJson(data));
                LinearLayoutManager layoutManager = new LinearLayoutManager(PlaceInfoActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                placeRelevantAdapter = new PlaceRelevantAdapter(data, PlaceInfoActivity.this);
                rvPlaceRelevant.setLayoutManager(layoutManager);
                rvPlaceRelevant.setAdapter(placeRelevantAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<PlaceInfoVO>> call, Throwable t) {

            }
        });

        tvTitle.setText(title);
        tvIntro.setText(intro);
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(this).load(imageUrl).into(ivPicture);
            ivPicture.setVisibility(View.VISIBLE);
        }
    }
}
