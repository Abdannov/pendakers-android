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
import com.pendakers.Model.DataSamarinda;
import com.pendakers.Model.ResponseModelSamarinda;
import com.pendakers.R;
import com.pendakers.Adapter.pksAdapterSamarinda;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SamarindaActivity extends AppCompatActivity {
    FloatingActionButton button1;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataSamarinda> listsmd = new ArrayList<>();
    private SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samarinda);

        button1 = findViewById(R.id.tambahdatasmd);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SamarindaActivity.this, TambahdataSamarindaActivity.class));
            }
        });

        rvData = findViewById(R.id.rv_datasmd);
        srlData = findViewById(R.id.srl_smd);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        //selectDatasmd();

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                selectDatasmd();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        selectDatasmd();
    }

    private void selectDatasmd(){
        APIRequestData ardDatasmd = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelSamarinda> tampilData = ardDatasmd.ardSelectData();

        tampilData.enqueue(new Callback<ResponseModelSamarinda>() {
            @Override
            public void onResponse(Call<ResponseModelSamarinda> call, Response<ResponseModelSamarinda> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                //Toast.makeText(SamarindaActivity.this, "Kode : " +kode+ "| Pesan : " +pesan, Toast.LENGTH_SHORT).show();

                listsmd = response.body().getData();

                adData = new pksAdapterSamarinda(SamarindaActivity.this, listsmd);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelSamarinda> call, Throwable throwable) {
                Toast.makeText(SamarindaActivity.this, "Gagal Menghubungi Server" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }
}