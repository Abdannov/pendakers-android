package com.pendakers.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pendakers.R;

public class HomeActivity extends AppCompatActivity {

    Button smd, bpp, btg, kukar, kubar, kutim, berau, paser, ppu, mku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        smd = findViewById(R.id.smd);
        smd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SamarindaActivity.class));
            }
        });

        bpp = findViewById(R.id.bpp);
        bpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, BalikpapanActivity.class));
            }
        });

        btg = findViewById(R.id.btg);
        btg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, BontangActivity.class));
            }
        });

        kukar = findViewById(R.id.kukar);
        kukar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, KukarActivity.class));
            }
        });

        kubar = findViewById(R.id.kubar);
        kubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, KubarActivity.class));
            }
        });

        kutim = findViewById(R.id.kutim);
        kutim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, KutimActivity.class));
            }
        });

        berau = findViewById(R.id.berau);
        berau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, BerauActivity.class));
            }
        });

        paser = findViewById(R.id.paser);
        paser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PaserActivity.class));
            }
        });

        ppu = findViewById(R.id.ppu);
        ppu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PPUActivity.class));
            }
        });

        mku = findViewById(R.id.mku);
        mku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, MahakamUluActivity.class));
            }
        });
    }
}