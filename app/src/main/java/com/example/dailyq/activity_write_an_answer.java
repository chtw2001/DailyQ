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
import android.view.inputmethod.InputMethodManager;
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
import java.util.Random;

public class activity_write_an_answer extends AppCompatActivity {

    ActionBar aBar;
    public int year, month, day;
    EditText diary, question_diary;

    int[] dayonmonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    //    private static String[][] calender;
    Button prev, next;
    TextView date, question_text;
    String file, filename, id, file_write, filename_write;
    boolean isEditMode;
    int randomNumber;

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

        question_diary = (EditText) findViewById(R.id.question_diary);
        diary = (EditText) findViewById(R.id.diary);
        question_text = (TextView) findViewById(R.id.question_text);

        filename_write = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day)+"_"+"write";
        file_write = readDiary(filename_write);
        question_diary.setText(file_write);

        filename = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day);
        file = readDiary(filename);
        diary.setText(file);

        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        question_text.setText(question_date.readData(this, randomNumber));

        isEditMode = true; //편집모드 아이콘 Visible 초기값
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

                Random random = new Random();
                randomNumber = random.nextInt(100) + 1;
                Context context = v.getContext();
                question_text.setText(question_date.readData(context, randomNumber));

                //질문에 답하기 영역
                filename = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day);
                file = readDiary(filename);
                diary.setText(file);

                //일기 영역
                filename_write = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day)+"_"+"write";
                file_write = readDiary(filename_write);
                question_diary.setText(file_write);

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

                Random random = new Random();
                randomNumber = random.nextInt(100) + 1;
                Context context = v.getContext();
                question_text.setText(question_date.readData(context, randomNumber));

                //질문에 답하기 영역
                filename = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day);
                file = readDiary(filename);
                diary.setText(file);

                //일기 영역
                filename_write = Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day)+"_"+"write";
                file_write = readDiary(filename_write);
                question_diary.setText(file_write);

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

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem editModeItem = menu.findItem(R.id.edit_mode);
        MenuItem saveItem = menu.findItem(R.id.save);

        LinearLayout writeLayout = findViewById(R.id.write);

        editModeItem.setVisible(isEditMode);
        saveItem.setVisible(!isEditMode);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int curld = item.getItemId();
        LinearLayout write_layout = findViewById(R.id.write);

        switch (curld){
            case R.id.edit_mode:
                isEditMode = false;
                invalidateOptionsMenu();

                hideKeyboard();

                Toast.makeText(this, "편집모드 입니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:
                try{
                    isEditMode = true;
                    invalidateOptionsMenu();

                    //질문 답변
                    FileOutputStream outFs_write = new FileOutputStream(new File(getFilesDir()+"/"+id, filename_write));
                    String str_write = question_diary.getText().toString();
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
                    Toast.makeText(getApplicationContext(), "오류입니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.friend_answer:
                Toast.makeText(this, "친구의 답변으로.", Toast.LENGTH_SHORT).show();

                //답변한 친구 이름이 나열된 액티비티로 이동. 친구 리스트만 화면을 꽉 채움
                Intent intent  = new Intent(activity_write_an_answer.this, friend_answer_list.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 키보드 숨기기
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

