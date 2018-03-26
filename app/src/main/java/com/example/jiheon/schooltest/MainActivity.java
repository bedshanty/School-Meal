package com.example.jiheon.schooltest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import org.hyunjun.school.School;
import org.hyunjun.school.SchoolException;
import org.hyunjun.school.SchoolMenu;
import org.hyunjun.school.SchoolSchedule;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    MealParser mealParser;

    Button mealBtn;
    Button mealTimeBtn;
    DatePicker datePicker;
    TimePicker timePicker;

    // 사용자 학교의 정보

    public static School.Type   SCHOOL_TYPE     = School.Type.HIGH;
    public static School.Region SCHOOL_REGION   = School.Region.DAEGU;
    public static String        SCHOOL_CODE     = "D100000282";

    public static int BREAKFAST_TIME   = 720;
    public static int LUNCH_TIME       = 1240;
    public static int DINNER_TIME      = 1840;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mealBtn = findViewById(R.id.mealBtn);
        mealTimeBtn = findViewById(R.id.dateMealBtn);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);

        mealBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 시간 정보를 얻기 위한 DateTime 객체 생성
                DateTime dateTime = new DateTime();

                mealParser = new MealParser();
                mealParser.delegate = MainActivity.this;

                try {
                    // MealParser AsyncTask 로 급식 정보를 받아 menu 에 저장
                    mealParser.execute(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay(), dateTime.getMealType());
                } catch (Exception e) {
                    // AsyncTask 에러
                    Toast.makeText(MainActivity.this, "일시적인 오류입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mealTimeBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 현재 시간 정보를 얻기 위한 DateTime 객체 생성
                DateTime dateTime = new DateTime(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getHour(), timePicker.getMinute());

                mealParser = new MealParser();
                mealParser.delegate = MainActivity.this;

                try {
                    // MealParser AsyncTask 로 급식 정보를 받아 menu 에 저장
                    mealParser.execute(dateTime.getYear(), dateTime.getMonth() + 1, dateTime.getDay(), dateTime.getMealType());
                } catch (Exception e) {
                    // AsyncTask 에러
                    Toast.makeText(MainActivity.this, "일시적인 오류입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void processFinish(String menu) {
        // menu 가 null 인 경우 프로그램의 에러
        if(menu == null) {
            Toast.makeText(MainActivity.this, "Menu가 NULL 입니다.", Toast.LENGTH_SHORT).show();
        } else {
            // Intent 를 생성하여 MealActivity 로 메뉴 정보를 전송한다.
            Intent intent = new Intent(MainActivity.this, MealActivity.class);
            intent.putExtra("menu", menu);

            startActivity(intent);
        }
    }
}