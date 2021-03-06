package com.example.lllov.projectkjh;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.Adapter.LocationInfoAdapter;
import com.example.lllov.projectkjh.DTO.LocationInfoVO;
import com.example.lllov.projectkjh.DTO.LocationVO;
import com.google.gson.GsonBuilder;

import org.parceler.Parcels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*==================================================================================================
 * 지역 선택시 지역 정보 리스트를 보여줌
 * 여행 계획을 추가할 수 있음
 * 상단의 가이드, 맛집, 관광 버튼을 클릭해 해당 정보 화면으로 이
 *=================================================================================================*/
public class LocationInfoActivity extends BaseActivity {
    RecyclerView rvLocationInfo;
    LinearLayoutManager layoutManager;
    LocationInfoAdapter adapter;

    TextView btnGuide, btnRestaurant, btnLocation;
    ImageView ivPicture;

    FloatingActionButton btnAddTravel;
    Toolbar tbar;

    private LocationVO location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 삭제
        deleteStatusBar();
        setContentView(R.layout.activity_location_info);

        rvLocationInfo = findViewById(R.id.rvLocationInfo);
        layoutManager = new LinearLayoutManager(this);

        //지역정보를 parcelable 형태로 받아옴.
        Intent inIntent = getIntent();
        location = Parcels.unwrap(inIntent.getParcelableExtra("location"));

        //지역 정보 리스트를 요청
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<LocationInfoVO>> call = service.getLocationInfoList(location.getId());

        call.enqueue(new Callback<ArrayList<LocationInfoVO>>() {
            @Override
            public void onResponse(Call<ArrayList<LocationInfoVO>> call, Response<ArrayList<LocationInfoVO>> response) {
                ArrayList<LocationInfoVO> data = response.body();

                adapter = new LocationInfoAdapter(data, LocationInfoActivity.this);
                rvLocationInfo.setAdapter(adapter);
                rvLocationInfo.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<ArrayList<LocationInfoVO>> call, Throwable t) {

            }
        });

        tbar = findViewById(R.id.tbar);
        ivPicture = findViewById(R.id.ivPicture);

        tbar.setTitle(location.getName());
        Glide.with(this).load(location.getImageUrl()).into(ivPicture);

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
        //맛집, 관광 버튼 클릭시 같은 화면에 다른 데이터를 구성하기 위해 매개변수로 타입 값을 넘겨줌
        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commend(2);
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

    //가이드 버튼
    private void guide() {
        Intent intent = new Intent(LocationInfoActivity.this, LocationGuideActivity.class);
        intent.putExtra("location", Parcels.wrap(location));
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    //맛집/관광 버튼
    private void commend(int type) {
        Intent intent = new Intent(LocationInfoActivity.this, PlaceRecommendActivity.class);
        intent.putExtra("location", Parcels.wrap(location));
        intent.putExtra("type", type);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    //여행 추가 버튼
    private void addTravel() {
        Intent intent = new Intent(LocationInfoActivity.this, RegistrationTravelActivity.class);
        intent.putExtra("location", Parcels.wrap(location));
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }
}
