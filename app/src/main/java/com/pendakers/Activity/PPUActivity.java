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
import com.pendakers.Adapter.pksAdapterPPU;
import com.pendakers.Model.DataPPU;
import com.pendakers.Model.ResponseModelPPU;
import com.pendakers.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PPUActivity extends AppCompatActivity {
    FloatingActionButton button1;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataPPU> listppu = new ArrayList<>();
    private SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppuactivity);
        button1 = findViewById(R.id.tambahdatappu);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PPUActivity.this, TambahdataPPU.class));
            }
        });

        rvData = findViewById(R.id.rv_datappu);
        srlData = findViewById(R.id.srl_ppu);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                selectDatappu();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        selectDatappu();
    }

    public void selectDatappu() {
        APIRequestData ardDatappu = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelPPU> tampilData = ardDatappu.ardSelectDataPPU();

        tampilData.enqueue(new Callback<ResponseModelPPU>() {
            @Override
            public void onResponse(Call<ResponseModelPPU> call, Response<ResponseModelPPU> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(PPUActivity.this, "Kode : " + kode + "| Pesan : " + pesan, Toast.LENGTH_SHORT).show();

                listppu = response.body().getData();

                adData = new pksAdapterPPU(PPUActivity.this, listppu);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelPPU> call, Throwable throwable) {
                Toast.makeText(PPUActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}