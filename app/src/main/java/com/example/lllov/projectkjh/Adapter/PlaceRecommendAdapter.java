package com.example.lllov.projectkjh.Adapter;

import android.content.Intent;
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
import com.example.lllov.projectkjh.ApiClient;
import com.example.lllov.projectkjh.ApiService;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.FavoritePlaceVO;
import com.example.lllov.projectkjh.DTO.PlaceVO;
import com.example.lllov.projectkjh.PlaceInfoActivity;
import com.example.lllov.projectkjh.R;

import org.parceler.Parcels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*==================================================================================================
 * 맛집, 관광 장소 리스트 어댑터
 * 리스트 클릭시 해당 장소의 정보 화면으로 이동
 * 좋아요(찜) 가능
 *=================================================================================================*/
public class PlaceRecommendAdapter extends RecyclerView.Adapter<PlaceRecommendAdapter.ViewHolder> {

    ArrayList<PlaceVO> data;
    BaseActivity context;

    public PlaceRecommendAdapter(ArrayList<PlaceVO> data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_place_recommend_row, parent, false);
        return new ViewHolder(view);
    }

    public PlaceVO getItem(int position) {
        if (data == null) {
            return null;
        }
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final PlaceVO data = getItem(position);

        viewHolder.tvTitle.setText(data.getPlace().getTitle());
        viewHolder.tvIntro.setText(data.getPlace().getIntro());
        viewHolder.tvType.setText(context.PLACE_TYPE.get(data.getPlace().getType()));
        viewHolder.isFavorite = data.getIsFavorite();

        //좋아요 여부 확인
        Glide.with(context).load(viewHolder.isFavorite?R.drawable.ic_favorite_red:R.drawable.ic_favorite_border_white).into(viewHolder.btnFavorite);

        //이미지 있으면 로드
        String imageUrl = data.getPlace().getImageUrl();
        if(!TextUtils.isEmpty(imageUrl)) {
            Glide.with(context).load(imageUrl).into(viewHolder.ivPicture);
        }

        //좋아요 버튼
        viewHolder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavorite(viewHolder, data);
            }
        });

        //리스트 클릭시 해당 장소 정보 화면
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaceInfoActivity.class);
                FavoritePlaceVO place = new FavoritePlaceVO(data.getPlace().getId(), data.getPlace().getType(), data.getPlace().getTitle(), data.getPlace().getIntro(), data.getPlace().getImageUrl(), data.getPlace().getLatitude(), data.getPlace().getLongitude(), data.getPlace().getLocationId());
                intent.putExtra("place", Parcels.wrap(place));
                context.startActivity(intent);
                context.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView tvTitle, tvIntro, tvType;
        ImageView ivPicture, btnFavorite;
        boolean isFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvIntro = itemView.findViewById(R.id.tvIntro);
            tvType = itemView.findViewById(R.id.tvType);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
        }
    }

    public void onFavorite(final ViewHolder viewHolder, PlaceVO data) {
        //통신
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<Boolean> call = service.setIsFavoritePlace(context.getUserId(), data.getPlace().getId());

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                //좋아요 여부를 가져온 후 여부에 따라 이미지 로딩
                boolean isFavorite = response.body();
                viewHolder.isFavorite = isFavorite;
                Glide.with(context).load(isFavorite?R.drawable.ic_favorite_red:R.drawable.ic_favorite_border_white).into(viewHolder.btnFavorite);
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }
}
