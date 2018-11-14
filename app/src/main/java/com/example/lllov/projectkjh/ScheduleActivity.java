package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.example.lllov.projectkjh.Adapter.ScheduleAdapter;
import com.example.lllov.projectkjh.Adapter.ScheduleDayAdapter;
import com.example.lllov.projectkjh.DTO.ScheduleDTO;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*==================================================================================================
 * 선택한 장소와 날짜를 토대로 만들어진 일정 화면
 * 상단에 선택한 날짜 만큼 day 버튼이 있으며 클릭시 해당 날짜 리스트로 스크롤링
 * 선택한 날짜에 맞는 일정 리스트가 만들어지며 각각에 장소와 메모를 추가할 수 있는 버튼 있음
 *=================================================================================================*/
public class ScheduleActivity extends BaseActivity {
    ScheduleDayAdapter scheduleDayAdapter;
    ScheduleAdapter scheduleAdapter;
    LinearLayoutManager scheduleDayLayoutManager, scheduleLayoutManager;
    Toolbar toolbar;
    Button btnAddPlace, btnAddMemo;

    RecyclerView rvDay, rvSchedule;

    ScheduleDTO schedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 제거
        deleteStatusBar();
        setContentView(R.layout.activity_schedule);
        //툴바
        toolbar = new ToolBar(this).setBack().setToolbar();

        //Parcelable 형태의 schedule 데이터를 가져옴
        Intent inIntent = getIntent();
        schedule = Parcels.unwrap(inIntent.getParcelableExtra("schedule"));
        long startDay = schedule.getStartDay();
        long endDay = schedule.getEndDay();
        //선택한 일정이 며칠인지.
        int dayNumber = (int)(((endDay - startDay) / (1000 * 60 * 60 * 24)) + 1);

        rvDay = findViewById(R.id.rvDay);
        scheduleDayLayoutManager = new LinearLayoutManager(this);
        //리사이클러뷰 가로 스크롤
        scheduleDayLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        scheduleDayAdapter = new ScheduleDayAdapter(dayNumber, this);
        rvDay.setAdapter(scheduleDayAdapter);
        rvDay.setLayoutManager(scheduleDayLayoutManager);

        rvSchedule = findViewById(R.id.rvSchedule);
        scheduleLayoutManager = new LinearLayoutManager(this);
        ArrayList<Long> scheduleList = new ArrayList<>();

        //선택한 날짜만큼 실제 날짜 리스트를 만들어줌
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(startDay));
        for (int i = 0; i < dayNumber; i++) {
            scheduleList.add(calendar.getTimeInMillis());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        scheduleAdapter = new ScheduleAdapter(scheduleList, this);
        rvSchedule.setAdapter(scheduleAdapter);
        rvSchedule.setLayoutManager(scheduleLayoutManager);
    }
}
