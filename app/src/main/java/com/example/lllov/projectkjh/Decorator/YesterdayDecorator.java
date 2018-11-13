package com.example.lllov.projectkjh.Decorator;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*==================================================================================================
 * 달력의 날짜를 판단하여 지난 날짜일 경우 회색, 선택 불가 처리
 *=================================================================================================*/
public class YesterdayDecorator implements DayViewDecorator {
    private Calendar calendar = Calendar.getInstance();

    public YesterdayDecorator() {
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        //calendar에 decorate대상 날짜를 입력
        day.copyTo(calendar);

        //대상 날짜와 현재 날짜를 비교하여 이전 날짜들만 decorate
        return MyCalendar.isYesterday(calendar);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.GRAY));
        view.setDaysDisabled(true);
    }
}
