package com.pendakers.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModelSamarinda {
    private int kode;
    private String pesan;
    private List<DataSamarinda> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<DataSamarinda> getData() {
        return data;
    }

    public void setData(List<DataSamarinda> data) {
        this.data = data;
    }
}
