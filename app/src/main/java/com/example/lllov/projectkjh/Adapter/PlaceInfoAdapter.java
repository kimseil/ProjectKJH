package com.example.lllov.projectkjh.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.DTOPlaceInfo;
import com.example.lllov.projectkjh.R;

import java.util.ArrayList;

public class PlaceInfoAdapter extends RecyclerView.Adapter<PlaceInfoAdapter.ViewHolder> {

    ArrayList<DTOPlaceInfo> data;
    BaseActivity context;

    public PlaceInfoAdapter(ArrayList<DTOPlaceInfo> data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_place_info_column, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.tvTitle.setText(data.get(position).getTitle());
        viewHolder.tvContent.setText(data.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent;
        ImageView ivPicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            ivPicture = itemView.findViewById(R.id.ivPicture);
        }
    }
}
