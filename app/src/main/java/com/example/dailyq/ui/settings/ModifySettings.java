package com.example.dailyq.ui.settings;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dailyq.R;

import java.io.IOException;

public class ModifySettings extends AppCompatActivity {

    private static final String TAG = "ModifySettings";
    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText mEditNickname;
    private EditText mEditPassword;
    private EditText mEditBirthday;
    private ImageView mImagePreview;

    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_settings);

        mEditNickname = findViewById(R.id.edit_nickname);
        mEditPassword = findViewById(R.id.edit_password);
        mEditBirthday = findViewById(R.id.edit_birthday);
        mImagePreview = findViewById(R.id.image_preview);

        Button buttonSelectImage = findViewById(R.id.button_select_image);
        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        Button buttonSaveChanges = findViewById(R.id.button_save_changes);
        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void saveChanges() {
        String nickname = mEditNickname.getText().toString();
        String password = mEditPassword.getText().toString();
        String birthday = mEditBirthday.getText().toString();

        // 예시로 수정된 회원 정보를 로그로 출력합니다.
        Log.d(TAG, "Nickname: " + nickname);
        Log.d(TAG, "Password: " + password);
        Log.d(TAG, "Birthday: " + birthday);
        Log.d(TAG, "Image URI: " + mImageUri);

        // 수정된 회원 정보를 서버에 전송하는 코드를 작성합니다.
        // ...

        Toast.makeText(this, "회원 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageUri);
                mImagePreview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
