package com.example.jiheon.schooltest;

/**
 * Created by JiHeon on 2018-03-20.
 */

public interface MealType {
    // 아침, 점심, 저녁, 다음 날 아침인지 구분
    // 오늘 저녁을 먹고 난 후인 경우 다음날 아침을 표시해준다
    int BREAKFAST = 1, LUNCH = 2, DINNER = 3, NEXT_BREAKFAST = 4;
}
