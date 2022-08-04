package com.pendakers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class detailpksBerau extends AppCompatActivity {
    TextView idText, nomouText, nopksText, tglpksText, mbText, jwText, ukText, mkText, jenisText, dfText, ttText;
    String id, nomou, nopks, tglpks, mb, jw, uk, mk, tt, jenis, df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpks_berau);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00a859"));
        actionBar.setBackgroundDrawable(colorDrawable);

        idText = findViewById(R.id.fieldid);
        nomouText = findViewById(R.id.fieldnomormou);
        nopksText = findViewById(R.id.fieldnomorpks);
        tglpksText = findViewById(R.id.fieldtglpks);
        mbText = findViewById(R.id.fieldmb);
        jwText = findViewById(R.id.fieldjw);
        ukText = findViewById(R.id.fielduk);
        mkText = findViewById(R.id.fieldmk);
        ttText = findViewById(R.id.fieldtentang);
        jenisText = findViewById(R.id.fieldjenis);
        dfText = findViewById(R.id.fielddf);

        id = getIntent().getStringExtra("id");
        nomou = getIntent().getStringExtra("nomor_mou");
        nopks = getIntent().getStringExtra("nomor_pks");
        tglpks = getIntent().getStringExtra("tanggal_pks");
        mb = getIntent().getStringExtra("masa_berlaku");
        jw = getIntent().getStringExtra("jangka_waktu");
        uk = getIntent().getStringExtra("unit_kerja");
        mk = getIntent().getStringExtra("mitra_kerja");
        jenis = getIntent().getStringExtra("jenis");
        df = getIntent().getStringExtra("data_file");
        tt = getIntent().getStringExtra("tentang");

        idText.setText(id);
        nomouText.setText(nomou);
        nopksText.setText(nopks);
        tglpksText.setText(tglpks);
        mbText.setText(mb);
        jwText.setText(jw);
        ukText.setText(uk);
        mkText.setText(mk);
        dfText.setText(df);
        ttText.setText(tt);
        jenisText.setText(jenis);

    }
}