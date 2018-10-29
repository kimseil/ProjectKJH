package com.example.lllov.projectkjh.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private ArrayList<Long> scheduleList;
    private BaseActivity activity;

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.KOREA);
    private SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.KOREA);

    private String[] dayOfWeek = {"일", "월", "화", "수", "목", "금", "토"};

    public ScheduleAdapter(ArrayList<Long> scheduleList, BaseActivity activity) {
        this.scheduleList = scheduleList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.item_schedule_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(scheduleList.get(i));

        viewHolder.tvDayNumber.setText("day" + (i + 1));
        viewHolder.tvDay.setText(monthFormat.format(calendar.getTime()) + "." + dayFormat.format(calendar.getTime()) + "/" + dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayNumber, tvDay;
        ExpandableListView evSchedule;
        Button btnAddLocation, btnAddMemo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDayNumber = itemView.findViewById(R.id.tvDayNumber);
            tvDay = itemView.findViewById(R.id.tvDay);
            evSchedule = itemView.findViewById(R.id.evSchedule);
            btnAddLocation = itemView.findViewById(R.id.btnAddLocation);
            btnAddMemo = itemView.findViewById(R.id.btnAddMemo);
        }
    }

}