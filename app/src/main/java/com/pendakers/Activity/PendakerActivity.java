package com.pendakers.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pendakers.API.APIRequestData;
import com.pendakers.API.RetroServer;
import com.pendakers.Manager.Const;
import com.pendakers.Manager.PrefManager;
import com.pendakers.Model.DataSamarinda;
import com.pendakers.Model.ResponseModelSamarinda;
import com.pendakers.R;
import com.pendakers.Adapter.pksAdapterSamarinda;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendakerActivity extends AppCompatActivity {
    FloatingActionButton button1;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataSamarinda> listsmd = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private String codeAcces;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samarinda);
        codeAcces = getIntent().getStringExtra("codeAccess");
        button1 = findViewById(R.id.tambahdatasmd);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(codeAcces);
        setSupportActionBar(toolbar);
        button1.setOnClickListener(view -> {
            Intent intent = new Intent(PendakerActivity.this, TambahdataSamarindaActivity.class);
            intent.putExtra("codeAccess",codeAcces);
            intent.putExtra("from","add");
            startActivity(intent);
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
        PrefManager prf = new PrefManager(this);
        Log.e("tokenn", ""+prf.getString(Const.TOKEN));
        Call<ResponseModelSamarinda> tampilData = ardDatasmd.ardSelectData("Bearer "+prf.getString(Const.TOKEN),codeAcces);

        tampilData.enqueue(new Callback<ResponseModelSamarinda>() {
            @Override
            public void onResponse(Call<ResponseModelSamarinda> call, Response<ResponseModelSamarinda> response) {

                //Toast.makeText(SamarindaActivity.this, "Kode : " +kode+ "| Pesan : " +pesan, Toast.LENGTH_SHORT).show();

                listsmd = response.body().getData();

                adData = new pksAdapterSamarinda(PendakerActivity.this, listsmd, codeAcces);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelSamarinda> call, Throwable throwable) {
                Toast.makeText(PendakerActivity.this, "Gagal Menghubungi Server" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}