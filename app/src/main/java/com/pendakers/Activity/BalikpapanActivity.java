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
import com.pendakers.Adapter.pksAdapterBalikpapan;
import com.pendakers.Model.DataBalikpapan;
import com.pendakers.Model.ResponseModelBalikpapan;
import com.pendakers.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalikpapanActivity extends AppCompatActivity {
    FloatingActionButton button1;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataBalikpapan> listbpp = new ArrayList<>();
    private SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balikpapan);
        button1 = findViewById(R.id.tambahdatabpp);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BalikpapanActivity.this, TambahdataBalikpapan.class));
            }
        });

        rvData = findViewById(R.id.rv_databpp);
        srlData = findViewById(R.id.srl_bpp);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                selectDatabpp();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        selectDatabpp();
    }

    public void selectDatabpp(){
        APIRequestData ardDatabpp = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelBalikpapan> tampilData = ardDatabpp.ardSelectDatabpp();

        tampilData.enqueue(new Callback<ResponseModelBalikpapan>() {
            @Override
            public void onResponse(Call<ResponseModelBalikpapan> call, Response<ResponseModelBalikpapan> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(BalikpapanActivity.this, "Kode : " +kode+ "| Pesan : " +pesan, Toast.LENGTH_SHORT).show();

                listbpp = response.body().getData();

                adData = new pksAdapterBalikpapan(BalikpapanActivity.this, listbpp);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelBalikpapan> call, Throwable throwable) {
                Toast.makeText(BalikpapanActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}