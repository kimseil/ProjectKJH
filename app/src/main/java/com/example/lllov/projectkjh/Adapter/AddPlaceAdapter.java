package com.example.lllov.projectkjh.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.FavoritePlaceVO;
import com.example.lllov.projectkjh.PlaceInfoActivity;
import com.example.lllov.projectkjh.R;

import org.parceler.Parcels;

import java.util.ArrayList;

/*==================================================================================================
 * 장소 추가 화면의 장소 리스트 어댑터
 *=================================================================================================*/
public class AddPlaceAdapter extends RecyclerView.Adapter<AddPlaceAdapter.ViewHolder> {

    ArrayList<FavoritePlaceVO> data;
    BaseActivity context;

    public AddPlaceAdapter(ArrayList<FavoritePlaceVO> data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_add_place_row, parent, false);

        return new ViewHolder(view);
    }

    public FavoritePlaceVO getItem(int position) {
        if (data == null) {
            return null;
        }
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final FavoritePlaceVO data = getItem(position);

        viewHolder.tvPlace.setText(data.getTitle());
        viewHolder.tvLocation.setText(context.PLACE_TYPE.get(data.getType()));

        //이미지 있으면 로드
        String imageUrl = data.getImageUrl();
        if(!TextUtils.isEmpty(imageUrl)) {
            Glide.with(context).load(imageUrl).into(viewHolder.ivPicture);
        }

        //리스트 클릭시 해당 장소 정보 화면
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaceInfoActivity.class);
                intent.putExtra("place", Parcels.wrap(data));
                context.startActivity(intent);
                context.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });

        viewHolder.btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        ImageView ivPicture;
        TextView tvPlace, tvLocation;
        Button btnSelect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            btnSelect = itemView.findViewById(R.id.btnSelect);
        }
    }
}
