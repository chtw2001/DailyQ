package com.example.dailyq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class past_answers extends AppCompatActivity {

    public int year, month, day;
    Button past_previous_answer;
    TextView pastDate, past_question_text;
    String file, filename, id, file_write, filename_write;
    EditText past_diary, past_question_diary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_answers);

        Intent intent = getIntent();
        year = intent.getIntExtra("year", 0);
        month = intent.getIntExtra("month", 0);
        day = intent.getIntExtra("day", 0);

        id = "1";

        pastDate = (TextView) findViewById(R.id.pastDate);
        pastDate.setText(year + "/" + month + "/" + day);

        past_question_text = (TextView) findViewById(R.id.past_question_text);
        past_question_text.setText(question_date.readData(past_answers.this, day));

        past_previous_answer = (Button) findViewById(R.id.past_previous_answer);

        past_diary = (EditText) findViewById(R.id.past_diary);
        past_question_diary = (EditText) findViewById(R.id.past_question_diary);

        //질문에 답하기 영역
        filename = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day);
        file = readDiary(filename);
        past_diary.setText(file);

        //일기 영역
        filename_write = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day)+"_"+"write";
        file_write = readDiary(filename_write);
        past_question_diary.setText(file_write);

        //이전 답변 눌렀을 때
        past_previous_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filename = Integer.toString(year)+"_"+Integer.toString(month-1)+"_"+Integer.toString(day);
                file = readDiary(filename);
                if ( file != null){
                    Intent intent  = new Intent(past_answers.this, past_answers.class);
                    intent.putExtra("year", year);
                    intent.putExtra("month", month-1);
                    intent.putExtra("day", day);
                    startActivity(intent);

                }else {

                    Toast.makeText(getApplicationContext(),"이전 답변 기록이 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public String readDiary(String fName) {
        String diaryStr = null;
        FileInputStream inFs;
        try{
            inFs = new FileInputStream(new File(getFilesDir()+"/"+id,fName));
            byte[] txt = new byte[1000];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
        }catch (IOException e){

        }
        return diaryStr;
    }
}