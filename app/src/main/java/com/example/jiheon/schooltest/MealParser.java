package com.example.jiheon.schooltest;

import android.os.AsyncTask;
import org.hyunjun.school.School;
import org.hyunjun.school.SchoolException;
import org.hyunjun.school.SchoolMenu;

/**
 * Created by JiHeon on 2018-03-20.
 */

public class MealParser extends AsyncTask<Integer, Void, String> {

    public AsyncResponse delegate = null;

    @Override
    protected String doInBackground(Integer... params) {

        String menu = null;

        try {
            // API 기본 설정
            School api = new School(MainActivity.SCHOOL_TYPE, MainActivity.SCHOOL_REGION, MainActivity.SCHOOL_CODE);

            SchoolMenu sm;

            // API 의 DAY 가 0 부터 시작하므로 DAY - 1
            // NEXT_BREAKFAST 인 경우 다음 날 아침을 가져와야 한다
            if(params[3] == MealType.NEXT_BREAKFAST)
                sm = api.getMonthlyMenu(params[0], params[1]).get(params[2]);
            else
                sm = api.getMonthlyMenu(params[0], params[1]).get(params[2] - 1);

            // params[3], MealType 에 따라 적절한 메뉴를 받아온다
            switch(params[3]) {
                case MealType.BREAKFAST:
                case MealType.NEXT_BREAKFAST:
                    menu = sm.breakfast;
                    break;

                case MealType.LUNCH:
                    menu = sm.lunch;
                    break;

                case MealType.DINNER:
                    menu = sm.dinner;
                    break;
            }

        } catch (SchoolException e) {
            return null;
        }

        return menu;
    }

    @Override
    protected void onPostExecute(String menu) {
        delegate.processFinish(menu);
    }
}
