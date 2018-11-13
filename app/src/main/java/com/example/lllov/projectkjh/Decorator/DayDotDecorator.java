package com.example.lllov.projectkjh.Decorator;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

/*==================================================================================================
 * 리스트 형태로 날짜를 받아 달력의 해당 날짜에 점을 찍어줌
 *=================================================================================================*/
public class DayDotDecorator implements DayViewDecorator {
    private int color;
    private HashSet<CalendarDay> dates;

    public DayDotDecorator(int color, Collection<CalendarDay> dates) {
        this.color = color;
        this.dates = new HashSet<>(dates);
    }

    //해당 날짜 데코레이트 조건 맞는지 여부
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    //데코레이트
    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, color)); // 날짜 밑에 점
    }
}
