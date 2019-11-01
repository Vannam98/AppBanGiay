package com.example.appbangiay.data_models;

public class TinhTrangDonHang {

    public String id;
    public String maDH;
    public String tenDH;
    public String thongTinDH;
    public String tinhTrang;
    public String tongTien;
    public String ngay;

    public TinhTrangDonHang(){

    }

    public TinhTrangDonHang(String id, String maDH, String tenDH, String thongTinDH, String tinhTrang, String tongTien, String ngay) {
        this.id = id;
        this.maDH = maDH;
        this.tenDH = tenDH;
        this.thongTinDH = thongTinDH;
        this.tinhTrang = tinhTrang;
        this.tongTien = tongTien;
        this.ngay = ngay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getTenDH() {
        return tenDH;
    }

    public void setTenDH(String tenDH) {
        this.tenDH = tenDH;
    }

    public String getThongTinDH() {
        return thongTinDH;
    }

    public void setThongTinDH(String thongTinDH) {
        this.thongTinDH = thongTinDH;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    @Override
    public String toString() {
        return this.id + this.maDH + this.tenDH + this.thongTinDH
                + this.tinhTrang
                + this.tongTien + this.ngay;
    }
}

