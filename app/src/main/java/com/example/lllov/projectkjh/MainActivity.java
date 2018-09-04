package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout btnFindTravel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 삭제
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //툴바
        ToolBar toolbar = new ToolBar(this);
        toolbar.setMenu();
        toolbar.setTitle("대동여지도");
        toolbar.setToolbar();

        btnFindTravel = findViewById(R.id.btnFindTravel);
        btnFindTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlaceInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
