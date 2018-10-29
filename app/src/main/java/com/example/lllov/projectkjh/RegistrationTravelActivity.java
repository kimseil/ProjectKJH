package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.lllov.projectkjh.Decorator.SaturdayDecorator;
import com.example.lllov.projectkjh.Decorator.SundayDecorator;
import com.example.lllov.projectkjh.Decorator.TodayDecorator;
import com.example.lllov.projectkjh.Decorator.YesterdayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class RegistrationTravelActivity extends BaseActivity {
    Toolbar toolbar;
    Button btnCommit;
    MaterialCalendarView cv;
    long startDay, endDay;

    static RegistrationTravelActivity sRegistrationTravelActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 제거
        deleteStatusBar();
        setContentView(R.layout.activity_registration_travel);

        //툴바
        toolbar = new ToolBar(this).setBack().setToolbar();
        //다른 액티비티에서 현재 액티비티 컨트롤용
        sRegistrationTravelActivity = this;

        btnCommit = findViewById(R.id.btnCommit);
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit();
            }
        });

        //캘린더뷰
        cv = findViewById(R.id.cv);
        Calendar nowCalendar = Calendar.getInstance();
        cv.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(nowCalendar.get(Calendar.YEAR), nowCalendar.get(Calendar.MONTH), 1))
                .setMaximumDate(CalendarDay.from(nowCalendar.get(Calendar.YEAR), 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        cv.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE);

        cv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if (selected) {
                    btnCommit.setText(formatYMD.format(date.getDate()));
                } else {
                    widget.clearSelection();
                    btnCommit.setText("가는날, 오는날을 선택해주세요");
                }
                btnCommit.setEnabled(false);
            }
        });
        cv.setOnRangeSelectedListener(new OnRangeSelectedListener() {
            @Override
            public void onRangeSelected(@NonNull MaterialCalendarView widget, @NonNull List<CalendarDay> dates) {
                startDay = dates.get(0).getDate().getTime();
                endDay = dates.get(dates.size() - 1).getDate().getTime();
                btnCommit.setText(formatYMD.format(startDay) + " - " + formatMD.format(endDay) + " / 등록완료");
                btnCommit.setEnabled(true);
            }
        });

        updateDecorator();
    }

    public void commit() {
        Intent intent = new Intent(RegistrationTravelActivity.this, ScheduleActivity.class);
        intent.putExtra("startDay", startDay);
        intent.putExtra("endDay", endDay);
        intent.putExtra("dayNumber", (int)((endDay - startDay) / (1000 * 60 * 60 * 24)) + 1);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    public void updateDecorator() {
        cv.removeDecorators();
        cv.addDecorators(new SundayDecorator(), new SaturdayDecorator(), new TodayDecorator(), new YesterdayDecorator());
        cv.setDateSelected(cv.getSelectedDate(), false);
    }
}
