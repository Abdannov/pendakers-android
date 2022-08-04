package com.pendakers.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pendakers.R;

import java.util.HashMap;
import java.util.Map;

public class TambahdataKubar extends AppCompatActivity {
    public static final String url = "http://10.0.2.2/project_mou/insertkubar.php";
    EditText ino_mou, ino_pks, itgl_pks, imb, ijw, iuk, imk, itt, ithp, idf;
    TextView tket;
    Button binput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahdata_kubar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00a859"));
        actionBar.setBackgroundDrawable(colorDrawable);

        ino_mou = findViewById(R.id.nomorMOU);
        ino_pks = findViewById(R.id.nomorPKS);
        itgl_pks = findViewById(R.id.tglPKS);
        imb = findViewById(R.id.masaberlaku);
        ijw = findViewById(R.id.jangkawaktu);
        iuk = findViewById(R.id.unitkerja);
        imk = findViewById(R.id.mitrakerja);
        itt = findViewById(R.id.tentang);
        ithp = findViewById(R.id.tahapan);
        idf = findViewById(R.id.datafile);
        tket = findViewById(R.id.txtKet);
        binput = findViewById(R.id.btninput);

        binput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputdataKubar();
            }
        });
    }

    void inputdataKubar() {
        String nomormou = ino_mou.getText().toString();
        String nomorpks = ino_pks.getText().toString();
        String tanggalpks = itgl_pks.getText().toString();
        String masaberlaku = imb.getText().toString();
        String jangkawaktu = ijw.getText().toString();
        String unitkerja = iuk.getText().toString();
        String mitrakerja = imk.getText().toString();
        String tahapan = ithp.getText().toString();
        String datafile = idf.getText().toString();
        String tentang = itt.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tket.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tket.setText("Error tidak dapat diproses");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("nomor_mou", nomormou);
                params.put("nomor_pks", nomorpks);
                params.put("tanggal_pks", tanggalpks);
                params.put("masa_berlaku", masaberlaku);
                params.put("jangka_waktu", jangkawaktu);
                params.put("unit_kerja", unitkerja);
                params.put("mitra_kerja", mitrakerja);
                params.put("jenis", tahapan);
                params.put("data_file", datafile);
                params.put("tentang", tentang);
                return params;

            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}