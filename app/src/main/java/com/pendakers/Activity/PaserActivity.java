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
import com.pendakers.Adapter.pksAdapterPaser;
import com.pendakers.Model.DataPaser;
import com.pendakers.Model.ResponseModelPaser;
import com.pendakers.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaserActivity extends AppCompatActivity {
    FloatingActionButton button1;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataPaser> listpaser = new ArrayList<>();
    private SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paser);
        button1 = findViewById(R.id.tambahdatapaser);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaserActivity.this, TambahdataPaser.class));
            }
        });

        rvData = findViewById(R.id.rv_datapaser);
        srlData = findViewById(R.id.srl_paser);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                selectDatapaser();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        selectDatapaser();
    }

    public void selectDatapaser() {
        APIRequestData ardDatapaser = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelPaser> tampilData = ardDatapaser.ardSelectDataPaser();

        tampilData.enqueue(new Callback<ResponseModelPaser>() {
            @Override
            public void onResponse(Call<ResponseModelPaser> call, Response<ResponseModelPaser> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(PaserActivity.this, "Kode : " + kode + "| Pesan : " + pesan, Toast.LENGTH_SHORT).show();

                listpaser = response.body().getData();

                adData = new pksAdapterPaser(PaserActivity.this, listpaser);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelPaser> call, Throwable throwable) {
                Toast.makeText(PaserActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}