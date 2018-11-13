package com.example.lllov.projectkjh;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

/*==================================================================================================
 * AppCompatActivity 를 상속받은 액티비티로
 * 공통적으로 필요한 상수, 메소드를 정의하고 각 액티비티에 상속시킴
 * 코드 재사용성 증가
 *=================================================================================================*/
public class BaseActivity extends AppCompatActivity {
    //날짜 포맷
    SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    SimpleDateFormat formatMD = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    SimpleDateFormat formatYear = new SimpleDateFormat("yyyy", Locale.KOREA);
    SimpleDateFormat formatMonth = new SimpleDateFormat("MM", Locale.KOREA);
    SimpleDateFormat formatDay = new SimpleDateFormat("dd", Locale.KOREA);

    public final static HashMap<Integer, String> PLACE_TYPE = new HashMap<>(); // 장소의 각 코드에 맞는 타입을 정의
    public static long sUserId; // 로그인 시 회원id

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PLACE_TYPE.put(101, "관광명소");
        PLACE_TYPE.put(102, "테마/체험");
        PLACE_TYPE.put(103, "쇼핑");
        PLACE_TYPE.put(201, "음식점");
        PLACE_TYPE.put(202, "카페/디저트");
        PLACE_TYPE.put(203, "술집/바");
    }

    //dp를 px로 변환 (dp를 입력받아 px을 리턴)
    public float dpToPixel(float dp){
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    //px을 dp로 변환 (px을 입력받아 dp를 리턴)
    public float pixelsToDp(float px){
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    //상단 스테이터스바 제거
    public void deleteStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //뒤로가기 버튼에 애니메이션 추가
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    //회원 id 리턴
    public long getUserId() {
        return sUserId;
    }
}
