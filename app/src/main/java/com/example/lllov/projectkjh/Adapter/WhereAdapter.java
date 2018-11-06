package com.example.lllov.projectkjh.Adapter;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.LocationVO;
import com.example.lllov.projectkjh.LocationInfoActivity;
import com.example.lllov.projectkjh.R;

import org.parceler.Parcels;

import java.util.ArrayList;

public class WhereAdapter extends RecyclerView.Adapter<WhereAdapter.ViewHolder> {

    ArrayList<LocationVO> data;
    BaseActivity context;

    public WhereAdapter(ArrayList<LocationVO> data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_where_grid, parent, false);
        return new ViewHolder(view);
    }

    //list에서 해당 position의 data를 가져옴
    public LocationVO getItem(int position) {
        if (data == null) {
            return null;
        }
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //첫번째 버튼 앞에 마진을 줌(디자인)
        if (position == 0 || position == 1) {
            GridLayoutManager.LayoutParams param = (GridLayoutManager.LayoutParams) viewHolder.container.getLayoutParams();
            param.topMargin = (int) context.dpToPixel(40);
            viewHolder.container.setLayoutParams(param);
        }

        if (position == data.size() - 1 || position == data.size() - 2) {
            GridLayoutManager.LayoutParams param = (GridLayoutManager.LayoutParams) viewHolder.container.getLayoutParams();
            param.bottomMargin = (int) context.dpToPixel(400);
            viewHolder.container.setLayoutParams(param);
        }

        final LocationVO data = getItem(position);
        String name = data.getName();
        String imageUrl = data.getImageUrl();

        viewHolder.tvName.setText(name);
        Glide.with(context).load(imageUrl).into(viewHolder.ivPicture);
        //이미지 둥글게
        viewHolder.ivPicture.setBackground(new ShapeDrawable(new OvalShape()));
        viewHolder.ivPicture.setClipToOutline(true);

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationOnClick(view, data);
            }
        });
    }

    //버튼 클릭시 해당 location의 정보 화면으로 이동
    public void locationOnClick(View view, LocationVO location) {
        Intent intent = new Intent(context, LocationInfoActivity.class);
        intent.putExtra("location", Parcels.wrap(location));
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout container;
        ImageView ivPicture;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
