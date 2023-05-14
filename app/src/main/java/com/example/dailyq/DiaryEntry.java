package com.example.dailyq;

import java.util.Calendar;

public class DiaryEntry implements Comparable<DiaryEntry> {
    private String date;
    private String contents;

    public DiaryEntry(String date, String contents) {
        this.date = date;
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Calendar getCalendar() {
        String[] dateParts = date.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar;
    }

    @Override
    public int compareTo(DiaryEntry diaryEntry) {
        return this.date.compareTo(diaryEntry.date);
    }
}
