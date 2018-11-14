package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lllov.projectkjh.Adapter.MyTripAdapter;
import com.example.lllov.projectkjh.Adapter.PlaceRecommendAdapter;
import com.example.lllov.projectkjh.DTO.DTOTripInfo;
import com.example.lllov.projectkjh.DTO.PlaceVO;
import com.example.lllov.projectkjh.DTO.ResponseScheduleVO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lllov.projectkjh.BaseActivity.sUserId;

public class MytripFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MyTripAdapter myTripAdapter;

    public MytripFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_mytrip, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(view.getContext());

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<ResponseScheduleVO>> call = service.getScheduleList(sUserId);

        call.enqueue(new Callback<ArrayList<ResponseScheduleVO>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseScheduleVO>> call, Response<ArrayList<ResponseScheduleVO>> response) {
                ArrayList<ResponseScheduleVO> data = response.body();

                myTripAdapter = new MyTripAdapter(data, (BaseActivity)getActivity());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(myTripAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseScheduleVO>> call, Throwable t) {

            }
        });

        return view;
    }
}
