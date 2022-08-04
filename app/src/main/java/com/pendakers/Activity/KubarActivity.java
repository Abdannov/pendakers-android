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
import com.pendakers.Adapter.pksAdapterKubar;
import com.pendakers.Model.DataKubar;
import com.pendakers.Model.ResponseModelKubar;
import com.pendakers.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KubarActivity extends AppCompatActivity {
    FloatingActionButton button1;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataKubar> listkubar = new ArrayList<>();
    private SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kubar);
        button1 = findViewById(R.id.tambahdatakubar);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KubarActivity.this, TambahdataKubar.class));
            }
        });

        rvData = findViewById(R.id.rv_datakubar);
        srlData = findViewById(R.id.srl_kubar);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                selectDatakubar();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        selectDatakubar();
    }

    public void selectDatakubar() {
        APIRequestData ardDatakubar = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelKubar> tampilData = ardDatakubar.ardSelectDataKubar();

        tampilData.enqueue(new Callback<ResponseModelKubar>() {
            @Override
            public void onResponse(Call<ResponseModelKubar> call, Response<ResponseModelKubar> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(KubarActivity.this, "Kode : " + kode + "| Pesan : " + pesan, Toast.LENGTH_SHORT).show();

                listkubar = response.body().getData();

                adData = new pksAdapterKubar(KubarActivity.this, listkubar);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelKubar> call, Throwable throwable) {
                Toast.makeText(KubarActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}