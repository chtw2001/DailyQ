package com.example.dailyq.ui.settings;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dailyq.R;

import java.io.IOException;
import java.util.Calendar;

public class ModifySettings extends AppCompatActivity {

    private static final String TAG = "ModifySettings";
    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText mEditNickname;
    private TextView mEditBirthday;
    private ImageView mImagePreview;

    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_settings);

        mEditNickname = findViewById(R.id.edit_nickname);
        mEditBirthday = findViewById(R.id.edit_birthday);
        mImagePreview = findViewById(R.id.image_preview);
        String user[] = {"username","password","2000-01-01"};
        mEditNickname.setText(user[0]);
        mEditBirthday.setText(user[2]);


        Button buttonSelectImage = findViewById(R.id.button_select_image);
        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        // 생년월일 변경 EditText를 클릭했을 때 DatePickerDialog를 띄워 날짜 선택하도록 함
        mEditBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Button buttonSaveChanges = findViewById(R.id.button_save_changes);
        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        Button button_edit_password = (Button) findViewById(R.id.button_edit_password);
        button_edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = (View) View.inflate(ModifySettings.this,R.layout.edit_password,null);
                EditText edit_old_password = (EditText) dialogView.findViewById(R.id.edit_old_password);
                EditText edit_new_password = (EditText) dialogView.findViewById(R.id.edit_new_password);
                EditText edit_password_confirm = (EditText) dialogView.findViewById(R.id.edit_password_confirm);

                AlertDialog.Builder dlg = new AlertDialog.Builder(ModifySettings.this);
                dlg.setView(dialogView);
                dlg.setTitle("비밀번호 변경");
                dlg.setPositiveButton("변경",null);
                dlg.setNegativeButton("취소",null);

                final AlertDialog alertDialog = dlg.create();
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        positiveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String oldPassword = edit_old_password.getText().toString();
                                String newPassword = edit_new_password.getText().toString();
                                String confirmPassword = edit_password_confirm.getText().toString();

                                if (!oldPassword.equals(user[1])) {
                                    Toast.makeText(ModifySettings.this, "현재 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                                    edit_old_password.setText("");
                                    return;
                                }

                                if (!newPassword.equals(confirmPassword)) {
                                    Toast.makeText(ModifySettings.this, "새 비밀번호와 비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                                    edit_password_confirm.setText("");
                                    return;
                                }

                                if(newPassword.length()==0){
                                    Toast.makeText(ModifySettings.this, "새 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                user[1] = newPassword;
                                Toast.makeText(ModifySettings.this, "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }
                        });
                    }
                });

                alertDialog.show();
            }
        });


    }

    // DatePickerDialog를 띄우는 메소드
    private void showDatePickerDialog() {
        // 현재 날짜를 기본값으로 설정
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // DatePickerDialog를 생성하고 리스너를 등록
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // 선택한 날짜를 yyyy-MM-dd 형식으로 EditText에 설정
                        mEditBirthday.setText(String.format("%d-%02d-%02d", year, month + 1, dayOfMonth));
                    }
                },
                year, month, day);
        // DatePickerDialog를 보여줌
        datePickerDialog.show();
    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void saveChanges() {
        String nickname = mEditNickname.getText().toString();
        String birthday = mEditBirthday.getText().toString();

        //확인용
        Log.d(TAG, "Nickname: " + nickname);
        Log.d(TAG, "Birthday: " + birthday);
        Log.d(TAG, "Image URI: " + mImageUri);


        Toast.makeText(this, "회원 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
    }

}
