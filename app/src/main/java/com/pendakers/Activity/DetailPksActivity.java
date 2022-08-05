package com.pendakers.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pendakers.R;

public class DetailPksActivity extends AppCompatActivity {
    TextView idText, nomouText, nopksText, tglpksText, thnText, jwText, ukText, mkText, jenisText, dfText, ttText, btnDownloadPdf;
    String id, nomou, nopks, tglpks, thn, jw, uk, mk, tt, jenis, df;
    Toolbar toolbar;
    Button btnEdit;
    private String codeAccess;
    DownloadManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpks_samarinda);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        idText = findViewById(R.id.fieldid);
        nomouText = findViewById(R.id.fieldnomormou);
        nopksText = findViewById(R.id.fieldnomorpks);
        tglpksText = findViewById(R.id.fieldtglpks);
        thnText = findViewById(R.id.fieldthn);
        jwText = findViewById(R.id.fieldjw);
        ukText = findViewById(R.id.fielduk);
        mkText = findViewById(R.id.fieldmk);
        ttText = findViewById(R.id.fieldtentang);
        jenisText = findViewById(R.id.fieldjenis);
        dfText = findViewById(R.id.fielddf);
        btnEdit = findViewById(R.id.btnEdit);
        btnDownloadPdf = findViewById(R.id.btnDownloadPdf);

        codeAccess = getIntent().getStringExtra("codeAccess");
        id = getIntent().getStringExtra("id");
        nomou = getIntent().getStringExtra("mou");
        nopks = getIntent().getStringExtra("pks");
        tglpks = getIntent().getStringExtra("tanggal");
        thn = getIntent().getStringExtra("tahun");
        jw = getIntent().getStringExtra("jangka_waktu");
        uk = getIntent().getStringExtra("unitkerja");
        mk = getIntent().getStringExtra("mitrakerja");
        jenis = getIntent().getStringExtra("tahapan");
        df = getIntent().getStringExtra("file");
        tt = getIntent().getStringExtra("tentang");

        idText.setText(id);
        nomouText.setText(nomou);
        nopksText.setText(nopks);
        tglpksText.setText(tglpks);
        thnText.setText(thn);
        jwText.setText(jw);
        ukText.setText(uk);
        mkText.setText(mk);
        dfText.setText(df);
        ttText.setText(tt);
        jenisText.setText(jenis);
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(DetailPksActivity.this, TambahdataSamarindaActivity.class);
            intent.putExtra("codeAccess",codeAccess);
            intent.putExtra("from","edit");
            intent.putExtra("id",id);
            intent.putExtra("mou",nomou);
            intent.putExtra("pks",nopks);
            intent.putExtra("tanggal",tglpks);
            intent.putExtra("tahun",thn);
            intent.putExtra("jangkaWaktu",jw);
            intent.putExtra("unitKerja",uk);
            intent.putExtra("mitrakerja",mk);
            intent.putExtra("tentang",tt);
            intent.putExtra("file",df);
            startActivity(intent);
        });
        btnDownloadPdf.setOnClickListener(v -> {
            String baseUrl ="http://pendatakers.com/";
            String baseUrlLocal ="http://192.168.0.110:8000/";
            Uri uri = Uri.parse(baseUrlLocal+"storage/file/"+df);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setVisibleInDownloadsUi(false);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.getLastPathSegment());

            dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            dm.enqueue(request);
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