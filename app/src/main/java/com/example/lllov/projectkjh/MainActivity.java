package com.example.lllov.projectkjh;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout btnFindTravel;
    TextView tv,tvName;
    ImageView profile;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 삭제
        deleteStatusBar();
        setContentView(R.layout.activity_main);

        //툴바
        Toolbar toolbar = new ToolBar(this).setToolbar();

        //햄버거메뉴
        DrawerLayout drawer = findViewById(R.id.dl);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nv);
        navigationView.setNavigationItemSelectedListener(this);

        //햄버거메뉴 닫기 버튼
        ((ImageView) navigationView.getHeaderView(0).findViewById(R.id.btnBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        //LoginActivity에서 송신한 데이터 수신
        Intent intent = getIntent();

        String name = intent.getExtras().getString("name");
        final String profilePath = intent.getExtras().getString("profile");
        Log.d("PROFILE", profilePath);

        tv = findViewById(R.id.tv);

        final View headerView = navigationView.getHeaderView(0);
        profile = headerView.findViewById(R.id.ivProfile);
        profile.setBackground(new ShapeDrawable(new OvalShape()));
        profile.setClipToOutline(true);
        Glide.with(this).load(profilePath).into(profile);

        tvName = headerView.findViewById(R.id.tvName);
        tvName.setText(name+"님");

        tv.setText(name + "님");

        btnFindTravel = findViewById(R.id.btnFindTravel);
        btnFindTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WhereActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });

    }

    //햄버거메뉴가 열려있을때 뒤로가기 버튼 클릭시 메뉴를 닫음
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.dl);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //햄버거메뉴 각 네비게이션 선택 처리
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        String s = "";

        switch (item.getItemId()) {
            case R.id.nav_a:
                intent = new Intent(MainActivity.this, MyTripActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
            case R.id.nav_b:
                intent = new Intent(MainActivity.this, RegistrationTravelActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
            default:
                break;
        }

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        DrawerLayout drawer = findViewById(R.id.dl);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
