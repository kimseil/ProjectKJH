package com.example.lllov.projectkjh.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.R;

public class LocationInfoAdapter extends RecyclerView.Adapter<LocationInfoAdapter.ViewHolder> {

    /*
    ArrayList<String> data;
    */
    BaseActivity context;

    /*
    public LocationInfoAdapter(ArrayList<String> data, Context context) {
        this.data = data;
        this.context = context;
    }
    */

    public LocationInfoAdapter(BaseActivity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_location_info_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        /*
        viewHolder.tvItem.setText(data.get(i));
        */
    }

    @Override
    public int getItemCount() {
        /*
        return data.size();
        */
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /*
        TextView tvItem;
        */

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /*
            tvItem = itemView.findViewById(R.id.tvItem);
            */
        }
    }
}
