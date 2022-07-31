package com.pendakers.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pendakers.Model.DataBalikpapan;
import com.pendakers.R;

import java.util.List;


public class pksAdapterBalikpapan extends RecyclerView.Adapter<pksAdapterBalikpapan.HolderData> {
    private Context ctx;
    private List<DataBalikpapan> listbpp;

    public pksAdapterBalikpapan(Context ctx, List<DataBalikpapan> listbpp) {
        this.ctx = ctx;
        this.listbpp = listbpp;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_balikpapan, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataBalikpapan dm = listbpp.get(position);

        holder.tvid.setText(String.valueOf(dm.getId()));
        holder.tvNm.setText(dm.getNomor_mou());
        holder.tvNp.setText(dm.getNomor_pks());
        holder.tvMb.setText(dm.getMasa_berlaku());
        holder.tvTgl.setText(dm.getTanggal_pks());
        holder.tvUk.setText(dm.getUnit_kerja());
        holder.tvTt.setText(dm.getTentang());
    }

    @Override
    public int getItemCount() {
        return listbpp.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvid, tvNm, tvNp, tvMb, tvTgl, tvUk, tvTt;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvid = itemView.findViewById(R.id.fieldid);
            tvNm = itemView.findViewById(R.id.fieldnomormou);
            tvNp = itemView.findViewById(R.id.fieldnomorpks);
            tvMb = itemView.findViewById(R.id.fieldmb);
            tvTgl = itemView.findViewById(R.id.fieldtglpks);
            tvUk = itemView.findViewById(R.id.fielduk);
            tvTt = itemView.findViewById(R.id.fieldtentang);
        }
    }
}