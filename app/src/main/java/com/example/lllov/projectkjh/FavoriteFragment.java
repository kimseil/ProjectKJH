package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lllov.projectkjh.Adapter.FavoriteFragmentAdapter;
import com.example.lllov.projectkjh.DTO.FavoritePlaceVO;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lllov.projectkjh.BaseActivity.sUserId;
/*==================================================================================================
 * '찜한 장소' 탭 화면
 *=================================================================================================*/

public class FavoriteFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    FavoriteFragmentAdapter favoriteFragmentAdapter;

    public FavoriteFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(view.getContext());

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<FavoritePlaceVO>> call = service.getFavoritePlaceList(sUserId);

        call.enqueue(new Callback<ArrayList<FavoritePlaceVO>>() {
            @Override
            public void onResponse(Call<ArrayList<FavoritePlaceVO>> call, Response<ArrayList<FavoritePlaceVO>> response) {
                ArrayList<FavoritePlaceVO> data = response.body();
                Log.e("getFavoriteList Log", new GsonBuilder().setPrettyPrinting().create().toJson(data));

                favoriteFragmentAdapter = new FavoriteFragmentAdapter(data, (BaseActivity)getActivity());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(favoriteFragmentAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<FavoritePlaceVO>> call, Throwable t) {

            }
        });

        return view;
    }
}
