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
import com.pendakers.Adapter.pksAdapterBerau;
import com.pendakers.Model.DataBerau;
import com.pendakers.Model.ResponseModelBerau;
import com.pendakers.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerauActivity extends AppCompatActivity{
    FloatingActionButton button1;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataBerau> listberau = new ArrayList<>();
    private SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berau);
        button1 = findViewById(R.id.tambahdataberau);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BerauActivity.this, TambahdataBerau.class));
            }
        });

        rvData = findViewById(R.id.rv_databerau);
        srlData = findViewById(R.id.srl_berau);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                selectDataberau();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        selectDataberau();
    }

    public void selectDataberau() {
        APIRequestData ardDataberau = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelBerau> tampilData = ardDataberau.ardSelectDataBerau();

        tampilData.enqueue(new Callback<ResponseModelBerau>() {
            @Override
            public void onResponse(Call<ResponseModelBerau> call, Response<ResponseModelBerau> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(BerauActivity.this, "Kode : " + kode + "| Pesan : " + pesan, Toast.LENGTH_SHORT).show();

                listberau = response.body().getData();

                adData = new pksAdapterBerau(BerauActivity.this, listberau);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelBerau> call, Throwable throwable) {
                Toast.makeText(BerauActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}