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
import com.pendakers.Adapter.pksAdapterMKU;
import com.pendakers.Model.DataMKU;
import com.pendakers.Model.ResponseModelMKU;
import com.pendakers.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MahakamUluActivity extends AppCompatActivity {
    FloatingActionButton button1;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataMKU> listmu = new ArrayList<>();
    private SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahakam_ulu);
        button1 = findViewById(R.id.tambahdatamu);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MahakamUluActivity.this, TambahdataMKU.class));
            }
        });

        rvData = findViewById(R.id.rv_datamu);
        srlData = findViewById(R.id.srl_mu);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                selectDatamu();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        selectDatamu();
    }

    public void selectDatamu() {
        APIRequestData ardDatamu = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelMKU> tampilData = ardDatamu.ardSelectDataMU();

        tampilData.enqueue(new Callback<ResponseModelMKU>() {
            @Override
            public void onResponse(Call<ResponseModelMKU> call, Response<ResponseModelMKU> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(MahakamUluActivity.this, "Kode : " + kode + "| Pesan : " + pesan, Toast.LENGTH_SHORT).show();

                listmu = response.body().getData();

                adData = new pksAdapterMKU(MahakamUluActivity.this, listmu);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelMKU> call, Throwable throwable) {
                Toast.makeText(MahakamUluActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}