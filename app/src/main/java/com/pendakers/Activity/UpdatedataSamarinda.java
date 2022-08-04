package com.pendakers.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pendakers.API.APIRequestData;
import com.pendakers.API.RetroServer;
import com.pendakers.Model.ResponseModelSamarinda;
import com.pendakers.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatedataSamarinda extends AppCompatActivity {
    private int xid;
    private String xnm, xnp, xtgl, xmb, xjw, xuk, xmk, xtt, xjns, xdf;
    private EditText etnm, etnp, ettgl, etmb, etjw, etuk, etmk, ettt, etjns, etdf;
    private Button ubah;
    private String yjenis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata_samarinda);

        Intent terima = getIntent();
        xid = terima.getIntExtra("xid", -1);
        xnm = terima.getStringExtra("xnm");
        xnp = terima.getStringExtra("xnp");
        xtgl = terima.getStringExtra("xtgl");
        xmb = terima.getStringExtra("xthn");
        xjw = terima.getStringExtra("xjw");
        xuk = terima.getStringExtra("xuk");
        xmk = terima.getStringExtra("xmk");
        xtt = terima.getStringExtra("xtt");
        xjns = terima.getStringExtra("xjns");
        xdf = terima.getStringExtra("xdf");

        etnm = findViewById(R.id.nomorMOU);
        etnp = findViewById(R.id.nomorPKS);
        ettgl = findViewById(R.id.tglPKS);
        etmb = findViewById(R.id.masaberlaku);
        etjw = findViewById(R.id.jangkawaktu);
        etuk = findViewById(R.id.unitkerja);
        etmk = findViewById(R.id.mitrakerja);
        ettt = findViewById(R.id.tentang);
        etjns = findViewById(R.id.tahapan);
        etdf = findViewById(R.id.datafile);
        ubah = findViewById(R.id.btnupdate);

        etnm.setText(xnm);
        etnp.setText(xnp);
        ettgl.setText(xtgl);
        etmb.setText(xmb);
        etjw.setText(xjw);
        etuk.setText(xuk);
        etmk.setText(xmk);
        ettt.setText(xtt);
        etjns.setText(xjns);
        etdf.setText(xdf);

        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yjenis = etjns.getText().toString();
                updateData();
            }
        });
    }

    private void updateData(){
        /*APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelSamarinda> updateData = ardData.ardUpdatesmd(xid, xjns);

        updateData.enqueue(new Callback<ResponseModelSamarinda>() {
            @Override
            public void onResponse(Call<ResponseModelSamarinda> call, Response<ResponseModelSamarinda> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UpdatedataSamarinda.this, "Berhasil Ubah Data", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModelSamarinda> call, Throwable throwable) {
                Toast.makeText(UpdatedataSamarinda.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}