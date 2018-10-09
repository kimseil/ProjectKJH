package com.example.lllov.projectkjh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class ScheduleDayAdapter extends RecyclerView.Adapter<ScheduleDayAdapter.ViewHolder> {
    int dayNumber;
    Context context;

    public ScheduleDayAdapter(int dayNumber, Context context) {
        this.dayNumber = dayNumber;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_schedule_day_column, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.btnSelectDay.setText("day" + (i + 1));

        //첫번째 버튼 앞에 마진을 줌(디자인)
        if (i == 0) {
            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) viewHolder.btnSelectDay.getLayoutParams();
            param.leftMargin = (int) Convert.convertDpToPixel(24, context);
            viewHolder.btnSelectDay.setLayoutParams(param);
        }
    }

    @Override
    public int getItemCount() {
        return dayNumber;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button btnSelectDay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnSelectDay = itemView.findViewById(R.id.btnSelectDay);
        }
    }


}
