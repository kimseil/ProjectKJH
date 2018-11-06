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

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.DTO.LocationGuideVO;
import com.example.lllov.projectkjh.DTO.DTOLocationGuide;
import com.example.lllov.projectkjh.DTO.LocationVO;
import com.example.lllov.projectkjh.R;

import org.parceler.Parcels;

import java.lang.reflect.Array;
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
            args.putParcelable("data", Parcels.wrap(data));
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_location_guide, container, false);
            ImageView ivPicture = rootView.findViewById(R.id.ivPicture);
            TextView tvTitle = rootView.findViewById(R.id.tvTitle);
            RecyclerView rvContent = rootView.findViewById(R.id.rvContent);
            LocationGuideContentAdapter adapter;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            int position = getArguments().getInt("position");
            DTOLocationGuide data = Parcels.unwrap(getArguments().getParcelable("data"));

            switch (position) {
                case 0:
                    tvTitle.setText("정말 유용한\n" + data.getLocation().getName() + " 정보와 팁");
                    break;
                case 1:
                    tvTitle.setText(data.getLocation().getName() + "볼거리, 즐길거리의\n모든 것");
                    break;
                case 2:
                    tvTitle.setText(data.getLocation().getName() + "\n먹킷리스트");
                    break;
            }
            Glide.with(getActivity()).load(data.getLocation().getImageUrl()).into(ivPicture);

            adapter = new LocationGuideContentAdapter(data.getData(), (BaseActivity)getActivity());
            rvContent.setAdapter(adapter);
            rvContent.setLayoutManager(linearLayoutManager);

            return rootView;
        }
    }
}
