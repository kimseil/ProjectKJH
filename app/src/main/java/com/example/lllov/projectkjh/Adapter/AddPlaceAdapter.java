package com.example.lllov.projectkjh.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.DTOAddPlace;
import com.example.lllov.projectkjh.R;

import java.util.ArrayList;

public class AddPlaceAdapter extends RecyclerView.Adapter<AddPlaceAdapter.ViewHolder> {

    ArrayList<DTOAddPlace> data;
    BaseActivity context;

    public AddPlaceAdapter(ArrayList<DTOAddPlace> data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_add_place_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.tvPlace.setText(data.get(position).getPlace());
        viewHolder.tvLocation.setText(data.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView tvPlace, tvLocation;
        Button btnSelect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            tvPlace = itemView.findViewById(R.id.tvLocation);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            btnSelect = itemView.findViewById(R.id.btnSelect);
        }
    }
}
