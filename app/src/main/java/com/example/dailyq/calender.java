package com.example.dailyq;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

    Fragment fragment_diary_detail;
    private void initialize(View view) {
        CalendarView calendarView = view.findViewById(R.id.calendarView);
        fragment_diary_detail = new fragment_diary_detail();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                // Do something when a date is clicked
                // You can access the year, month, and dayOfMonth parameters to get the selected date
                Log.d("MainActivity", "Selected date: " + year + "/" + (month+1) + "/" + day);
                // getChildFragmentManager().beginTransaction().replace(R.id.calendarView, fragment_diary_detail).commit();

                Intent intent = new Intent(getActivity(), activity_write_an_answer.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month+1);
                intent.putExtra("day", day);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_calender, container, false);
        initialize(view);
        return view;
    }
}