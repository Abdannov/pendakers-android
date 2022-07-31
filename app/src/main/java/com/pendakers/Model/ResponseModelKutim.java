package com.pendakers.Model;

import java.util.List;

public class ResponseModelKutim {
    private int kode;
    private String pesan;
    private List<DataKutim> data;

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

    public List<DataKutim> getData() {
        return data;
    }

    public void setData(List<DataKutim> data) {
        this.data = data;
    }
}
