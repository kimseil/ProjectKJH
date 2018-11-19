package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.lllov.projectkjh.Adapter.AddPlaceAdapter;
import com.example.lllov.projectkjh.DTO.FavoritePlaceVO;
import com.example.lllov.projectkjh.DTO.LocationVO;
import com.example.lllov.projectkjh.DTO.ResponseScheduleVO;
import com.example.lllov.projectkjh.DTO.ScheduleVO;

import org.parceler.Parcels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*==================================================================================================
 * 장소 추가 화면
 * 미리 찜해둔 장소를 선택하거나 등록되지 않은 장소를 직접 추가할 수 있음
 *=================================================================================================*/
public class AddPlaceActivity extends BaseActivity {

    AddPlaceAdapter favoriteAdapter, recommendAdapter;
    LinearLayoutManager favoriteManager, recommnedManager;
    RecyclerView rvFavorite, rvRecommend;

    public ResponseScheduleVO schedules;
    public LocationVO location;
    public ScheduleVO schedule;
    public long day;
    public int number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        Toolbar toolbar = new ToolBar(this).setBack().setToolbar();

        Intent inIntent = getIntent();
        schedules = Parcels.unwrap(inIntent.getParcelableExtra("schedules"));
        location = schedules.getLocation();
        schedule = schedules.getSchedule();
        day = inIntent.getLongExtra("day", 0);
        number = inIntent.getIntExtra("number", 0);

        favoriteManager = new LinearLayoutManager(this);
        recommnedManager = new LinearLayoutManager(this);
        rvFavorite = findViewById(R.id.rvFavorite);
        rvRecommend = findViewById(R.id.rvRecommend);

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<FavoritePlaceVO>> call = service.getFavoriteLocationPlaceList(sUserId, location.getId());

        call.enqueue(new Callback<ArrayList<FavoritePlaceVO>>() {
            @Override
            public void onResponse(Call<ArrayList<FavoritePlaceVO>> call, Response<ArrayList<FavoritePlaceVO>> response) {
                ArrayList<FavoritePlaceVO> data = response.body();

                favoriteAdapter = new AddPlaceAdapter(data, AddPlaceActivity.this);
                rvFavorite.setLayoutManager(favoriteManager);
                rvFavorite.setAdapter(favoriteAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<FavoritePlaceVO>> call, Throwable t) {

            }
        });

        Call<ArrayList<FavoritePlaceVO>> call2 = service.getPlaceAllList(location.getId());

        call2.enqueue(new Callback<ArrayList<FavoritePlaceVO>>() {
            @Override
            public void onResponse(Call<ArrayList<FavoritePlaceVO>> call, Response<ArrayList<FavoritePlaceVO>> response) {
                ArrayList<FavoritePlaceVO> data = response.body();

                recommendAdapter = new AddPlaceAdapter(data, AddPlaceActivity.this);
                rvRecommend.setLayoutManager(recommnedManager);
                rvRecommend.setAdapter(recommendAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<FavoritePlaceVO>> call, Throwable t) {

            }
        });
    }
}
