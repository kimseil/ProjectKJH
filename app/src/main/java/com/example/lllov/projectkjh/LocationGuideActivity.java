package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.lllov.projectkjh.Adapter.LocationGuidePagerAdapter;
import com.example.lllov.projectkjh.Adapter.LocationInfoAdapter;
import com.example.lllov.projectkjh.DTO.LocationGuideVO;
import com.example.lllov.projectkjh.DTO.DTOLocationGuide;
import com.example.lllov.projectkjh.DTO.LocationInfoVO;
import com.example.lllov.projectkjh.DTO.LocationVO;
import com.google.gson.GsonBuilder;

import org.parceler.Parcels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*==================================================================================================
 * 가이드 탭 선택시 나오는 가이드 리스트를 보여주는 화면
 * 뷰페이저로 구성되어 정보, 맛집, 관광 탭으로 나뉨
 * 각 리스트 클릭시 해당 정보 화면을 보여줌
 *=================================================================================================*/
public class LocationGuideActivity extends BaseActivity {

    private LocationGuidePagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    Toolbar toolbar;

    private LocationVO location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteStatusBar();
        setContentView(R.layout.activity_location_guide);

        //툴바
        toolbar = new ToolBar(this).setBack().setToolbar();

        //지역 정보 가져옴
        Intent inIntent = getIntent();
        location = Parcels.unwrap(inIntent.getParcelableExtra("location"));

        //가이드 리스트와 해당 회원의 찜 여부 요청
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<LocationGuideVO>> call = service.getLocationGuideList(location.getId(), sUserId);

        call.enqueue(new Callback<ArrayList<LocationGuideVO>>() {
            @Override
            public void onResponse(Call<ArrayList<LocationGuideVO>> call, Response<ArrayList<LocationGuideVO>> response) {
                ArrayList<LocationGuideVO> data = response.body();

                //지역 정보와 서버에서 가져온 데이터를 새로운 객체에 결합하여 어댑터에 넘겨줌
                ArrayList<DTOLocationGuide> outData = new ArrayList<>();
                outData.add(new DTOLocationGuide(location, new ArrayList<LocationGuideVO>()));
                outData.add(new DTOLocationGuide(location, new ArrayList<LocationGuideVO>()));
                outData.add(new DTOLocationGuide(location, new ArrayList<LocationGuideVO>()));
                for(int i = 0; i < data.size(); i++) {
                    outData.get(data.get(i).getLocationGuide().getType() - 1).getData().add(data.get(i));
                }
                mSectionsPagerAdapter = new LocationGuidePagerAdapter(getSupportFragmentManager(), outData);

                mViewPager = findViewById(R.id.container);
                mViewPager.setAdapter(mSectionsPagerAdapter);

                TabLayout tabLayout = findViewById(R.id.tabs);

                mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
            }

            @Override
            public void onFailure(Call<ArrayList<LocationGuideVO>> call, Throwable t) {

            }
        });
    }
}
