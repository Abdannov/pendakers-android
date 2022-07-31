package com.pendakers.Model;

import java.util.List;

public class ResponseModelPaser {
    private int kode;
    private String pesan;
    private List<DataPaser> data;

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

    public List<DataPaser> getData() {
        return data;
    }

    public void setData(List<DataPaser> data) {
        this.data = data;
    }
}
