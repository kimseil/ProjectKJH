package com.example.lllov.projectkjh.Decorator;

import java.util.Calendar;

public class MyCalendar {
    public static boolean isYesterday(Calendar calendar) {
        int calYear = calendar.get(Calendar.YEAR);
        int calMonth = calendar.get(Calendar.MONTH);
        int calDay = calendar.get(Calendar.DAY_OF_MONTH);

        //현재 날짜
        Calendar now = Calendar.getInstance();
        int nowYear = now.get(Calendar.YEAR);
        int nowMonth = now.get(Calendar.MONTH);
        int nowDay = now.get(Calendar.DAY_OF_MONTH);

        //대상 날짜와 현재 날짜를 비교
        if(calYear == nowYear && calMonth == nowMonth && calDay < nowDay) {
            return true;
        } else {
            return false;
        }
    }
}
