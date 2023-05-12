package com.example.dailyq.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dailyq.R;
import com.example.dailyq.calender;
import com.example.dailyq.timeline;
import com.example.dailyq.ui.dashboard.DashboardFragment;
import com.example.dailyq.ui.notifications.NotificationsFragment;
import com.example.dailyq.ui.settings.SettingsFragment;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    Fragment fragment_calender;
    Fragment fragment_timeline;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TabLayout tabs = view.findViewById(R.id.tab1);

        fragment_calender = new calender();
        fragment_timeline = new timeline();

        tabs.addTab(tabs.newTab().setText("calender"));
        tabs.addTab(tabs.newTab().setText("timeline"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭: " + position);

                Fragment selected = null;
                if (position == 0) {
                    selected = fragment_calender;
                } else if (position == 1) {
                    selected = fragment_timeline;
                }
                getChildFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // 기본 프래그먼트 설정
        getChildFragmentManager().beginTransaction().add(R.id.container, fragment_calender).commit();

        return view;
    }

}
