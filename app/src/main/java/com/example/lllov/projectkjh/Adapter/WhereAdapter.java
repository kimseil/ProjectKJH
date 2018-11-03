package com.example.lllov.projectkjh.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lllov.projectkjh.ApiClient;
import com.example.lllov.projectkjh.ApiService;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.DTOLocationGroup;
import com.example.lllov.projectkjh.DTO.DTOWhere;
import com.example.lllov.projectkjh.R;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhereAdapter extends RecyclerView.Adapter<WhereAdapter.ViewHolder> {

    ArrayList<DTOLocationGroup> data;

    public WhereAdapter(ArrayList<DTOLocationGroup> data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    BaseActivity context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_where_row, parent, false);
        return new ViewHolder(view);
    }

    public DTOLocationGroup getItem(int position) {
        if(data == null) {
            return null;
        }
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        DTOLocationGroup data = getItem(position);
        String name = data.getName();
        int id = data.getId();
        viewHolder.tvTitle.setText(name);

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<DTOWhere>> call = service.getLocations(id);
        call.enqueue(new Callback<ArrayList<DTOWhere>>() {
            @Override
            public void onResponse(Call<ArrayList<DTOWhere>> call, Response<ArrayList<DTOWhere>> response) {
                ArrayList<DTOWhere> data = response.body();


                Log.e("@@@@@@@@", new GsonBuilder().setPrettyPrinting().create().toJson(data));

                viewHolder.adapter = new WhereGridAdapter(data, context);
                viewHolder.layoutManager = new GridLayoutManager(context, 2);
                viewHolder.layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return 2;
                    }
                });
                viewHolder.rvContent.setLayoutManager(viewHolder.layoutManager);
                viewHolder.rvContent.setAdapter(viewHolder.adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<DTOWhere>> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        RecyclerView rvContent;
        WhereGridAdapter adapter;
        GridLayoutManager layoutManager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            rvContent = itemView.findViewById(R.id.rvContent);
        }
    }
}
