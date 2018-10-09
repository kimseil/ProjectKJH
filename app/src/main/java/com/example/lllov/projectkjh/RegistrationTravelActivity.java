package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;


public class RegistrationTravelActivity extends AppCompatActivity {
    Toolbar toolbar;
    MaterialCalendarView cv;

    static RegistrationTravelActivity sRegistrationTravelActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 제거
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration_travel);

        //툴바
        toolbar = new ToolBar(this).setBack().setToolbar();
        //다른 액티비티에서 현재 액티비티 컨트롤용
        sRegistrationTravelActivity = this;

        //캘린더뷰
        cv = findViewById(R.id.cv);
        cv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

            }
        });
    }
}
