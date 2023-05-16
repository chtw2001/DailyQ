package com.example.dailyq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class friend_answer_list extends AppCompatActivity {

    TextView friend_answer_list_name;
    ListView friend_answer_list_View;
    friend_answer_Adapter adapter;
    RelativeLayout relativeLayout;
    public int year, month, day;
    public String id;

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
        adapter = new friend_answer_Adapter(this,  getData());
        friend_answer_list_View.setAdapter(adapter);

    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        data.add("test 1");
        data.add("test 2");
        data.add("test 3");
        return data;
    }

    public class friend_answer_Adapter extends BaseAdapter {

        private Context context;
        private List<String> data;

        public friend_answer_Adapter(Context context, List<String> data) {
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
            itemTextView.setText(data.get(position));

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

}

