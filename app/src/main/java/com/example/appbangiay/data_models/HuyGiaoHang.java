package com.example.appbangiay.data_models;

import java.util.ArrayList;

public class HuyGiaoHang {

    private String id;
    private ArrayList<DonHangHoanThanhNhanVien> DsHuy;

    public HuyGiaoHang(){

    }

    public HuyGiaoHang(String id, ArrayList<DonHangHoanThanhNhanVien> dsHuy)
    {
        this.id = id;
        DsHuy = dsHuy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<DonHangHoanThanhNhanVien> getDsHuy() {
        return DsHuy;
    }

    public void setDsHuy(ArrayList<DonHangHoanThanhNhanVien> dsHuy) {
        DsHuy = dsHuy;
    }
}
