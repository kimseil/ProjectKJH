package com.example.lllov.projectkjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;


public class LoginActivity extends BaseActivity {
    private SessionCallback callback;

    TextView btnLoginGuest;
    private Button btnLoginKakao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteStatusBar();
        setContentView(R.layout.activitiy_login);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);

        btnLoginGuest = findViewById(R.id.btnLoginGuest);
        btnLoginKakao = findViewById(R.id.btnLoginKakao);

        btnLoginGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginGuest();
            }
        });

        btnLoginKakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session session = Session.getCurrentSession();
                session.addCallback(new SessionCallback());
                session.open(AuthType.KAKAO_LOGIN_ALL,LoginActivity.this);
            }
        });

    }

    public class SessionCallback implements ISessionCallback {

        // 로그인에 성공한 상태
        @Override
        public void onSessionOpened() {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
            requestMe();
        }

        // 로그인에 실패한 상태
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());
        }

        // 사용자 정보 요청
        public void requestMe() {
            // 사용자정보 요청 결과에 대한 Callback
            UserManagement.getInstance().requestMe(new MeResponseCallback() {
                // 세션 오픈 실패. 세션이 삭제된 경우,
                @Override
                public void onSessionClosed(ErrorResult errorResult) {

                    Log.e("SessionCallback :: ", "onSessionClosed : " + errorResult.getErrorMessage());

                }

                // 회원이 아닌 경우,
                @Override

                public void onNotSignedUp() {
                    Log.e("SessionCallback :: ", "onNotSignedUp");
                }

                // 사용자정보 요청에 성공한 경우,
                @Override
                public void onSuccess(UserProfile userProfile) {

                    Log.e("SessionCallback :: ", "onSuccess");

                    String nickname = userProfile.getNickname();
                    String email = userProfile.getEmail();
                    String profileImagePath = userProfile.getProfileImagePath();
                    String thumnailPath = userProfile.getThumbnailImagePath();
                    String UUID = userProfile.getUUID();
                    long id = userProfile.getId();

                    /*
                    Log.e("Profile : ", nickname + "");
                    Log.e("Profile : ", email + "");
                    Log.e("Profile : ", profileImagePath  + "");
                    Log.e("Profile : ", thumnailPath + "");
                    Log.e("Profile : ", UUID + "");
                    Log.e("Profile : ", id + "");
                    */

                    Toast.makeText(getApplicationContext(),nickname+"님 반갑습니다",Toast.LENGTH_SHORT).show();
                }

                // 사용자 정보 요청 실패
                @Override

                public void onFailure(ErrorResult errorResult) {
                    Log.e("SessionCallback :: ", "onFailure : " + errorResult.getErrorMessage());
                }
            });
        }
    }


    private void loginGuest() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }


}
