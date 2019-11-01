package com.example.appbangiay.data_models;

public class DonHangXacNhanQuanLy {
    public String id;
    public String maDH;
    public String tenSP;
    public String soLuong;
    public String size;
    public String tenKH;
    public String soDTKH;
    public String diaChiKH;
    public String tongTien;
    public String tinhTrang;

    public DonHangXacNhanQuanLy(){

    }

    public DonHangXacNhanQuanLy(String id, String maDH, String tenSP, String soLuong, String size, String tenKH, String soDTKH, String diaChiKH, String tongTien, String tinhTrang) {
        this.id = id;
        this.maDH = maDH;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.size = size;
        this.tenKH = tenKH;
        this.soDTKH = soDTKH;
        this.diaChiKH = diaChiKH;
        this.tongTien = tongTien;
        this.tinhTrang = tinhTrang;
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

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoDTKH() {
        return soDTKH;
    }

    public void setSoDTKH(String soDTKH) {
        this.soDTKH = soDTKH;
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return  id + maDH +tenSP + soLuong + size + tenKH + soDTKH + diaChiKH +  tongTien + tinhTrang;
    }
}

