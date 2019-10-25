package com.example.appbangiay.data_models;

public class DonHangHuy {

    private String maSanPham;
    private String tenSanPham;
    private String soLuong;
    private String size;
    private boolean tinhTrang = false;
    private String liDoHuy;
    private String id;


    public DonHangHuy() {

    }

    public DonHangHuy(String id, String maSanPham, String tenSanPham, String soLuong, String size, boolean tinhTrang, String liDoHuy) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.size = size;
        this.tinhTrang = tinhTrang;
        this.liDoHuy = liDoHuy;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
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

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getLiDoHuy() {
        return liDoHuy;
    }

    public void setLiDoHuy(String liDoHuy) {
        this.liDoHuy = liDoHuy;
    }

}

