package com.example.dailyq.ui.dashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyq.MainActivity;
import com.example.dailyq.R;
import com.example.dailyq.databinding.FragmentDashboardsBinding;
import com.example.dailyq.friend_timeline;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardsBinding binding;
    private Button friendAddButton;
    public ArrayList<User> friendData = new ArrayList<>();
    private FriendListAdapter friendListAdapter;
    TextView user_id;
    int id;
    int i = 1;

    RelativeLayout relativeLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 데이터 불러오기
        loadFriendData();
        // 값 출력
        for (User friend : friendData) {
            System.out.println("이름: " + friend.getName() + ", ID: " + friend.getId());
            i = friend.getId()+1;
            System.out.println(i);
        }

        ListView listView = root.findViewById(R.id.friend_list_view);
        friendListAdapter = new FriendListAdapter(getContext(), R.layout.activity_friend_item_layout, friendData);
        listView.setAdapter(friendListAdapter);

        friendAddButton = root.findViewById(R.id.friend_add_button);
        friendAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());
                dlg.setTitle("친구 추가");
                final EditText input = new EditText(getContext());
                dlg.setView(input);
                dlg.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        if (!text.isEmpty()) {
                            id = i;
                            friendData.add(new User(text, i));; // EditText에 입력된 텍스트를 리스트뷰에 추가
                            i++;

                            saveFriendData();
                            friendListAdapter.notifyDataSetChanged();

                            //값 들어갔는지 보기
                            for (User friend : friendData) {
                                System.out.println("친구 추가:::" + "이름: " + friend.getName() + ", ID: " + friend.getId());
                            }

                        } else {
                            Toast.makeText(getContext(), "친구 ID를 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

        return root;
    }

    public class FriendListAdapter extends ArrayAdapter<User> {

        private final Context context;
        private final ArrayList<User> friendData;

        public FriendListAdapter(Context context, int resource, ArrayList<User> friendData) {
            super(context, resource, friendData);
            this.context = context;
            this.friendData = friendData;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 뷰를 재사용하기 위해 convertView가 null인 경우에만 생성
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_friend_item_layout, parent, false);
            }

            // 뷰의 각 위젯에 MyData 객체의 데이터를 설정하기 전에 이전에 설정된 값을 초기화
            TextView nameTextView = convertView.findViewById(R.id.name_text_view);
            nameTextView.setText(null);

            TextView idTextView = convertView.findViewById(R.id.user_id);
            idTextView.setText(null);

            Button deleteButton = convertView.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(null);

            final View finalConvertView = convertView;

            relativeLayout = convertView.findViewById(R.id.friend_list_itme);
            // null 체크 후 이벤트 리스너 등록
            if (relativeLayout != null) {
                nameTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView nameTextView = finalConvertView.findViewById(R.id.name_text_view);
                        String name = nameTextView.getText().toString();

                        Intent intent = new Intent(getActivity(), friend_timeline.class);
                        System.out.println(idTextView.getText().toString());
                        intent.putExtra("id", idTextView.getText().toString());
                        startActivity(intent);
                    }
                });
            }

            User user = friendData.get(position);
            nameTextView.setText(user.getName());
            idTextView.setText(String.valueOf(user.getId()));

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 삭제 버튼을 누르면 해당 아이템 삭제
                    friendData.remove(position);
                    saveFriendData();
                    notifyDataSetChanged();

                    //삭제 잘 되었는지 확인
                    for (User friend : friendData) {
                        System.out.println("친구 추가:::" + "이름: " + friend.getName() + ", ID: " + friend.getId());
                    }



                }
            });
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    친구 데이터 저장

    // 상수 정의 추가
    private static final String PREFS_NAME = "FriendDataPrefs";
    private static final String FRIEND_DATA_KEY = "FriendData";
    private void saveFriendData() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // friendData 리스트를 JSON 형태로 변환하여 저장
        Gson gson = new Gson();
        String json = gson.toJson(friendData);

        editor.putString(FRIEND_DATA_KEY, json);
        editor.apply();

        //오류 있을때 데이터 싹 비우는 용도
        //editor.clear().apply();

    }

    private void loadFriendData() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(FRIEND_DATA_KEY, "");

        // 저장된 데이터가 있을 경우에만 불러오기
        if (!json.isEmpty()) {
            // JSON 문자열을 friendData 리스트로 변환
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<User>>() {}.getType();
            friendData = gson.fromJson(json, type);

        }
    }
}
