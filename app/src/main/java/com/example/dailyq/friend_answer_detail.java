package com.example.dailyq;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class friend_answer_detail extends AppCompatActivity {

    TextView friend_answer_detail_title, friend_answer_detail_Q, friend_answer_detail_D;
    public int year, month, day;
    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_answer_detail);

        Intent intent = getIntent();
        String receivedValue = intent.getStringExtra("name"); // 데이터 추출
        year = intent.getIntExtra("year", 0);
        month = intent.getIntExtra("month", 0);
        day = intent.getIntExtra("day", 0);
        // id = intent.getStringExtra("id"); // id받아와서 files/id/year_month_day 찾아야 함

        friend_answer_detail_title = (TextView) findViewById(R.id.friend_answer_detail_title);
        friend_answer_detail_Q = (TextView) findViewById(R.id.friend_answer_detail_Q);
        friend_answer_detail_D = (TextView) findViewById(R.id.friend_answer_detail_D);

        friend_answer_detail_title.setText(receivedValue);
    }

}
