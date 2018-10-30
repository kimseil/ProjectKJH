package com.example.lllov.projectkjh;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class WhereActivity extends BaseActivity {

    Toolbar toolbar;
    SearchView searchView;
    ImageView iv1, iv2, iv3, iv4, iv5, iv6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 제거
        deleteStatusBar();
        setContentView(R.layout.activity_where);

        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        iv6 = findViewById(R.id.iv6);

        toolbar = new ToolBar(this).setBack().setToolbar();
        Glide.with(this).load("https://cdn.pixabay.com/photo/2016/05/17/13/52/osaka-castle-1398118_960_720.jpg").into(iv1);
        Glide.with(this).load("https://d2ur7st6jjikze.cloudfront.net/offer_photos/7462/40659_large_1525259846.jpg?1525259846").into(iv2);
        Glide.with(this).load("https://svcstrg2.navitime.jp/curation/img/NTJtrv0132-ko/top.jpg").into(iv3);
        Glide.with(this).load("https://travelblog.expedia.co.kr/wp-content/uploads/2016/05/04.jpg").into(iv4);
        Glide.with(this).load("https://www.singaporeair.com/saar5/images/plan-travel/packages/singapore-stepover-holiday/sg-stepover-holiday.jpg").into(iv5);
        Glide.with(this).load("http://www.passnjoy.com/family/cebu/assets/img/g_img16.png").into(iv6);
        iv1.setBackground(new ShapeDrawable(new OvalShape()));
        iv1.setClipToOutline(true);
        iv2.setBackground(new ShapeDrawable(new OvalShape()));
        iv2.setClipToOutline(true);
        iv3.setBackground(new ShapeDrawable(new OvalShape()));
        iv3.setClipToOutline(true);
        iv4.setBackground(new ShapeDrawable(new OvalShape()));
        iv4.setClipToOutline(true);
        iv5.setBackground(new ShapeDrawable(new OvalShape()));
        iv5.setClipToOutline(true);
        iv6.setBackground(new ShapeDrawable(new OvalShape()));
        iv6.setClipToOutline(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setQueryHint("여행할 도시를 검색하세요");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    public void onClick(View view) {
        TextView tvLocation = view.findViewById(R.id.tv1);
        String location = tvLocation.getText().toString();

        Toast.makeText(this, location, Toast.LENGTH_SHORT).show();

        //test
        Intent intent = new Intent(WhereActivity.this, LocationInfoActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }
}
