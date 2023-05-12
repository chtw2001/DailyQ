package com.example.dailyq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dailyq.ui.dashboard.DashboardFragment;
import com.example.dailyq.ui.home.HomeFragment;
import com.example.dailyq.ui.notifications.NotificationsFragment;
import com.example.dailyq.ui.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
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

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
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