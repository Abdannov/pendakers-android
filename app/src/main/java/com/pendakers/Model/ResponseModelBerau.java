package com.pendakers.Model;

import java.util.List;

public class ResponseModelBerau {
    private int kode;
    private String pesan;
    private List<DataBerau> data;

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

    public List<DataBerau> getData() {
        return data;
    }

    public void setData(List<DataBerau> data) {
        this.data = data;
    }
}
