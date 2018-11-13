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

/*==================================================================================================
 * 지역 정보 화면에서 맛집, 관광 버튼 클릭시 나타나는 지역 리스트 화면
 * 클릭시 해당하는 지역 정보 화면으로 이동
 * 이전 화면에서 맛집을 클릭했는지 관광을 클릭해야 하는지에 따라 정보가 달라지며 intent로 받은 type으로 판단
 *=================================================================================================*/
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

        //이전 화면에서 맛집을 선택했는지 관광을 선택했는지를 알 수 있는 type과, parcelable 형태의 지역 정보를 받아옴
        Intent inIntent = getIntent();
        final int type = inIntent.getIntExtra("type", 1); // 1 - 관광지, 2 - 맛집
        location = Parcels.unwrap(inIntent.getParcelableExtra("location"));

        //지역 리스트와 해당 회원의 찜 여부를 요청
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<PlaceVO>> call = service.getPlaceList(location.getId(), type, sUserId);

        call.enqueue(new Callback<ArrayList<PlaceVO>>() {
            @Override
            public void onResponse(Call<ArrayList<PlaceVO>> call, Response<ArrayList<PlaceVO>> response) {
                ArrayList<PlaceVO> data = response.body();

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
