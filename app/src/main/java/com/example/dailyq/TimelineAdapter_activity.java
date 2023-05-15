package com.example.dailyq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Calendar;
import java.util.List;

public class TimelineAdapter_activity extends RecyclerView.Adapter<TimelineAdapter_activity.ViewHolder> {
    private List<DiaryEntry> diaryEntries;
    private TimelineAdapter.OnDateSelectedListener listener;

    public TimelineAdapter_activity(Context context, List<DiaryEntry> diaryEntries, TimelineAdapter.OnDateSelectedListener listener) {
        this.diaryEntries = diaryEntries;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiaryEntry diaryEntry = diaryEntries.get(position);
        holder.dateTextView.setText(diaryEntry.getDate());
        holder.contentTextView.setText(diaryEntry.getContents());

        holder.itemView.setOnClickListener(view -> {
            Calendar calendar = diaryEntry.getCalendar();
            CalendarDay date = CalendarDay.from(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            listener.onDateSelected(date);
        });
    }

    @Override
    public int getItemCount() {
        return diaryEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView contentTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
        }
    }
}
