package com.example.dailyq;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link calender#newInstance} factory method to
 * create an instance of this fragment.
 */

public class calender extends Fragment implements OnDateSelectedListener {
    Fragment fragment_diary_detail;
    private MaterialCalendarView calendarView;
    private HashSet<CalendarDay> datesWithContent;
    private int dotColor = Color.RED;
    String id = "1";
    private void initialize(View view) {
        calendarView = view.findViewById(R.id.calendarView);
        fragment_diary_detail = new fragment_diary_detail();
        calendarView.setOnDateChangedListener(this);
        datesWithContent = new HashSet<>(); // Initialize the set

    }
    public Boolean readDiary(String fName) {
        String diaryStr = null;
        FileInputStream inFs;
        try{
            inFs = getActivity().openFileInput(fName);
            byte[] txt = new byte[1000];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
        }catch (IOException e){
            return false;
        }
        if (diaryStr.length() == 0){
            return false;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_calender, container, false);
        initialize(view);
        getAllDiaryDates();
        updateRedDots();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        datesWithContent.clear();
        getAllDiaryDates();
        updateRedDots();
    }
    private void updateRedDots() {
        calendarView.removeDecorators();
        calendarView.addDecorator(new EventDecorator(dotColor, datesWithContent));
    }
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        Intent intent = new Intent(getActivity(), activity_write_an_answer.class);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        startActivity(intent);
        if (readDiary(Integer.toString(year)+"_"+Integer.toString(month)+"_"+Integer.toString(day))){
            datesWithContent.add(CalendarDay.from(year, month, day));
        }else{
            updateRedDots();
        }
    }
    public class EventDecorator implements DayViewDecorator {

        private final int color;
        private final HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(5, color));
        }
    }

    private void getAllDiaryDates() {
        String[] fileList = new File(getActivity().getFilesDir(),id).list();

        for (String fileName : fileList) {
            if (fileName.matches("\\d+_\\d+_\\d+")) {
                try {
                    //InputStreamReader inputStreamReader = new InputStreamReader(getActivity().openFileInput(fileName));
                    //BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    FileInputStream fis = new FileInputStream(new File(getActivity().getFilesDir() + File.separator + id, fileName));
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));

                    String line = bufferedReader.readLine();

                    if (line != null && !line.isEmpty()) {
                        String[] parts = fileName.split("_");
                        int year = Integer.parseInt(parts[0]);
                        int month = Integer.parseInt(parts[1]);
                        int day = Integer.parseInt(parts[2]);

                        datesWithContent.add(CalendarDay.from(year, month, day));
                    }
                } catch (IOException e) {
                    Log.e("calendar", "File read error: " + fileName, e);
                }
            }
        }
    }

}