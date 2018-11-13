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
import com.example.lllov.projectkjh.DTO.DTOTripInfo;

import java.util.ArrayList;

public class Fragment_mytrip extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MyTripAdapter myTripAdapter;

    public Fragment_mytrip (){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mytrip, container, false);

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        ArrayList<DTOTripInfo> tripInfos = new ArrayList<>();
        tripInfos.add(new DTOTripInfo(R.drawable.praha,"프라하","2018-1-1"));
        tripInfos.add(new DTOTripInfo(R.drawable.paris,"파리","2018-10-1"));
        myTripAdapter = new MyTripAdapter(tripInfos);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myTripAdapter);
        return view;
    }
}
