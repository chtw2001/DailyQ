package com.example.dailyq.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dailyq.R;
import com.example.dailyq.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private ListView listViewNotifications;
    private List<String> notificationItems;
    private CustomAdapter notificationItemsAdapter;

    private void initialize(View view){

        listViewNotifications = view.findViewById(R.id.listview_notifications);

        notificationItems = new ArrayList<>();
        notificationItems.add("Notification 1Notification 1Notification 1Notification 1Notification 1Notification 1Notification 1Notification 1Notification 1Notification 1Notification 1Notification 1Notification 1Notification 1");
        notificationItems.add("Notification 2");
        notificationItems.add("Notification 3");
        notificationItemsAdapter = new CustomAdapter(getContext(),notificationItems);
        listViewNotifications.setAdapter(notificationItemsAdapter);

    }



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        initialize(view);
        return view;
    }

    protected class CustomAdapter extends BaseAdapter {
        private Context mContext;
        private List<String> mItems;

        public CustomAdapter(Context context, List<String> items) {
            mContext = context;
            mItems = items;
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            TextView textView;
            ImageView deleteButton;

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_notification_item, null);
            textView = (TextView) view.findViewById(R.id.textview_notification_item);
            deleteButton = (ImageView) view.findViewById(R.id.delete_notification_item);


            final String item = mItems.get(position);
            textView.setText(item);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //클릭시 어디로 갈지 적기
                    Toast.makeText(getContext(),item+"을 누름",Toast.LENGTH_SHORT).show();
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItems.remove(item);
                    notifyDataSetChanged();
                }
            });

            return view;
        }

    }




}