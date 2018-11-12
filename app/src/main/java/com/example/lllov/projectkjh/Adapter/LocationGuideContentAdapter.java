package com.example.lllov.projectkjh.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.LocationGuideVO;
import com.example.lllov.projectkjh.LocationGuideInfoActivity;
import com.example.lllov.projectkjh.LocationInfoActivity;
import com.example.lllov.projectkjh.R;

import org.parceler.Parcels;

import java.util.ArrayList;

public class LocationGuideContentAdapter extends RecyclerView.Adapter<LocationGuideContentAdapter.ViewHolder> {

    private ArrayList<LocationGuideVO> data;
    private BaseActivity context;

    public LocationGuideContentAdapter(ArrayList<LocationGuideVO> data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_location_guide_content_row, parent, false);

        return new ViewHolder(view);
    }

    public LocationGuideVO getItem(int position) {
        if (data == null) {
            return null;
        }

        return data.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final LocationGuideVO data = getItem(position);

        viewHolder.tvTitle.setText(data.getTitle());
        viewHolder.tvContent.setText(data.getIntro());

        String imageUrl = data.getImageUrl();
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(context).load(imageUrl).into(viewHolder.ivPicture);
            viewHolder.ivPicture.setVisibility(View.VISIBLE);
        }

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guideOnClick(view, data);
            }
        });

        viewHolder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavorite(viewHolder);
            }
        });
    }

    public void guideOnClick(View view, LocationGuideVO locationGuide) {
        Intent intent = new Intent(context, LocationGuideInfoActivity.class);
        intent.putExtra("locationGuide", Parcels.wrap(locationGuide));
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout container;
        TextView tvTitle, tvContent;
        ImageView ivPicture, btnFavorite;
        boolean isFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
        }
    }

    public void onFavorite(LocationGuideContentAdapter.ViewHolder viewHolder) {
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
