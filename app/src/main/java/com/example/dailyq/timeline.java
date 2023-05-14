package com.example.dailyq;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class timeline extends Fragment {
    String id = "1";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        setupTimeline(view);
        return view;
    }

    private List<DiaryEntry> getAllDiary() {
        List<DiaryEntry> diaryEntries = new ArrayList<>();
        String[] fileList = new File(getActivity().getFilesDir(),id).list();

        for (String fileName : fileList) {
            if (fileName.matches("\\d+_\\d+_\\d+")) {
                try {
                    FileInputStream fis = new FileInputStream(new File(getActivity().getFilesDir() + File.separator + id, fileName));
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

    private void setupTimeline(View view) {
        List<DiaryEntry> diaryEntries = getAllDiary();
        Collections.sort(diaryEntries);

        RecyclerView recyclerView = view.findViewById(R.id.timelineRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        TimelineAdapter adapter = new TimelineAdapter(this, diaryEntries); // 수정된 부분
        recyclerView.setAdapter(adapter);

        // Scroll to the bottom of the RecyclerView
        if (diaryEntries.size() > 0) {
            recyclerView.scrollToPosition(diaryEntries.size() - 1);
        }
    }

    public void onDateSelected(CalendarDay date) {
        System.out.println(date); // 여기서 날짜 받으면 해당 날짜꺼 수정하는 액티비티로 전환 (캘린더에서 날짜 누르면 들어가는 페이지랑 동일)
    }
}
