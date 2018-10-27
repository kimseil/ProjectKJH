package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

    TextView btnLoginGuest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteStatusBar();
        setContentView(R.layout.activitiy_login);

        btnLoginGuest = findViewById(R.id.btnLoginGuest);

        btnLoginGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginGuest();
            }
        });
    }

    private void loginGuest() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }
}
