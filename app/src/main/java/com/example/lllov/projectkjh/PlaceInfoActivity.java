package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.Adapter.PlaceInfoAdapter;
import com.example.lllov.projectkjh.Adapter.PlaceRelevantAdapter;
import com.example.lllov.projectkjh.DTO.FavoritePlaceVO;
import com.example.lllov.projectkjh.DTO.PlaceInfoVO;

import org.parceler.Parcels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*==================================================================================================
 * 장소 리스트 클릭시 나타나는 장소 정보 화면
 *=================================================================================================*/
public class PlaceInfoActivity extends BaseActivity {
    TextView tvTitle, tvType, tvIntro, tvText;
    ImageView ivPicture;

    PlaceRelevantAdapter placeRelevantAdapter;
    PlaceInfoAdapter placeInfoAdapter;
    RecyclerView rvContent, rvPlaceRelevant;

    FavoritePlaceVO place;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);
        Toolbar toolbar = new ToolBar(this).setBack().setMap().setToolbar();

        tvTitle = findViewById(R.id.tvTitle);
        tvType = findViewById(R.id.tvType);
        tvIntro = findViewById(R.id.tvIntro);
        tvText = findViewById(R.id.tvText);
        ivPicture = findViewById(R.id.ivPicture);
        rvContent = findViewById(R.id.rvContent);
        rvPlaceRelevant = findViewById(R.id.rvPlaceRelevant);

        //parcelable 형태로 place 정보 받아옴
        Intent inIntent = getIntent();
        place = Parcels.unwrap(inIntent.getParcelableExtra("place"));
        String title = place.getTitle();
        String intro = place.getIntro();
        String imageUrl = place.getImageUrl();
        int type = place.getType();

        //맛집일 경우 text 제거, 관광일 경우 "주요장소"
        tvText.setText(type/100 == 2?"메뉴":"주요장소");

        //장소의 정보 리스트를 요청
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<PlaceInfoVO>> call1 = service.getPlaceInfoList(place.getId());

        call1.enqueue(new Callback<ArrayList<PlaceInfoVO>>() {
            @Override
            public void onResponse(Call<ArrayList<PlaceInfoVO>> call, Response<ArrayList<PlaceInfoVO>> response) {
                ArrayList<PlaceInfoVO> data = response.body();
                LinearLayoutManager layoutManager = new LinearLayoutManager(PlaceInfoActivity.this);

                placeInfoAdapter = new PlaceInfoAdapter(data, PlaceInfoActivity.this);
                rvContent.setLayoutManager(layoutManager);
                rvContent.setAdapter(placeInfoAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<PlaceInfoVO>> call, Throwable t) {

            }
        });

        //장소의 관련 정보 리스트(가로 리사이클러뷰에 들어감)를 가져옴
        Call<ArrayList<PlaceInfoVO>> call2 = service.getPlaceRelevantList(place.getId());

        call2.enqueue(new Callback<ArrayList<PlaceInfoVO>>() {
            @Override
            public void onResponse(Call<ArrayList<PlaceInfoVO>> call, Response<ArrayList<PlaceInfoVO>> response) {
                ArrayList<PlaceInfoVO> data = response.body();
                //Log.e("@@@@@@@@", new GsonBuilder().setPrettyPrinting().create().toJson(data));
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
        tvType.setText(PLACE_TYPE.get(type));
        tvIntro.setText(intro);
        //데이터가 있을 경우 이미지 로딩
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(this).load(imageUrl).into(ivPicture);
            ivPicture.setVisibility(View.VISIBLE);
        }
    }
}
