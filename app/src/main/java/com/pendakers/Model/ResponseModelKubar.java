package com.pendakers.Model;

import java.util.List;

public class ResponseModelKubar {
    private int kode;
    private String pesan;
    private List<DataKubar> data;

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

    public List<DataKubar> getData() {
        return data;
    }

    public void setData(List<DataKubar> data) {
        this.data = data;
    }
}
