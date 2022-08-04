package com.pendakers.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pendakers.Model.DataPaser;
import com.pendakers.R;

import java.util.List;

public class pksAdapterPaser extends RecyclerView.Adapter<pksAdapterPaser.HolderData>{
    private Context ctx;
    private List<DataPaser> listpaser;

    public pksAdapterPaser(Context ctx, List<DataPaser> listpaser) {
        this.ctx = ctx;
        this.listpaser = listpaser;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_paser, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataPaser dm = listpaser.get(position);

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
        return listpaser.size();
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
