package com.example.lllov.projectkjh.Adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.ResponseScheduleInfoVO;
import com.example.lllov.projectkjh.R;

import java.util.ArrayList;

public class ScheduleInfoAdapter extends RecyclerView.Adapter<ScheduleInfoAdapter.ViewHolder> {

    ArrayList<ResponseScheduleInfoVO> data;
    BaseActivity context;

    public ScheduleInfoAdapter(ArrayList<ResponseScheduleInfoVO> data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_schedule_info_row, parent, false);
        return new ViewHolder(view);
    }

    public ResponseScheduleInfoVO getItem(int position) {
        if (data == null) {
            return null;
        }
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ResponseScheduleInfoVO data = getItem(position);

        if(data.getScheduleInfo().getType() == 1) {
            viewHolder.tvTitle.setText(data.getTitle());
            viewHolder.tvType.setText(context.PLACE_TYPE.get(data.getType()));
            //이미지가 있으면 로드
            String imageUrl = data.getImageUrl();
            if(!TextUtils.isEmpty(imageUrl)) {
                Glide.with(context).load(imageUrl).into(viewHolder.ivPicture);
            }

            viewHolder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        } else {
            viewHolder.tvMemo.setText(data.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout container;
        ImageView ivPicture;
        TextView tvTitle, tvMemo, tvType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvMemo = itemView.findViewById(R.id.tvMemo);
            tvType = itemView.findViewById(R.id.tvType);
        }
    }
}
