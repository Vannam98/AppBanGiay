package com.example.appbangiay.data_models;

import java.util.ArrayList;

public class HuyNguoiGiao {
    private String id;
    private ArrayList<DanhSachNguoiGiao> nguoigiao;

    public HuyNguoiGiao() {
    }
    public HuyNguoiGiao(String id, ArrayList<DanhSachNguoiGiao> nguoigiao) {
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
