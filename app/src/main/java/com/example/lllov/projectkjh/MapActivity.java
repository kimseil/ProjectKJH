package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lllov.projectkjh.DTO.FavoritePlaceVO;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.parceler.Parcels;

/*==================================================================================================
 * DB에서 장소의 위도,경도를 불러와 구글맵으로 보여주는 화면
 *=================================================================================================*/
public class MapActivity extends BaseActivity implements OnMapReadyCallback{
    GoogleMap mMap;
    FavoritePlaceVO place;
    Double latitude,longitude;
    String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent inIntent = getIntent();
        place = Parcels.unwrap(inIntent.getParcelableExtra("place"));

        latitude = Double.parseDouble(place.getLatitude());
        longitude = Double.parseDouble(place.getLongitude());
        name = place.getTitle();

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

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(osaka,15));
    }
}
