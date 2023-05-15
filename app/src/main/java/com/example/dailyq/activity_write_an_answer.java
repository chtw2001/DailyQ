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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class activity_write_an_answer extends AppCompatActivity {

    ActionBar aBar;
    int year, month, day;
    EditText diary, diary_write;

    int[] dayonmonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//    private static String[][] calender;
    Button prev, next;
    TextView date, question_text;
    String file, filename, id, file_write, filename_write;

    String[] question = {"가장 가고 싶은 여행지는 어디인가요?", "어제 잠들기 직전에 한 일을 적어주세요.", "당신의 낭만은 어떤 모습이었나요?", "당신은 무엇을 떠올리면 행복하다고 느끼나요?", "지금 이루어졌으면 하는 소원을 한 가지 적어주세요.", "지금 이루어졌으면 하는 소원을 한 가지 적어주세요."
            , "나를 가장 잘 표현할 수 있는 한 마디가 있을까요?", "실패를 경험해본 적이 있나요?", };
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

        diary_write = (EditText) findViewById(R.id.diary_write);
        diary = (EditText) findViewById(R.id.diary);
        question_text = (TextView) findViewById(R.id.question_text);

        filename_write = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day)+"_"+"write";
        file_write = readDiary(filename_write);
        diary_write.setText(file_write);

        filename = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day);
        file = readDiary(filename);
        diary.setText(file);

        question_text.setText(question[0]);
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
                //질문에 답하기 영역
                filename_write = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day)+"_"+"write";
                file_write = readDiary(filename_write);
                diary_write.setText(file_write);

                //일기 영역
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

                //질문에 답하기 영역
                filename_write = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day)+"_"+"write";
                file_write = readDiary(filename_write);
                diary_write.setText(file_write);

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

                    //질문 답변
                    FileOutputStream outFs_write = new FileOutputStream(new File(getFilesDir()+"/"+id, filename_write));
                    String str_write = diary_write.getText().toString();
                    outFs_write.write(str_write.getBytes());
                    outFs_write.flush();
                    outFs_write.close();

                    //일기
                    FileOutputStream outFs = new FileOutputStream(new File(getFilesDir()+"/"+id, filename));
                    String str = diary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.flush();
                    outFs.close();

                    Toast.makeText(getApplicationContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();

                }catch(IOException e){
                    Toast.makeText(getApplicationContext(), "tq", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.friend_answer:
                Toast.makeText(this, "친구의 답변으로.", Toast.LENGTH_SHORT).show();

                //답변한 친구 이름이 나열된 액티비티로 이동. 친구 리스트만 화면을 꽉 채움
                Intent intent  = new Intent(activity_write_an_answer.this, friend_answer_list.class);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}