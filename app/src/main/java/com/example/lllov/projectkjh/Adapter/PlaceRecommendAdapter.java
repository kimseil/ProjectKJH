package com.example.lllov.projectkjh.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lllov.projectkjh.DTO.DTORecommned;
import com.example.lllov.projectkjh.R;

import java.util.ArrayList;

public class PlaceRecommendAdapter extends RecyclerView.Adapter<PlaceRecommendAdapter.ViewHolder> {

    ArrayList<DTORecommned> data;
    Context context;

    public PlaceRecommendAdapter(ArrayList<DTORecommned> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_place_recommend_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        viewHolder.tvTitle.setText(data.get(position).getTitle());
        viewHolder.tvContent.setText(data.get(position).getContent());
        viewHolder.tvLocation.setText(data.get(position).getLocation());
        viewHolder.isFavorite = false;

        viewHolder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavorite(viewHolder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent, tvLocation;
        ImageView ivPicture, btnFavorite;
        boolean isFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvIntro);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
        }
    }

    public void onFavorite(ViewHolder viewHolder) {
        if(viewHolder.isFavorite) {
            viewHolder.isFavorite = false;
            viewHolder.btnFavorite.setImageResource(R.drawable.ic_favorite_border_black);
            Toast.makeText(context, "좋아요 취소", Toast.LENGTH_SHORT).show();
        } else {
            viewHolder.isFavorite = true;
            viewHolder.btnFavorite.setImageResource(R.drawable.ic_favorite_red);
            Toast.makeText(context, "좋아요", Toast.LENGTH_SHORT).show();
        }
    }
}
