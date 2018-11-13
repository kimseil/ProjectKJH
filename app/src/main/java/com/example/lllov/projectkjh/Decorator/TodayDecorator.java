package com.example.lllov.projectkjh.Decorator;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Date;

/*==================================================================================================
 * 달력의 날짜를 판단하여 오늘일 경우 초록색
 *=================================================================================================*/
public class TodayDecorator implements DayViewDecorator {
    private CalendarDay date;

    public TodayDecorator() {
        date = CalendarDay.today();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.GREEN));
    }

    public void setDate(Date date) {
        this.date = CalendarDay.from(date);
    }
}
