package com.pendakers.Model;

import java.util.List;

public class ResponseModelBalikpapan {
    private int kode;
    private String pesan;
    private List<DataBalikpapan> data;

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

    public List<DataBalikpapan> getData() {
        return data;
    }

    public void setData(List<DataBalikpapan> data) {
        this.data = data;
    }
}
