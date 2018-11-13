package com.example.lllov.projectkjh.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.R;

/*==================================================================================================
 * 일정 추가 화면의 상단에 선택할 날짜 만큼 생성되는 day 버튼 어댑터
 * 버튼 클릭시 해당하는 날짜로 스크롤(되도록 할 예정)
 *=================================================================================================*/
public class ScheduleDayAdapter extends RecyclerView.Adapter<ScheduleDayAdapter.ViewHolder> {
    int dayNumber;
    BaseActivity context;

    public ScheduleDayAdapter(int dayNumber, BaseActivity context) {
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
            param.leftMargin = (int) context.dpToPixel(24);
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
