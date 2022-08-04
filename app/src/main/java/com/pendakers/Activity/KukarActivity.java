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
import com.pendakers.Adapter.pksAdapterKukar;
import com.pendakers.Model.DataKukar;
import com.pendakers.Model.ResponseModelKukar;
import com.pendakers.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KukarActivity extends AppCompatActivity {
    FloatingActionButton button1;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataKukar> listkukar = new ArrayList<>();
    private SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kukar);
        button1 = findViewById(R.id.tambahdatakukar);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KukarActivity.this, TambahdataKukar.class));
            }
        });

        rvData = findViewById(R.id.rv_datakukar);
        srlData = findViewById(R.id.srl_kukar);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                selectDatakukar();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        selectDatakukar();
    }

    public void selectDatakukar() {
        APIRequestData ardDatakukar = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelKukar> tampilData = ardDatakukar.ardSelectDataKukar();

        tampilData.enqueue(new Callback<ResponseModelKukar>() {
            @Override
            public void onResponse(Call<ResponseModelKukar> call, Response<ResponseModelKukar> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(KukarActivity.this, "Kode : " + kode + "| Pesan : " + pesan, Toast.LENGTH_SHORT).show();

                listkukar = response.body().getData();

                adData = new pksAdapterKukar(KukarActivity.this, listkukar);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelKukar> call, Throwable throwable) {
                Toast.makeText(KukarActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}