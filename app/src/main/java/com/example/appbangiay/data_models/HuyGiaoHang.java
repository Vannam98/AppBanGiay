package com.example.appbangiay.data_models;

import java.util.ArrayList;

public class HuyGiaoHang {
    private  String id,lyDo;
    private ArrayList<DanhSachDagiao> DsHuy;

    public HuyGiaoHang() {
    }

    public HuyGiaoHang(String id, String lyDo, ArrayList<DanhSachDagiao> dsHuy) {
        this.id = id;
        this.lyDo = lyDo;
        DsHuy = dsHuy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<DanhSachDagiao> getDsHuy() {
        return DsHuy;
    }

    public void setDsHuy(ArrayList<DanhSachDagiao> dsHuy) {
        DsHuy = dsHuy;
    }


    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
}
