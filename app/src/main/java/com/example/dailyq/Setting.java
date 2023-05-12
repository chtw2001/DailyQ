package com.example.dailyq;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + "nam0221@sungkyul.ac.kr"));//이메일 주소 넣기
                intent.putExtra(Intent.EXTRA_SUBJECT, "DailyQ 관련 문의");//메일 제목 적용이 안되는데 이유를 모름
                startActivity(intent);
            }
        });

        this.btnWithdrawMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Setting.this)
                        .setTitle("회원탈퇴")
                        .setMessage("정말 회원탈퇴 하시겠습니까? 회원탈퇴 완료시 모든 데이터가 소멸되며 복구할 수 없습니다.")
                        .setNeutralButton("취소", null)
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Setting.this);
                                builder.setTitle("회원탈퇴를 위해 계정의 비밀번호를 입력해 주세요.");
                                final EditText input = new EditText(Setting.this);
                                builder.setView(input);
                                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String userInput = input.getText().toString();
                                        // 비밀번호 일치하는지 확인하고 회원탈퇴 처리하는 로직
                                    }
                                });
                                builder.setNegativeButton("취소", null);
                                builder.show();
                            }
                        })
                        .create()
                        .show();
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