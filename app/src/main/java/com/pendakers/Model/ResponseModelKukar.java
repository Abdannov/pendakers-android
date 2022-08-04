package com.pendakers.Model;

import java.util.List;

public class ResponseModelKukar {
    private int kode;
    private String pesan;
    private List<DataKukar> data;

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

    public List<DataKukar> getData() {
        return data;
    }

    public void setData(List<DataKukar> data) {
        this.data = data;
    }
}
