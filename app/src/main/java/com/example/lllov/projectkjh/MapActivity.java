package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lllov.projectkjh.DTO.PlaceVO;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.parceler.Parcels;

public class MapActivity extends BaseActivity implements OnMapReadyCallback{
    GoogleMap mMap;
    PlaceVO place;
    Double latitude,longitude;
    String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent inIntent = getIntent();
        place = Parcels.unwrap(inIntent.getParcelableExtra("place"));

        latitude = Double.parseDouble(place.getPlace().getLatitude());
        longitude = Double.parseDouble(place.getPlace().getLongitude());
        name = place.getPlace().getTitle();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;

        LatLng osaka = new LatLng(latitude,longitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(osaka).title(name);

        mMap.addMarker(markerOptions);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(osaka,5));
    }
}
