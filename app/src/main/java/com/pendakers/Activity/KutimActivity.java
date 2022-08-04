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
import com.pendakers.Adapter.pksAdapterKutim;
import com.pendakers.Model.DataKutim;
import com.pendakers.Model.ResponseModelKutim;
import com.pendakers.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KutimActivity extends AppCompatActivity {
    FloatingActionButton button1;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataKutim> listkutim = new ArrayList<>();
    private SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kutim);
        button1 = findViewById(R.id.tambahdatakutim);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KutimActivity.this, TambahdataKutim.class));
            }
        });

        rvData = findViewById(R.id.rv_datakutim);
        srlData = findViewById(R.id.srl_kutim);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                selectDatakutim();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        selectDatakutim();
    }

    public void selectDatakutim() {
        APIRequestData ardDatakutim = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelKutim> tampilData = ardDatakutim.ardSelectDataKutim();

        tampilData.enqueue(new Callback<ResponseModelKutim>() {
            @Override
            public void onResponse(Call<ResponseModelKutim> call, Response<ResponseModelKutim> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(KutimActivity.this, "Kode : " + kode + "| Pesan : " + pesan, Toast.LENGTH_SHORT).show();

                listkutim = response.body().getData();

                adData = new pksAdapterKutim(KutimActivity.this, listkutim);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelKutim> call, Throwable throwable) {
                Toast.makeText(KutimActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}