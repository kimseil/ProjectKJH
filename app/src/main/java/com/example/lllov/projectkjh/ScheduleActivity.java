package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.lllov.projectkjh.Adapter.ScheduleAdapter;
import com.example.lllov.projectkjh.Adapter.ScheduleDayAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleActivity extends BaseActivity {
    ScheduleDayAdapter scheduleDayAdapter;
    ScheduleAdapter scheduleAdapter;
    LinearLayoutManager scheduleDayLayoutManager, scheduleLayoutManager;
    Toolbar toolbar;

    RecyclerView rvDay, rvSchedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 제거
        deleteStatusBar();
        setContentView(R.layout.activity_schedule);

        //툴바
        toolbar = new ToolBar(this).setBack().setToolbar();

        rvDay = findViewById(R.id.rvDay);
        scheduleDayLayoutManager = new LinearLayoutManager(this);
        scheduleDayLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //테스트용으로 임시 일정. 선택한 일정이 며칠인지.
        int dayNumber = 10;
        scheduleDayAdapter = new ScheduleDayAdapter(dayNumber, this);
        rvDay.setAdapter(scheduleDayAdapter);
        rvDay.setLayoutManager(scheduleDayLayoutManager);

        rvSchedule = findViewById(R.id.rvSchedule);
        scheduleLayoutManager = new LinearLayoutManager(this);
        ArrayList<Long> scheduleList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 10; i++) {
            scheduleList.add(calendar.getTimeInMillis());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        scheduleAdapter = new ScheduleAdapter(scheduleList, this);
        rvSchedule.setAdapter(scheduleAdapter);
        rvSchedule.setLayoutManager(scheduleLayoutManager);
    }
}
