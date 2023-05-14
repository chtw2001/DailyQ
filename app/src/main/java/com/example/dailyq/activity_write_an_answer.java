package com.example.dailyq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class activity_write_an_answer extends AppCompatActivity {

    ActionBar aBar;
    int year, month, day;
    EditText diary;

    int[] dayonmonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//    private static String[][] calender;
    Button prev, next;
    TextView date;
    String file, filename, id;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_an_answer);
        id = "1";


        // 인텐트에서 넘어온 데이터 추출
        Intent intent = getIntent();
        year = intent.getIntExtra("year", 0);
        month = intent.getIntExtra("month", 0);
        day = intent.getIntExtra("day", 0);

        prev = (Button) findViewById(R.id.prev);
        next = (Button) findViewById(R.id.next);
        date = (TextView) findViewById(R.id.date);
        date.setText(year + "/" + month + "/" + day);

        diary = (EditText) findViewById(R.id.diary);

        filename = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day);
        file = readDiary(filename);
        diary.setText(file);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day == 1) {
                    day = dayonmonth[month];
                    month -= 1;
                } else {
                    day -= 1;
                }
                date.setText(year + "/" + month + "/" + day);
                filename = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day);
                file = readDiary(filename);
                diary.setText(file);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day == dayonmonth[month - 1]) {
                    day = 1;
                    month += 1;
                } else {
                    day += 1;
                }
                date.setText(year + "/" + month + "/" + day);
                filename = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day);
                file = readDiary(filename);
                diary.setText(file);
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
            diary.setHint("오늘의 질문 : ");
        }
        return diaryStr;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.write_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int curld = item.getItemId();

        switch (curld){
            case R.id.edit_mode:
                Toast.makeText(this, "편집모드 입니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:
                try{
                    FileOutputStream outFs = new FileOutputStream(new File(getFilesDir()+"/"+id, filename));
                    String str = diary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.flush();
                    outFs.close();
                    Toast.makeText(getApplicationContext(), "일기가 저장되었습니다", Toast.LENGTH_SHORT).show();
                }catch(IOException e){
                    Toast.makeText(getApplicationContext(), "tq", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.friend_answer:
                Toast.makeText(this, "친구의 답변으로.", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}