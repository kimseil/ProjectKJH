package com.example.lllov.projectkjh;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ToolBar {
    private AppCompatActivity mActivity;
    private Toolbar mToolbar;

    //툴바 호출한 액티비티 가져오기
    public ToolBar(AppCompatActivity activity) {
        mActivity = activity;
    }

    //툴바 생성
    public void setToolbar() {
        mToolbar = mActivity.findViewById(R.id.toolbar);
        mActivity.setSupportActionBar(mToolbar);
    }

    //타이틀
    public void setTitle(String title) {
        TextView tvTitle = mActivity.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
    }

    //뒤로가기 버튼 생성
    public void setBack() {
        ImageView btnBack = mActivity.findViewById(R.id.btnBack);
        btnBack.setClickable(true);
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.onBackPressed();
            }
        });
    }

    //햄버거 버튼 생성
    public void setMenu() {
        ImageView btnMenu = mActivity.findViewById(R.id.btnMenu);
        btnMenu.setClickable(true);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //햄버거
            }
        });
    }
}
