package com.example.lllov.projectkjh;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ToolBar {
    private BaseActivity mActivity;
    private Toolbar mToolbar;
    private ImageView btnBack,btnMap;
    private TextView tvToolbarTitle;

    //툴바 호출한 액티비티 가져오기
    public ToolBar(BaseActivity activity) {
        mActivity = activity;

        mToolbar = mActivity.findViewById(R.id.toolbar);

        tvToolbarTitle = mActivity.findViewById(R.id.tvTitle);
        btnBack = mActivity.findViewById(R.id.btnBack);
        btnMap = mActivity.findViewById(R.id.btnMap);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnBack();
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnMap();
            }
        });
    }

    //툴바 생성
    public Toolbar setToolbar() {
        mActivity.setSupportActionBar(mToolbar);

        return mToolbar;
    }

    //타이틀
    public ToolBar setTitle(String title) {
        tvToolbarTitle.setText(title);

        return this;
    }

    //뒤로가기 버튼
    public ToolBar setBack() {
        btnBack.setVisibility(View.VISIBLE);

        return this;
    }

    //지도보기 버튼
    public ToolBar setMap(){
        btnMap.setVisibility(View.VISIBLE);

        return this;
    }

    private void onBtnBack() {
        mActivity.onBackPressed();
    }

    private void onBtnMap() {
        Intent intent = new Intent(mActivity,MapActivity.class);
        mActivity.startActivity(intent);
    }
}
