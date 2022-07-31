package com.pendakers.Model;

import java.util.List;

public class ResponseModelMKU {
    private int kode;
    private String pesan;
    private List<DataMKU> data;

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

    public List<DataMKU> getData() {
        return data;
    }

    public void setData(List<DataMKU> data) {
        this.data = data;
    }
}
