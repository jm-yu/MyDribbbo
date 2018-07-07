package jmyu.ufl.edu.mydribbbo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmyu.ufl.edu.mydribbbo.R;
import jmyu.ufl.edu.mydribbbo.dribbbo.Dribbbo;
import jmyu.ufl.edu.mydribbbo.dribbbo.auth.Auth;
import jmyu.ufl.edu.mydribbbo.dribbbo.auth.AuthActivity;

/**
 * Created by jmyu on 7/4/18.
 */

public class LoginActivity extends AppCompatActivity {

    private static final int REQ_CODE = 100;
    @BindView(R.id.activity_login_btn) TextView loginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Dribbbo.init(this);
        Log.d("Jimmy", String.valueOf(Dribbbo.isLoggedIn()));

        if (!Dribbbo.isLoggedIn()) {
            loginBtn.setOnClickListener(v -> {
                Intent intent = new Intent(this, AuthActivity.class);
                intent.putExtra(AuthActivity.KEY_URL, Auth.getAuthorizeUrl());
                startActivityForResult(intent, REQ_CODE);
            });
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            final String authCode = data.getStringExtra(AuthActivity.KEY_CODE);
            Log.d("Jimmy_code", authCode);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String token = Auth.getTokenfromCode(authCode);
                        Log.d("Jimmy_token", token);
                        Dribbbo.login(LoginActivity.this, token);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
