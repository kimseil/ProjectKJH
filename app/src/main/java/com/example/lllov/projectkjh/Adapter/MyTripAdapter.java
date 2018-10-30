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

import com.example.lllov.projectkjh.R;
import com.example.lllov.projectkjh.DTO.TripInfo;

import java.util.ArrayList;

public class MyTripAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView tvLocation, tvPeriod;

        MyViewHolder(View view) {
            super(view);
            ivPicture = view.findViewById(R.id.ivLocation);
            tvLocation = view.findViewById(R.id.tvLocation);
            tvPeriod = view.findViewById(R.id.tvPeriod);
        }
    }

    private ArrayList<TripInfo> tripInfoArrayList;

    public MyTripAdapter(ArrayList<TripInfo> tripInfoArrayList) {
        this.tripInfoArrayList = tripInfoArrayList;
    }

    //Recycler의 행을 표시하는 클래스
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_trip_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.ivPicture.setImageResource(tripInfoArrayList.get(position).drawableId);
        myViewHolder.tvLocation.setText(tripInfoArrayList.get(position).location);
        myViewHolder.tvPeriod.setText(tripInfoArrayList.get(position).period);

        //이미지 둥글게
        myViewHolder.ivPicture.setBackground(new ShapeDrawable(new OvalShape()));
        myViewHolder.ivPicture.setClipToOutline(true);
    }

    @Override
    public int getItemCount() {
        return tripInfoArrayList.size();
    }
}
