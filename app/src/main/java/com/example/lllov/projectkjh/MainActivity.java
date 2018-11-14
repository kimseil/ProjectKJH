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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lllov.projectkjh.DTO.ScheduleDTO;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

/*==================================================================================================
 * 메인 화면
 * 햄버거 버튼으로 내 여행을 확인할 수 있으며 로그아웃 등 회원정보 관리 기능
 * 여행 검색 버튼으로 여행지 검색 화면으로 이동
 *=================================================================================================*/
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout btnFindTravel;
    TextView tv,tvName;
    ImageView profile;
    ScheduleDTO schedule;

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

        tv = findViewById(R.id.tv);

        //navigationView.getHeaderView로 햄버거메뉴 상단 헤더부분을 가져와 헤더에 있는 이미지뷰를 원형으로 만들고 이미지 로드
        final View headerView = navigationView.getHeaderView(0);
        profile = headerView.findViewById(R.id.ivProfile);
        profile.setBackground(new ShapeDrawable(new OvalShape()));
        profile.setClipToOutline(true);
        Glide.with(this).load(profilePath).into(profile);

        tvName = headerView.findViewById(R.id.tvName);
        tvName.setText(name+"님");

        tv.setText(name + "님");

        //여행 검색 버튼
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

        switch (item.getItemId()) {
            case R.id.nav_a:
                intent = new Intent(MainActivity.this, MyTripActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
            case R.id.nav_b:
                intent = new Intent(MainActivity.this, MyInfoActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
            case R.id.nav_c:
                if(Session.getCurrentSession().isOpened())
                    onClickLogout();
                else
                    Toast.makeText(this,"이미 로그아웃 되어있습니다.",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.dl);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void onClickLogout() {
        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}
