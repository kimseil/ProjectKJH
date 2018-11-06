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

        Intent inIntent = getIntent();
        location = Parcels.unwrap(inIntent.getParcelableExtra("location"));

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<LocationGuideVO>> call = service.getLocationGuideList(location.getId());

        call.enqueue(new Callback<ArrayList<LocationGuideVO>>() {
            @Override
            public void onResponse(Call<ArrayList<LocationGuideVO>> call, Response<ArrayList<LocationGuideVO>> response) {
                ArrayList<LocationGuideVO> data = response.body();
                Log.e("@@@@@@@@", new GsonBuilder().setPrettyPrinting().create().toJson(data));

                ArrayList<DTOLocationGuide> outData = new ArrayList<>();
                outData.add(new DTOLocationGuide(location, new ArrayList<LocationGuideVO>()));
                outData.add(new DTOLocationGuide(location, new ArrayList<LocationGuideVO>()));
                outData.add(new DTOLocationGuide(location, new ArrayList<LocationGuideVO>()));
                for(int i = 0; i < data.size(); i++) {
                    outData.get(data.get(i).getType() - 1).getData().add(data.get(i));
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
