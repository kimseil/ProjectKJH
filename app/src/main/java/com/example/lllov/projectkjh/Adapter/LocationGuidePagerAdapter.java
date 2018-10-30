package com.example.lllov.projectkjh.Adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lllov.projectkjh.DTO.DTOInfo;
import com.example.lllov.projectkjh.DTO.DTOLocationGuide;
import com.example.lllov.projectkjh.R;

import java.util.ArrayList;

public class LocationGuidePagerAdapter extends FragmentPagerAdapter {
    ArrayList<DTOLocationGuide> data;

    public LocationGuidePagerAdapter(FragmentManager fm, ArrayList<DTOLocationGuide> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceHolderFragment.newInstance(position, data.get(position));
    }

    @Override
    public int getCount() {
        return 3;
    }

    public static class PlaceHolderFragment extends Fragment {
        public PlaceHolderFragment() {}

        public static PlaceHolderFragment newInstance(int position, DTOLocationGuide data) {
            PlaceHolderFragment fragment = new PlaceHolderFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putParcelable("data", data);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            int position = getArguments().getInt("position");

            View rootView = inflater.inflate(R.layout.fragment_location_guide, container, false);
            ImageView ivPicture = rootView.findViewById(R.id.ivPicture);
            TextView tvTitle = rootView.findViewById(R.id.tvIntro);
            RecyclerView rvContent = rootView.findViewById(R.id.rvContent);
            LocationGuideContentAdapter adapter;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

            DTOLocationGuide data = getArguments().getParcelable("data");
            tvTitle.setText(data.getTitle());

            ArrayList<DTOInfo> info = data.getInfo();
            adapter = new LocationGuideContentAdapter(info, getActivity());
            rvContent.setAdapter(adapter);
            rvContent.setLayoutManager(linearLayoutManager);

            return rootView;
        }
    }
}
