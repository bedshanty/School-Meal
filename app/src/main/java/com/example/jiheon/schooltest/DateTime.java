package com.example.jiheon.schooltest;

import java.util.Calendar;

/**
 * Created by JiHeon on 2018-03-20.
 */

public class DateTime {

    // 년, 월, 일 식사종류
    private int year;
    private int month;
    private int day;
    private int mealType;

    public DateTime() {

        // Calendar 객체를 통해 현재 년, 월, 일 계산
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);

        // 시간, 분 계산
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);

        // 시간을 이용해 MealType 계산
        setMealType(hour, min);
    }

    public DateTime(int year, int month, int day, int hour, int min) {
        this.year = year;
        this.month = month;
        this.day = day;

        setMealType(hour, min);
    }

    public void setMealType(int hour, int min) {
        // 시간 * 100 + 분 을 함으로써 24시간을 정수형으로 표현
        int sum = hour * 100 + min;

        // 사용자 학교의 아침, 점심, 저녁 시간에 따라 가져와야할 MealType 결정
        if(sum < MainActivity.BREAKFAST_TIME)
            mealType = MealType.BREAKFAST;

        else if(sum < MainActivity.LUNCH_TIME)
            mealType = MealType.LUNCH;

        else if(sum < MainActivity.DINNER_TIME)
            mealType = MealType.DINNER;

        else
            mealType = MealType.NEXT_BREAKFAST;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getMealType() {
        return mealType;
    }
}