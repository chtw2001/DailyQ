package com.example.dailyq;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_diary_detail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_diary_detail extends Fragment {
 // 여기서 fragment_diary_detail로 가면 입력 받은 날짜로 맨 위에 띄워주고 저장하면 해당 배열에 저장하는 로직 짜야함
    Button button;
    Fragment fragment_calender;
    public void initialize(View view) {
        button = view.findViewById(R.id.close);
        fragment_calender = new calender();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.container, fragment_calender).commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_diary_detail, container, false);
        initialize(view);
        return view;
    }
}