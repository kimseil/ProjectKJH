package com.example.lllov.projectkjh;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    SimpleDateFormat formatMD = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    SimpleDateFormat formatYear = new SimpleDateFormat("yyyy", Locale.KOREA);
    SimpleDateFormat formatMonth = new SimpleDateFormat("MM", Locale.KOREA);
    SimpleDateFormat formatDay = new SimpleDateFormat("dd", Locale.KOREA);


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

    public void deleteStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}
