package com.pendakers.Model;

import java.util.List;

public class ResponseModelBontang {
    private int kode;
    private String pesan;
    private List<DataBontang> data;

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

    public List<DataBontang> getData() {
        return data;
    }

    public void setData(List<DataBontang> data) {
        this.data = data;
    }
}
