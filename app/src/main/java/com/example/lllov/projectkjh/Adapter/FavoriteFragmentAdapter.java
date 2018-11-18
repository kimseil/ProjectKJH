package com.example.lllov.projectkjh.Adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.FavoritePlaceVO;
import com.example.lllov.projectkjh.R;

import java.util.ArrayList;

/*==================================================================================================
 * '찜한 목록'의 recyclerview를 구성하는 어댑터
 *=================================================================================================*/

public class FavoriteFragmentAdapter extends RecyclerView.Adapter<FavoriteFragmentAdapter.ViewHolder> {

    private ArrayList<FavoritePlaceVO> data;
    private BaseActivity context;

    public FavoriteFragmentAdapter(ArrayList<FavoritePlaceVO> data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    //Recycler의 행을 표시하는 클래스
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_favorite_place_row,parent,false);

        return new ViewHolder(view);
    }

    public FavoritePlaceVO getItem(int position){
        if(data == null){
            return null;
        }
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        final FavoritePlaceVO data = getItem(position);

        Glide.with(context).load(data.getImageUrl()).into(holder.ivPicture);
        holder.tvTitle.setText(data.getTitle());
        holder.tvIntro.setText(data.getIntro());
        holder.tvType.setText(context.PLACE_TYPE.get(data.getType()));

        /*
        //리스트 클릭시 해당 장소 정보 화면
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaceInfoActivity.class);
                intent.putExtra("favorite", Parcels.wrap(data));
                context.startActivity(intent);
                context.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        ImageView ivPicture;
        TextView tvTitle,tvIntro,tvType;

        ViewHolder(View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            ivPicture = view.findViewById(R.id.ivPicture);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvIntro = view.findViewById(R.id.tvIntro);
            tvType = view.findViewById(R.id.tvType);
        }
    }
}
