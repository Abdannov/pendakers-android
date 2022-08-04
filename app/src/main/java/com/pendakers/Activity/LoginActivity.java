package com.pendakers.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pendakers.API.APIRequestData;
import com.pendakers.API.RetroServer;
import com.pendakers.Adapter.pksAdapterSamarinda;
import com.pendakers.Manager.Const;
import com.pendakers.Manager.PrefManager;
import com.pendakers.Model.ResponseLogin;
import com.pendakers.Model.ResponseModelSamarinda;
import com.pendakers.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPass;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.email);
        etPass = findViewById(R.id.password);
        button = findViewById(R.id.btnLogin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                loginApi(email,pass);
            }
        });
    }

    private void loginApi(String email, String pass) {
        APIRequestData loginAPI = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseLogin> loginRequest = loginAPI.getLoginApi(email,pass);
        loginRequest.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()){
                    PrefManager prf = new PrefManager(LoginActivity.this);
                    assert response.body() != null;
                    prf.setString(Const.TOKEN,response.body().getAccessToken());
                }
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable throwable) {
                Toast.makeText(LoginActivity.this, "Gagal Menghubungi Server" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }
}