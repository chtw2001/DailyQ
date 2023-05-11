package com.example.dailyq;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setting extends AppCompatActivity {
    Button btnModifySettings,btnLogout,btnDeveloperInfo,btnWithdrawMembership,btnDescription,btnSetLanguage;

    private void initialize(){

        this.btnModifySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new AlertDialog.Builder((Context)Setting.this)).setTitle("로그아웃").setMessage("정말 로그아웃 하시겠습니까?").setNeutralButton("취소",null).setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //로그아웃 후 메인화면으로 이동하도록 작성
                    }
                }).create().show();
            }
        });

        this.btnDeveloperInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.btnWithdrawMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.btnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.btnSetLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnModifySettings = (Button)findViewById(R.id.btnModifySettings);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnDeveloperInfo= (Button) findViewById(R.id.btnDeveloperInfo);
        btnWithdrawMembership = (Button) findViewById(R.id.btnWithdrawMembership);
        btnDescription = (Button) findViewById(R.id.btnDescription);
        btnSetLanguage = (Button) findViewById(R.id.btnSetLanguage);

        initialize();
    }


}