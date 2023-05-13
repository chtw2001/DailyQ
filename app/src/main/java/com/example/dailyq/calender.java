package com.example.dailyq;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link calender#newInstance} factory method to
 * create an instance of this fragment.
 */
public class calender extends Fragment {
    private static final int[] day = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static String[][] calender;

    private void initialize(View view) {
        CalendarView calendarView = view.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                // Do something when a date is clicked
                // You can access the year, month, and dayOfMonth parameters to get the selected date
                Log.d("MainActivity", "Selected date: " + year + "/" + (month+1) + "/" + day);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (calender == null) {
            calender = new String[12][];
            for (int i = 0; i < calender.length; i++) {
                calender[i] = new String[day[i]];
                for (int j = 0; j < calender[i].length; j++) {
                    calender[i][j] = "";
                }
            }
        }
        View view =  inflater.inflate(R.layout.fragment_calender, container, false);
        initialize(view);
        return view;
    }
}