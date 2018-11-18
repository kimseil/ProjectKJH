package com.example.lllov.projectkjh.Adapter;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.LocationVO;
import com.example.lllov.projectkjh.DTO.ResponseScheduleVO;
import com.example.lllov.projectkjh.DTO.ScheduleVO;
import com.example.lllov.projectkjh.R;
import com.example.lllov.projectkjh.ScheduleActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

/*==================================================================================================
 * '내 여행'의 recyclerview를 구성하는 어댑터
 *=================================================================================================*/
public class MyTripAdapter extends RecyclerView.Adapter<MyTripAdapter.ViewHolder> {
    private ArrayList<ResponseScheduleVO> data;
    private BaseActivity context;

    public MyTripAdapter(ArrayList<ResponseScheduleVO> data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    //Recycler의 행을 표시하는 클래스
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_my_trip_row,parent,false);

        return new ViewHolder(view);
    }

    public ResponseScheduleVO getItem(int position){
        if(data == null){
            return null;
        }
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResponseScheduleVO data = getItem(position);
        final ScheduleVO schedule = data.getSchedule();
        final LocationVO location = data.getLocation();

        Glide.with(context).load(location.getImageUrl()).into(holder.ivPicture);
        holder.tvLocation.setText(location.getName());
        holder.tvPeriod.setText(context.formatYMD.format(schedule.getStartDay()) + " ~ " + context.formatYMD.format(schedule.getEndDay()));

        //이미지 둥글게
        holder.ivPicture.setBackground(new ShapeDrawable(new OvalShape()));
        holder.ivPicture.setClipToOutline(true);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScheduleActivity.class);
                ResponseScheduleVO schedules = new ResponseScheduleVO(schedule, location);
                intent.putExtra("schedules", Parcels.wrap(schedules));
                context.startActivity(intent);
                context.finish();
                context.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        ImageView ivPicture;
        TextView tvLocation, tvPeriod;

        ViewHolder(View view) {
            super(view);
            ivPicture = view.findViewById(R.id.ivLocation);
            tvLocation = view.findViewById(R.id.tvLocation);
            tvPeriod = view.findViewById(R.id.tvPeriod);
            container = view.findViewById(R.id.container);
        }
    }
}
