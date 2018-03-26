package com.example.jiheon.schooltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MealActivity extends AppCompatActivity {

    TextView mealView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        mealView = findViewById(R.id.mealView);

        Intent intent = getIntent();

        // getIntent() 로 받아온 MainActivity 의 메뉴 정보를 출력
        mealView.setText(intent.getStringExtra("menu"));
    }
}
