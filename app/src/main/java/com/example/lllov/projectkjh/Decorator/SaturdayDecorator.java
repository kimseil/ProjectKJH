package com.example.lllov.projectkjh.Decorator;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

/*==================================================================================================
 * 달력의 각 날짜를 판단하여 토요일일 경우 파란색
 *=================================================================================================*/
public class SaturdayDecorator implements DayViewDecorator {

    private Calendar calendar = Calendar.getInstance();

    public SaturdayDecorator() {
    }


    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);
        return weekday == Calendar.SATURDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.BLUE));
    }
}
