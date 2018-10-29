package com.example.lllov.projectkjh.Decorator;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

//리스트로 받은 날짜들에 색을 입혀줌
public class DayColorDecorator implements DayViewDecorator {
    private int color;
    private HashSet<CalendarDay> dates;

    public DayColorDecorator(int color, Collection<CalendarDay> dates) {
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
        view.addSpan(new ForegroundColorSpan(color));// 날짜에 색 입히기
    }
}
