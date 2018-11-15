package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.lllov.projectkjh.DTO.LocationVO;
import com.example.lllov.projectkjh.DTO.ScheduleVO;
import com.example.lllov.projectkjh.Decorator.SaturdayDecorator;
import com.example.lllov.projectkjh.Decorator.SundayDecorator;
import com.example.lllov.projectkjh.Decorator.TodayDecorator;
import com.example.lllov.projectkjh.Decorator.YesterdayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;

import org.parceler.Parcels;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*==================================================================================================
 * 여행 일정을 등록하기 위해 날짜를 선택하는 화면
 * 캘린더는 material calendarview 라는 오픈 소스 활용
 * material calendarview 에서 제공하는 setDecorator를 활용해
 * 원하는 날짜에 점을 찍거나 색을 입히는 등의 커스텀 가능
 * 현재 월부터 표시하며, 현재 일 이전은 선택 불가 처리
 * 시작 날짜와 종료 날짜를 터치하면 자동으로 범위 선택됨
 *=================================================================================================*/
public class RegistrationTravelActivity extends BaseActivity {
    Toolbar toolbar;
    Button btnCommit;
    MaterialCalendarView cv;
    long startDay, endDay;

    LocationVO location;
    int locationId;

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

        Intent inIntent = getIntent();
        location = Parcels.unwrap(inIntent.getParcelableExtra("location"));
        locationId = location.getId();

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
                .setFirstDayOfWeek(Calendar.SUNDAY) // 일월화수목금토 형식
                .setMinimumDate(CalendarDay.from(nowCalendar.get(Calendar.YEAR), nowCalendar.get(Calendar.MONTH), 1)) // 현재 월 1일부터 표시
                .setMaximumDate(CalendarDay.from(nowCalendar.get(Calendar.YEAR) + 2, 11, 31)) // 현재로부터 2년 후까지 표시
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        cv.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE); // 시작, 끝 날짜 선택 모드

        cv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                //하나의 날짜만 선택시 버튼에 표시, 이미 선택된 날짜 선택시 클리어. 날짜 범위를 선택하지 않아 버튼 비활성화
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
                //날짜 범위 선택시 시작날짜와 끝날짜를 입력해주고 버튼 활성
                startDay = dates.get(0).getDate().getTime();
                endDay = dates.get(dates.size() - 1).getDate().getTime();
                btnCommit.setText(formatYMD.format(startDay) + " - " + formatMD.format(endDay) + " / 등록완료");
                btnCommit.setEnabled(true);
            }
        });

        updateDecorator();
    }

    //선택할 날짜 정보를 바탕으로 일정을 만들어 화면 이동
    public void commit() {
        //스케쥴 등록
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ScheduleVO> call = service.registrationTravel(startDay, endDay, locationId, sUserId);

        call.enqueue(new Callback<ScheduleVO>() {
            @Override
            public void onResponse(Call<ScheduleVO> call, Response<ScheduleVO> response) {
                ScheduleVO schedule = response.body();
                Intent intent = new Intent(RegistrationTravelActivity.this, ScheduleActivity.class);
                intent.putExtra("schedule", Parcels.wrap(schedule));
                intent.putExtra("location", Parcels.wrap(location));
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }

            @Override
            public void onFailure(Call<ScheduleVO> call, Throwable t) {

            }
        });
    }

    //decorator를 초기화, 업데이트 시켜줌
    public void updateDecorator() {
        cv.removeDecorators();
        cv.addDecorators(new SundayDecorator(), new SaturdayDecorator(), new TodayDecorator(), new YesterdayDecorator());
        cv.setDateSelected(cv.getSelectedDate(), false);
    }
}
