package com.example.dailyq;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dailyq.ui.dashboard.DashboardFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class friend_answer_list extends AppCompatActivity {

    TextView friend_answer_list_name;
    ListView friend_answer_list_View;
    friend_answer_Adapter adapter;
    RelativeLayout relativeLayout;
    public int year, month, day;
    public String id;
    List<User> friendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_answer_combine);
        Intent intent = getIntent();
        year = intent.getIntExtra("year", 0);
        month = intent.getIntExtra("month", 0);
        day = intent.getIntExtra("day", 0);

        friend_answer_list_name = findViewById(R.id.friend_answer_list_name);
        friend_answer_list_View = findViewById(R.id.friend_answer_list_View);

        friendData = loadFriendData();

        adapter = new friend_answer_Adapter(this, friendData);
        friend_answer_list_View.setAdapter(adapter);

    }

    private static final String PREFS_NAME = "FriendDataPrefs";
    private static final String FRIEND_DATA_KEY = "FriendData";

    private List<User> loadFriendData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(FRIEND_DATA_KEY, "");

        List<User> friendData = new ArrayList<>();

        // 저장된 데이터가 있을 경우에만 불러오기
        if (!json.isEmpty()) {
            // JSON 문자열을 friendData 리스트로 변환
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<User>>() {}.getType();
            friendData = gson.fromJson(json, type);
        }

        return friendData;
    }

    public class friend_answer_Adapter extends BaseAdapter {

        private Context context;
        private List<User> data;

        public friend_answer_Adapter(Context context, List<User> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.activity_friend_answer_list_item, parent, false);
            }

            TextView itemTextView = convertView.findViewById(R.id.friend_answer_list_name);
            itemTextView.setText(data.get(position).getName());

            final View finalConvertView = convertView; // final 변수로 선언
            relativeLayout = convertView.findViewById(R.id.friend_ansewr_list_itme_Layout);
            // null 체크 후 이벤트 리스너 등록
            if (relativeLayout != null) {
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView nameTextView = finalConvertView.findViewById(R.id.friend_answer_list_name);
                        String name = nameTextView.getText().toString();
                        System.out.println( "ssssssssssssssssssssssss"+name);

                        //친구의 답변 보기
                        Intent intent  = new Intent(friend_answer_list.this, friend_answer_detail.class);
                        intent.putExtra("name", name); // 데이터 추가
                        intent.putExtra("year", year);
                        intent.putExtra("month", month);
                        intent.putExtra("day", day);
                        // intent.putExtra("id", id); //id 친구의 id값도 넣어주어야함
                        startActivity(intent);
                    }
                });
            }

            return convertView;
        }
    }

    public class User {
        private String name;
        private int id;

        public User(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}

