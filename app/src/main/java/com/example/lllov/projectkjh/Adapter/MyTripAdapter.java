package com.example.lllov.projectkjh.Adapter;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.ResponseScheduleVO;
import com.example.lllov.projectkjh.R;
import com.example.lllov.projectkjh.DTO.DTOTripInfo;

import java.util.ArrayList;

/*==================================================================================================
 *
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
        View view = inflater.inflate(R.layout.fragment_mytrip,parent,false);

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

        Glide.with(context).load(data.getLocagtion().getImageUrl()).into(holder.ivPicture);
        holder.tvLocation.setText(data.getLocagtion().getName());
        holder.tvPeriod.setText(context.formatYMD.format(data.getSchedule().getStartDay()) + "~" + context.formatYMD.format(data.getSchedule().getEndDay()));

        //이미지 둥글게
        holder.ivPicture.setBackground(new ShapeDrawable(new OvalShape()));
        holder.ivPicture.setClipToOutline(true);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView tvLocation, tvPeriod;

        ViewHolder(View view) {
            super(view);
            ivPicture = view.findViewById(R.id.ivLocation);
            tvLocation = view.findViewById(R.id.tvLocation);
            tvPeriod = view.findViewById(R.id.tvPeriod);
        }
    }
}
