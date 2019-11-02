package com.example.appbangiay.data_models;

public class ThanhToan {
    private String id, tenSanPham, maSanPham, size ,soLuong;
    private double gia;

    public ThanhToan() {
    }

    public ThanhToan(String id, String maSanPham, String tenSanPham , String soLuong , String size,double gia) {
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.gia = gia;
        this.size = size;
        this.soLuong = soLuong;
        this.gia = gia;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
}
