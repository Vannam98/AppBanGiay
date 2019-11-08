package com.example.appbangiay.data_models;

import java.util.ArrayList;

public class HuyHoanThanh {
    private String id;
    private ArrayList<DonHangHoanThanhNhanVien> donHangHoanThanhNhanViens;

    public HuyHoanThanh() {
    }

    public HuyHoanThanh(String id, ArrayList<DonHangHoanThanhNhanVien> donHangHoanThanhNhanViens) {
        this.id = id;
        this.donHangHoanThanhNhanViens = donHangHoanThanhNhanViens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<DonHangHoanThanhNhanVien> getDonHangHoanThanhNhanViens() {
        return donHangHoanThanhNhanViens;
    }

    public void setDonHangHoanThanhNhanViens(ArrayList<DonHangHoanThanhNhanVien> donHangHoanThanhNhanViens) {
        this.donHangHoanThanhNhanViens = donHangHoanThanhNhanViens;
    }
}
