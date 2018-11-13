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
import com.example.lllov.projectkjh.ApiClient;
import com.example.lllov.projectkjh.ApiService;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.LocationGuideVO;
import com.example.lllov.projectkjh.LocationGuideInfoActivity;
import com.example.lllov.projectkjh.LocationInfoActivity;
import com.example.lllov.projectkjh.R;

import org.parceler.Parcels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        viewHolder.tvTitle.setText(data.getLocationGuide().getTitle());
        viewHolder.tvContent.setText(data.getLocationGuide().getIntro());

        viewHolder.isFavorite = data.getIsFavorite();

        //좋아요 여부 확인
        if (viewHolder.isFavorite) {
            Glide.with(context).load(R.drawable.ic_favorite_red).into(viewHolder.btnFavorite);
        }

        String imageUrl = data.getLocationGuide().getImageUrl();
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
                onFavorite(viewHolder, data);
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

    public void onFavorite(final ViewHolder viewHolder, LocationGuideVO data) {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<Boolean> call = service.setIsFavoriteGuide(context.getUserId(), data.getLocationGuide().getId());

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                boolean isFavorite = response.body();
                viewHolder.isFavorite = isFavorite;
                Glide.with(context).load(isFavorite?R.drawable.ic_favorite_red:R.drawable.ic_favorite_border_black).into(viewHolder.btnFavorite);
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }
}
