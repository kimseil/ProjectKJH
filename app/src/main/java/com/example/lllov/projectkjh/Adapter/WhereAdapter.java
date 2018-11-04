package com.example.lllov.projectkjh.Adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.DTOWhere;
import com.example.lllov.projectkjh.R;

public class WhereAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    DTOWhere data;
    BaseActivity context;

    public WhereAdapter(DTOWhere data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            case 0:
                view = inflater.inflate(R.layout.item_where_grid, parent, false);
                return new ViewHolder0(view);
            case 1:
                view = inflater.inflate(R.layout.item_where_grid, parent, false);
                return new ViewHolder1(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder.getItemViewType() == 1) {
            //
            ((ViewHolder1)viewHolder).tvTitle.setText("");
        }
    }

    @Override
    public int getItemCount() {
        int cnt = 0;
        for(int i = 0; i < data.getListLocationList().size(); i++) {
            cnt += data.getListLocationList().get(i).getLocationList().size();
        }
        return cnt;
    }

    public static class ViewHolder0 extends RecyclerView.ViewHolder {

        ConstraintLayout container;
        ImageView ivPicture;
        TextView tvName;

        public ViewHolder0(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }

    public static class ViewHolder1 extends ViewHolder0 {
        TextView tvTitle;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
