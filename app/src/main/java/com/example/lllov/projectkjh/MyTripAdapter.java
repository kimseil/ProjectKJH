package com.example.lllov.projectkjh;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyTripAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPlace;
        TextView tvPlace, tvPeriod;

        MyViewHolder(View view) {
            super(view);
            ivPlace = view.findViewById(R.id.ivPlace);
            tvPlace = view.findViewById(R.id.tvPlace);
            tvPeriod = view.findViewById(R.id.tvPeriod);
        }
    }

    private ArrayList<TripInfo> tripInfoArrayList;

    MyTripAdapter(ArrayList<TripInfo> tripInfoArrayList) {
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

        myViewHolder.ivPlace.setImageResource(tripInfoArrayList.get(position).drawableId);
        myViewHolder.tvPlace.setText(tripInfoArrayList.get(position).place);
        myViewHolder.tvPeriod.setText(tripInfoArrayList.get(position).period);
    }

    @Override
    public int getItemCount() {
        return tripInfoArrayList.size();
    }
}
