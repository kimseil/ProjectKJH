package com.example.lllov.projectkjh.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.LocationGuideInfoVO;
import com.example.lllov.projectkjh.R;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

public class LocationGuideInfoAdapter extends RecyclerView.Adapter<LocationGuideInfoAdapter.ViewHolder> {

    ArrayList<LocationGuideInfoVO> data;
    BaseActivity context;

    public LocationGuideInfoAdapter(ArrayList<LocationGuideInfoVO> data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_location_guide_info_row, parent, false);
        return new ViewHolder(view);
    }

    public LocationGuideInfoVO getItem(int position) {
        if (data == null) {
            return null;
        }
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        LocationGuideInfoVO data = getItem(position);

        Log.e("TEst", "onBindViewHolder: " + this.data.size());
        viewHolder.tvTitle.setText(data.getTitle());
        viewHolder.tvContent.setText(Html.fromHtml(data.getContent()));
        //url이 있으면 사진을 보여줌
        String imageUrl = data.getImageUrl();
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(context).load(imageUrl).into(viewHolder.ivPicture);
            viewHolder.ivPicture.setVisibility(View.VISIBLE);
        }
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
