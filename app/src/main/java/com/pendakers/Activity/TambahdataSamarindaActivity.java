package com.pendakers.Activity;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pendakers.API.APIRequestData;
import com.pendakers.API.RetroServer;
import com.pendakers.Model.CreateDataSamarinda;
import com.pendakers.Model.DataSamarinda;
import com.pendakers.Model.ResponseData;
import com.pendakers.Model.ResponseModelSamarinda;
import com.pendakers.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahdataSamarindaActivity extends AppCompatActivity {
    EditText ino_mou, ino_pks, itgl_pks, ithn, ijw, iuk, imk, itt, idf;
    Spinner ithp;
    TextView tket, labelUpload;
    Button binput, uploadFile;
    String mou, pks, tanggal, tahun, jangka_waktu, unitkerja, mitrakerja, tentang, tahapan, file;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahdata_samarinda);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00a859"));
        actionBar.setBackgroundDrawable(colorDrawable);

        ino_mou = findViewById(R.id.nomorMOU);
        ino_pks = findViewById(R.id.nomorPKS);
        itgl_pks = findViewById(R.id.tglPKS);
        ithn = findViewById(R.id.tahun);
        ijw = findViewById(R.id.jangkawaktu);
        iuk = findViewById(R.id.unitkerja);
        imk = findViewById(R.id.mitrakerja);
        itt = findViewById(R.id.tentang);
        idf = findViewById(R.id.datafile);
        binput = findViewById(R.id.btninput);
        labelUpload = findViewById(R.id.nameFile);
        uploadFile = findViewById(R.id.uploadFile);

        ithp = findViewById(R.id.tahapan);

        binput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mou = ino_mou.getText().toString();
                pks = ino_pks.getText().toString();
                tanggal = itgl_pks.getText().toString();
                tahun = ithn.getText().toString();
                jangka_waktu = ijw.getText().toString();
                unitkerja = iuk.getText().toString();
                mitrakerja = imk.getText().toString();
                tentang = itt.getText().toString();
                file = idf.getText().toString();
                String thp = String.valueOf(ithp.getSelectedItem());

                if (mou.trim().equals("")){
                    ino_mou.setError("Nomor MOU harus diisi");
                }else if(pks.trim().equals("")){
                    ino_pks.setError("Nomor PKS harus diisi");
                }else if(tanggal.trim().equals("")){
                    itgl_pks.setError("Tanggal harus diisi");
                }else if(tahun.trim().equals("")){
                    ithn.setError("Tahun harus diisi");
                }else if(jangka_waktu.trim().equals("")){
                    ijw.setError("Jangka Waktu harus diisi");
                }else if(tentang.trim().equals("")){
                    itt.setError("Nama Judul harus diisi");
                }else if(file.trim().equals("")){
                    idf.setError("Silahkan Upload File");
                }else{
                    inputdataSamarinda();
                }
            }
        });
        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String[] mimeTypes =
                            {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                                    "application/pdf"};
                    Intent addAttachment = new Intent(Intent.ACTION_GET_CONTENT);
                    addAttachment.setType("*/*");
                    addAttachment.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                    addAttachment.addCategory(Intent.CATEGORY_OPENABLE);
                    addAttachment.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                    addAttachment.setAction(Intent.ACTION_GET_CONTENT);
                    addAttachment.setAction(Intent.ACTION_OPEN_DOCUMENT);
                    startActivityForResult(addAttachment,0);
                } catch (Exception exception) {
                    Log.e("Filed",exception.getMessage());
                }
                Log.e("uriii", String.valueOf(uri));
            }
        });
    }



    void inputdataSamarinda() {
        APIRequestData ardDatasmd = RetroServer.konekRetrofit().create(APIRequestData.class);
        CreateDataSamarinda dataSamarinda = new CreateDataSamarinda();
        dataSamarinda.setMou(mou);
        dataSamarinda.setPks(pks);
        dataSamarinda.setTanggal(tanggal);
        dataSamarinda.setTahun(tahun);
        dataSamarinda.setJangka_waktu(jangka_waktu);
        dataSamarinda.setUnitkerja(unitkerja);
        dataSamarinda.setMitrakerja(mitrakerja);
        dataSamarinda.setTentang(tentang);
        dataSamarinda.setTahapan(tahapan);
        dataSamarinda.setFile(file);
        Call<ResponseData> inputdata = ardDatasmd.ardInsertsmd(dataSamarinda);

        inputdata.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()){
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    Toast.makeText(TambahdataSamarindaActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable throwable) {
                Toast.makeText(TambahdataSamarindaActivity.this, "Gagal Menghubungi Server | "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}