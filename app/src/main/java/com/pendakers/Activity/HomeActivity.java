package com.pendakers.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pendakers.Manager.Const;
import com.pendakers.Manager.PrefManager;
import com.pendakers.R;

public class HomeActivity extends AppCompatActivity {

    Button smd, bpp, btg, kukar, kubar, kutim, berau, paser, ppu, mku, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        smd = findViewById(R.id.smd);
        smd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","samarinda");
                startActivity(intent);
            }
        });

        bpp = findViewById(R.id.bpp);
        bpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","balikpapan");
                startActivity(intent);
            }
        });

        btg = findViewById(R.id.btg);
        btg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","bontang");
                startActivity(intent);
            }
        });

        kukar = findViewById(R.id.kukar);
        kukar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","kukar");
                startActivity(intent);
            }
        });

        kubar = findViewById(R.id.kubar);
        kubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","kubar");
                startActivity(intent);
            }
        });

        kutim = findViewById(R.id.kutim);
        kutim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","kutim");
                startActivity(intent);
            }
        });

        berau = findViewById(R.id.berau);
        berau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","berau");
                startActivity(intent);
            }
        });

        paser = findViewById(R.id.paser);
        paser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","paser");
                startActivity(intent);
            }
        });

        ppu = findViewById(R.id.ppu);
        ppu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","penajam");
                startActivity(intent);
            }
        });

        mku = findViewById(R.id.mku);
        mku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","mahakam");
                startActivity(intent);
            }
        });

        logout = findViewById(R.id.btnLogout);
        logout.setOnClickListener(v -> {
            PrefManager prf= new PrefManager(HomeActivity.this);
            prf.remove(Const.TOKEN);
            Intent i = new Intent(HomeActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        });
    }
}