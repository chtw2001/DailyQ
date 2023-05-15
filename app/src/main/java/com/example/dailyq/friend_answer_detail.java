package com.example.dailyq;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class friend_answer_detail extends AppCompatActivity {

    TextView friend_answer_detail_title, friend_answer_detail_Q, friend_answer_detail_D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_answer_detail);

        Intent intent = getIntent();
        String receivedValue = intent.getStringExtra("name"); // 데이터 추출

        friend_answer_detail_title = (TextView) findViewById(R.id.friend_answer_detail_title);
        friend_answer_detail_Q = (TextView) findViewById(R.id.friend_answer_detail_Q);
        friend_answer_detail_D = (TextView) findViewById(R.id.friend_answer_detail_D);

        friend_answer_detail_title.setText(receivedValue);
    }
}
