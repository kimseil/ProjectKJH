package com.example.lllov.projectkjh.Adapter;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.DTOWhere;
import com.example.lllov.projectkjh.LocationInfoActivity;
import com.example.lllov.projectkjh.R;

import java.util.ArrayList;

public class WhereGridAdapter extends RecyclerView.Adapter<WhereGridAdapter.ViewHolder> {

    ArrayList<DTOWhere> data;
    BaseActivity context;

    public WhereGridAdapter(ArrayList<DTOWhere> data, BaseActivity context) {
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

    public DTOWhere getItem(int position) {
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final DTOWhere data = getItem(position);

        viewHolder.tvName.setText(data.getLocation());

        Glide.with(context).load(data.getImageUrl()).into(viewHolder.ivPicture);
        viewHolder.ivPicture.setBackground(new ShapeDrawable(new OvalShape()));
        viewHolder.ivPicture.setClipToOutline(true);
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.locationOnClick(view, data.getLocation());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        LinearLayout container;
        ImageView ivPicture;
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            tvName = itemView.findViewById(R.id.tvName);
            container = itemView.findViewById(R.id.container);
        }

        public void locationOnClick(View view, String name) {
            Intent intent = new Intent(view.getContext(), LocationInfoActivity.class);
            intent.putExtra("name", name);
            view.getContext().startActivity(intent);
            ((BaseActivity)view.getContext()).overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        }
    }
}
