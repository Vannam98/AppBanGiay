package com.example.appbangiay.data_models;

import java.util.ArrayList;

public class DanhSachChonNguoiGiao {
    private String id;
    private ArrayList<DanhSachNguoiGiao> nguoigiao;

    public DanhSachChonNguoiGiao() {
    }

    public DanhSachChonNguoiGiao(String id, ArrayList<DanhSachNguoiGiao> nguoigiao) {
        this.id = id;
        this.nguoigiao = nguoigiao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<DanhSachNguoiGiao> getNguoigiao() {
        return nguoigiao;
    }

    public void setNguoigiao(ArrayList<DanhSachNguoiGiao> nguoigiao) {
        this.nguoigiao = nguoigiao;
    }
}
