package com.example.dailyq.ui.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.dailyq.R;

public class SettingsFragment extends Fragment {
    private Button btnModifySettings, btnLogout, btnDeveloperInfo, btnWithdrawMembership, btnDescription, btnSetLanguage;

    private void initialize(View view) {
        btnModifySettings = view.findViewById(R.id.btnModifySettings);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnDeveloperInfo = view.findViewById(R.id.btnDeveloperInfo);
        btnWithdrawMembership = view.findViewById(R.id.btnWithdrawMembership);
        btnDescription = view.findViewById(R.id.btnDescription);
        btnSetLanguage = view.findViewById(R.id.btnSetLanguage);

        btnModifySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ModifySettings.class);
                startActivity(intent);
                //startActivity(new Intent(getApplicationContext(), ModifySettings.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("로그아웃")
                        .setMessage("정말 로그아웃 하시겠습니까?")
                        .setNeutralButton("취소", null)
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 로그아웃 후 메인화면으로 이동하는 로직
                            }
                        })
                        .create()
                        .show();
            }
        });

        btnDeveloperInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + "nam0221@sungkyul.ac.kr"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "DailyQ 관련 문의");
                startActivity(intent);
            }
        });

        btnWithdrawMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("회원탈퇴")
                        .setMessage("정말 회원탈퇴 하시겠습니까? 회원탈퇴 완료시 모든 데이터가 소멸되며 복구할 수 없습니다.")
                        .setNeutralButton("취소", null)
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("회원탈퇴를 위해 계정의 비밀번호를 입력해 주세요.");
                                final EditText input = new EditText(getContext());
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

        btnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 설명 화면으로 이동하는 로직 구현
            }
        });

        btnSetLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 언어 설정 기능 구현
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initialize(view);
        return view;
    }
}
