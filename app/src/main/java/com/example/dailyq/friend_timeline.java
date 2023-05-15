package com.example.dailyq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class friend_timeline extends AppCompatActivity implements TimelineAdapter.OnDateSelectedListener{
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        id = String.valueOf(intent.getStringExtra("id"));
        System.out.println(id + "123123");
        setContentView(R.layout.fragment_timeline);
        setupTimeline();


    }

    private List<DiaryEntry> getAllDiary() {
        List<DiaryEntry> diaryEntries = new ArrayList<>();
        String[] fileList = new File(getFilesDir(),id).list();

        if (fileList == null){
            Toast.makeText(getApplicationContext(), "친구의 답변이 비어있습니다.", Toast.LENGTH_SHORT).show();
            return null;
        }

        for (String fileName : fileList) {
            if (fileName.matches("\\d+_\\d+_\\d+")) {
                try {
                    FileInputStream fis = new FileInputStream(new File(getFilesDir() + File.separator + id, fileName));
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
                    String contents = bufferedReader.readLine();

                    if (contents != null && !contents.isEmpty()) {
                        int year = Integer.parseInt(fileName.split("_")[0]);
                        int month = Integer.parseInt(fileName.split("_")[1]);
                        int day = Integer.parseInt(fileName.split("_")[2]);
                        String date = String.format("%04d-%02d-%02d", year, month, day);

                        diaryEntries.add(new DiaryEntry(date, contents));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return diaryEntries;
    }

    private void setupTimeline() {
        List<DiaryEntry> diaryEntries = getAllDiary();
        Collections.sort(diaryEntries);

        RecyclerView recyclerView = findViewById(R.id.timelineRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        TimelineAdapter_activity adapter = new TimelineAdapter_activity(this, diaryEntries, this);
        recyclerView.setAdapter(adapter);

        // Scroll to the bottom of the RecyclerView
        if (diaryEntries.size() > 0) {
            recyclerView.scrollToPosition(diaryEntries.size() - 1);
        }
    }

    public void onDateSelected(CalendarDay date) {
        Toast.makeText(getApplicationContext(), "친구의 일기를 수정할 수 없습니다.", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, activity_write_an_answer.class);
//        intent.putExtra("year", date.getYear());
//        intent.putExtra("month", date.getMonth());
//        intent.putExtra("day", date.getDay());
//        startActivity(intent);
    }
}
