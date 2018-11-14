package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.Nullable;

/*==================================================================================================
 * 내 정보 화면
 *=================================================================================================*/
public class MyInfoActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 삭제
        deleteStatusBar();
        setContentView(R.layout.activity_myinfo);
    }
}
