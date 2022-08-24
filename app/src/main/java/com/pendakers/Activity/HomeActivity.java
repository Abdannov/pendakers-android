package com.pendakers.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pendakers.Manager.Const;
import com.pendakers.Manager.PrefManager;
import com.pendakers.R;

public class HomeActivity extends AppCompatActivity {

    Button smd, bpp, btg, kukar, kubar, kutim, berau, paser, ppu, mku, pemprov, logout;
    Button smd1, bpp1, btg1, kukar1, kubar1, kutim1, berau1, paser1, mku1, ppu1, kaltim1;
    LinearLayout samarinda, balikpapan, bontang, kutaikar, kutaitim, kutaibar, beraube, paserpa, mahakamulu, penajam, kaltim;
    ViewPager viewPager;
    SliderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = findViewById(R.id.vpImg);
        adapter = new SliderAdapter(this);
        viewPager.setAdapter(adapter);

        samarinda = findViewById(R.id.smd);
        samarinda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","samarinda");
                startActivity(intent);
            }
        });

        smd = findViewById(R.id.smd1);
        smd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","samarinda");
                startActivity(intent);
            }
        });

        smd1 = findViewById(R.id.smd2);
        smd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","samarinda");
                startActivity(intent);
            }
        });

        balikpapan = findViewById(R.id.balikpapan);
        balikpapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","balikpapan");
                startActivity(intent);
            }
        });

        bpp1 = findViewById(R.id.bpp1);
        bpp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","balikpapan");
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

        bontang = findViewById(R.id.bontang);
        bontang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","bontang");
                startActivity(intent);
            }
        });

        btg1 = findViewById(R.id.btg1);
        btg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","bontang");
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

        kutaikar = findViewById(R.id.kutaikar);
        kutaikar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","kukar");
                startActivity(intent);
            }
        });

        kukar1 = findViewById(R.id.kukar1);
        kukar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","kukar");
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

        kutaibar = findViewById(R.id.kutaibar);
        kutaibar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","kubar");
                startActivity(intent);
            }
        });

        kubar1 = findViewById(R.id.kubar1);
        kubar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","kubar");
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

        kutaitim = findViewById(R.id.kutaitim);
        kutaitim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","kutim");
                startActivity(intent);
            }
        });

        kutim1 = findViewById(R.id.kutim1);
        kutim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","kutim");
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

        beraube = findViewById(R.id.beraube);
        beraube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","berau");
                startActivity(intent);
            }
        });

        berau1 = findViewById(R.id.berau1);
        berau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","berau");
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

        paserpa = findViewById(R.id.paserpa);
        paserpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","paser");
                startActivity(intent);
            }
        });

        paser1 = findViewById(R.id.paser1);
        paser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","paser");
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

        penajam = findViewById(R.id.penajam);
        penajam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","penajam");
                startActivity(intent);
            }
        });

        ppu1 = findViewById(R.id.penajam1);
        ppu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","penajam");
                startActivity(intent);
            }
        });

        mahakamulu = findViewById(R.id.mahakamulu);
        mahakamulu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","mahakam");
                startActivity(intent);
            }
        });

        mku1 = findViewById(R.id.mku1);
        mku1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","mahakam");
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

        kaltim = findViewById(R.id.kaltim);
        kaltim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","pemprov");
                startActivity(intent);
            }
        });

        kaltim1 = findViewById(R.id.kaltim1);
        kaltim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess","pemprov");
                startActivity(intent);
            }
        });

        pemprov = findViewById(R.id.pemprov);
        pemprov.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, PendakerActivity.class);
            intent.putExtra("codeAccess","pemprov");
            startActivity(intent);
        });

        logout = findViewById(R.id.btnLogout);
        logout.setOnClickListener(v -> {
            PrefManager prf= new PrefManager(HomeActivity.this);
            prf.remove(Const.TOKEN);
            Intent i = new Intent(HomeActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        });
    }
}