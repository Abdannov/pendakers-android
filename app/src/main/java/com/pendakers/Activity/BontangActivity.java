package com.pendakers.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pendakers.API.APIRequestData;
import com.pendakers.API.RetroServer;
import com.pendakers.Adapter.pksAdapterBontang;
import com.pendakers.Model.DataBontang;
import com.pendakers.Model.ResponseModelBontang;
import com.pendakers.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BontangActivity extends AppCompatActivity {
    FloatingActionButton button1;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataBontang> listbtg = new ArrayList<>();
    private SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bontang);
        button1 = findViewById(R.id.tambahdatabtg);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BontangActivity.this, TambahdataBontang.class));
            }
        });

        rvData = findViewById(R.id.rv_databtg);
        srlData = findViewById(R.id.srl_btg);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                selectDatabtg();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        selectDatabtg();
    }

    public void selectDatabtg() {
        APIRequestData ardDatabtg = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelBontang> tampilData = ardDatabtg.ardSelectDatabtg();

        tampilData.enqueue(new Callback<ResponseModelBontang>() {
            @Override
            public void onResponse(Call<ResponseModelBontang> call, Response<ResponseModelBontang> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(BontangActivity.this, "Kode : " +kode+ "| Pesan : " +pesan, Toast.LENGTH_SHORT).show();

                listbtg = response.body().getData();

                adData = new pksAdapterBontang(BontangActivity.this, listbtg);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelBontang> call, Throwable throwable) {
                Toast.makeText(BontangActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}