package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout btnFindTravel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 삭제
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        ((ImageView)navigationView.getHeaderView(0).findViewById(R.id.btnBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnFindTravel = findViewById(R.id.btnFindTravel);
        btnFindTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WhereActivity.class);
                startActivity(intent);
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
        String s = "";

        switch (item.getItemId()) {
            case R.id.nav_a:
                Intent intent = new Intent(MainActivity.this,MyTripActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_b:
                s = "B 선택";
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
