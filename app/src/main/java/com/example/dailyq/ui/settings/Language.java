package com.example.dailyq.ui.settings;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dailyq.R;

import java.util.Locale;

public class Language extends AppCompatActivity {

    private ListView languageList;
    final String[] languages = {"한국어", "English", "日本語", "Español", "Français", "Deutsch", "Italiano", "Português", "中文", "русский"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        languageList = findViewById(R.id.language_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, languages);
        languageList.setAdapter(adapter);

        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setLocale(languages[position]);
            }
        });
    }

    private void setLocale(String languageCode) {
        //실질적으로 언어를 변경하는 뭔가를 만들어야 함
        Toast.makeText(this, languageCode+" select", Toast.LENGTH_SHORT).show();
    }
}
