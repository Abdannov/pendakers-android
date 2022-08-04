package com.pendakers.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.pendakers.API.APIRequestData;
import com.pendakers.API.RetroServer;
import com.pendakers.Model.DataSamarinda;
import com.pendakers.Model.ResponseModelSamarinda;
import com.pendakers.R;
import com.pendakers.Activity.UpdatedataSamarinda;
import com.pendakers.detailpksSamarinda;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pksAdapterSamarinda extends RecyclerView.Adapter<pksAdapterSamarinda.HolderData>{
    private Context ctx;
    private List<DataSamarinda> listsmd;
    private List<DataSamarinda> getsmd;
    private int idsmd;

    public pksAdapterSamarinda(Context ctx, List<DataSamarinda> listsmd) {
        this.ctx = ctx;
        this.listsmd = listsmd;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_samarinda, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataSamarinda dm = listsmd.get(position);

        holder.tv_id.setText(String.valueOf(dm.getId()));
        holder.tv_tt.setText(dm.getTentang());
        holder.tv_nm.setText(dm.getMou());
        holder.tv_np.setText(dm.getPks());
        holder.tv_tgl.setText(dm.getTanggal());
        holder.tv_thn.setText(dm.getTahun());
        holder.tv_uk.setText(dm.getUnitkerja());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, detailpksSamarinda.class);
                Bundle bundle = new Bundle();
                bundle.putString("mou",dm.getMou());
                bundle.putString("pks", dm.getPks());
                bundle.putString("tanggal", dm.getTanggal());
//                bundle.putString("masa_berlaku", dm.getMasa_berlaku());
                bundle.putString("jangka_waktu", dm.getJangka_waktu());
                bundle.putString("unitkerja", dm.getUnitkerja());
                bundle.putString("mitrakerja", dm.getMitrakerja());
                bundle.putString("tentang", dm.getTentang());
                bundle.putString("tahapan", dm.getTahapan());
                bundle.putString("tahun", dm.getTahun());
                bundle.putString("file", dm.getFile());

                intent.putExtras(bundle);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listsmd.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tv_id, tv_nm, tv_np, tv_tt, tv_thn, tv_uk, tv_tgl;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_nm = itemView.findViewById(R.id.tv_nomormou);
            tv_np = itemView.findViewById(R.id.tv_nomorpks);
            tv_tgl = itemView.findViewById(R.id.tv_tglpks);
            tv_tt = itemView.findViewById(R.id.tv_tentang);
            tv_thn = itemView.findViewById(R.id.tv_tahun);
            tv_uk = itemView.findViewById(R.id.tv_uk);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Silahkan Ubah Data");
                    dialogPesan.setCancelable(true);

                    idsmd = Integer.parseInt(tv_id.getText().toString());

                    dialogPesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            updateData();
                            dialog.dismiss();
                        }
                    });

                    dialogPesan.show();

                    return false;
                }
            });
        }

        private void updateData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModelSamarinda> getData = ardData.ardGetsmd(idsmd);

            getData.enqueue(new Callback<ResponseModelSamarinda>() {
                @Override
                public void onResponse(Call<ResponseModelSamarinda> call, Response<ResponseModelSamarinda> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    getsmd = response.body().getData();

                    int varID = getsmd.get(0).getId();
                    String varNM = getsmd.get(0).getMou();
                    String varNP = getsmd.get(0).getPks();
                    String varTGL = getsmd.get(0).getTanggal();
//                    String varMB = getsmd.get(0).getMasa_berlaku();
                    String varJW = getsmd.get(0).getJangka_waktu();
                    String varUK = getsmd.get(0).getUnitkerja();
                    String varMK = getsmd.get(0).getMitrakerja();
                    String varTT = getsmd.get(0).getTentang();
                    String varJNS = getsmd.get(0).getTahapan();
                    String varTHN = getsmd.get(0).getTahun();
                    String varDF = getsmd.get(0).getFile();

                    //Toast.makeText(ctx, "", Toast.LENGTH_SHORT).show();
                    Intent kirim = new Intent(ctx, UpdatedataSamarinda.class);
                    kirim.putExtra("xid", varID);
                    kirim.putExtra("xnm", varNM);
                    kirim.putExtra("xnp", varNP);
                    kirim.putExtra("xtgl", varTGL);
//                    kirim.putExtra("xmb", varMB);
                    kirim.putExtra("xjw", varJW);
                    kirim.putExtra("xuk", varUK);
                    kirim.putExtra("xmk", varMK);
                    kirim.putExtra("xtt", varTT);
                    kirim.putExtra("xjns", varJNS);
                    kirim.putExtra("xthn", varTHN);
                    kirim.putExtra("xdf", varDF);
                    ctx.startActivity(kirim);
                }

                @Override
                public void onFailure(Call<ResponseModelSamarinda> call, Throwable throwable) {

                }
            });


        }
    }
}
