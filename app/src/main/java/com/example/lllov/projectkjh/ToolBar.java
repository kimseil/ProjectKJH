package com.example.lllov.projectkjh;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ToolBar {
    private BaseActivity mActivity;
    private Toolbar mToolbar;
    private ImageView btnBack;
    private TextView tvTitle;

    //툴바 호출한 액티비티 가져오기
    public ToolBar(BaseActivity activity) {
        mActivity = activity;

        mToolbar = mActivity.findViewById(R.id.toolbar);

        tvTitle = mActivity.findViewById(R.id.tvTitle);
        btnBack = mActivity.findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnBack();
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
        tvTitle.setText(title);

        return this;
    }

    //뒤로가기 버튼
    public ToolBar setBack() {
        btnBack.setVisibility(View.VISIBLE);

        return this;
    }

    private void onBtnBack() {
        mActivity.onBackPressed();
    }
}
