package com.example.lllov.projectkjh.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.lllov.projectkjh.AddPlaceActivity;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.R;
import com.example.lllov.projectkjh.ScheduleActivity;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/*==================================================================================================
 * 일정 추가 화면의 일정 목록 어댑터
 * 선택한 날짜만큼 리스트가 생성됨
 * 원하는 날짜에 장소, 메모를 추가하는 버튼 있음
 *=================================================================================================*/
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private ArrayList<Long> scheduleList;
    private BaseActivity context;

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.KOREA);
    private SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.KOREA);

    private String[] dayOfWeek = {"일", "월", "화", "수", "목", "금", "토"};

    public ScheduleAdapter(ArrayList<Long> scheduleList, BaseActivity context) {
        this.scheduleList = scheduleList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_schedule_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //데이터로부터 각 아이템에 해당하는 날짜를 넣어줌.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(scheduleList.get(position));

        //해당 일정이 여행 며칠째인지 ex) day1, day2, day3, ...
        viewHolder.tvDayNumber.setText("day" + (position + 1));
        //해당 일정의 실제 날짜
        viewHolder.tvDay.setText(monthFormat.format(calendar.getTime()) + "." + dayFormat.format(calendar.getTime()) + "/" + dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
        //장소추가 버튼
        viewHolder.btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlace();
            }
        });
        //메모추가 버튼
        viewHolder.btnAddMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addmemo();
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayNumber, tvDay;
        ExpandableListView evSchedule;
        Button btnAddPlace, btnAddMemo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDayNumber = itemView.findViewById(R.id.tvDayNumber);
            tvDay = itemView.findViewById(R.id.tvDay);
            evSchedule = itemView.findViewById(R.id.evSchedule);
            btnAddPlace = itemView.findViewById(R.id.btnAddPlace);
            btnAddMemo = itemView.findViewById(R.id.btnAddMemo);
        }
    }

    private void addPlace() {
        Intent intent = new Intent(context, AddPlaceActivity.class);
        intent.putExtra("schedules", Parcels.wrap(((ScheduleActivity)context).schedules));
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    private void addmemo() {
        Intent intent = new Intent(context, AddPlaceActivity.class);
        intent.putExtra("schedules", Parcels.wrap(((ScheduleActivity)context).schedules));
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

}
