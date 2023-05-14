package com.example.dailyq.ui.dashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyq.MainActivity;
import com.example.dailyq.R;
import com.example.dailyq.databinding.FragmentDashboardsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardsBinding binding;
    private Button friendAddButton;
    private ArrayList<String> friendData = new ArrayList<>();
    private FriendListAdapter friendListAdapter;

    RelativeLayout relativeLayout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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
                            friendData.add(text); // EditText에 입력된 텍스트를 리스트뷰에 추가
                            friendListAdapter.notifyDataSetChanged();

                            //값 들어갔는지 보기
                            for(String friend : friendData) {
                                System.out.println(friend);
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

    public class FriendListAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final ArrayList<String> friendData;

        public FriendListAdapter(Context context, int resource, ArrayList<String> friendData) {
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

            Button deleteButton = convertView.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(null);


            relativeLayout = convertView.findViewById(R.id.friend_list_itme);
            // null 체크 후 이벤트 리스너 등록
            if (relativeLayout != null) {
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("tttttttttttttttttttt");
                        System.out.println("tt33333333333333ttttttt");
                        System.out.println("타임라인 만들어지면 달아주면 됨");

                    }
                });
            }



            nameTextView.setText(friendData.get(position));
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 삭제 버튼을 누르면 해당 아이템 삭제
                    friendData.remove(position);
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
