package com.pendakers.Model;

import java.util.List;

public class ResponseModelPPU {
    private int kode;
    private String pesan;
    private List<DataPPU> data;

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

    public List<DataPPU> getData() {
        return data;
    }

    public void setData(List<DataPPU> data) {
        this.data = data;
    }
}
