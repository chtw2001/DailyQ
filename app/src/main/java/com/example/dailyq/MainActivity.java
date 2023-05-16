package com.example.dailyq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dailyq.ui.dashboard.DashboardFragment;
import com.example.dailyq.ui.home.HomeFragment;
import com.example.dailyq.ui.notifications.NotificationsFragment;
import com.example.dailyq.ui.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btnSetting;

    //BottomNavigationView bottomNavigationView;
    calender fragment_calender;
    timeline fragment_timeline;
    DashboardFragment fragment_dashboard;
    HomeFragment fragment_home;
    NotificationsFragment fragment_notifications;
    SettingsFragment fragment_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        btnSetting = (Button) findViewById(R.id.btnSetting);
//        btnSetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),Setting.class);
//                startActivity(intent);
//
//            }
//        });

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);

        //질문 데이터 추가. 앱 빌드 시 처음 한 번만 실행
        question_date.initialize(getApplicationContext());
        
        File folder = new File(getFilesDir(),"1");
        if (!folder.exists()) {
            folder.mkdir(); //폴더 생성
        }

        fragment_dashboard = new DashboardFragment();
        fragment_home = new HomeFragment();
        fragment_notifications = new NotificationsFragment();
        fragment_settings = new SettingsFragment();

        fragment_calender = new calender();
        fragment_timeline = new timeline();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_home).commit();

        BottomNavigationView bottomNav = findViewById(R.id.tab2);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_home).commit();
                        break;
                    case R.id.navigation_dashboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_dashboard).commit();
                        break;
                    case R.id.navigation_notifications:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_notifications).commit();
                        break;
                    case R.id.navigation_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_settings).commit();
                        break;
                }
                return true;
            }
        });
    }
}