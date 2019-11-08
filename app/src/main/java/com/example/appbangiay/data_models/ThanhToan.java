package com.example.appbangiay.data_models;

public class ThanhToan {
    private String id, tenSanPham, maSanPham, size ,soLuong;
    private double gia;
    private boolean isXacNhan = false;
    private String tinhTrang;
    public ThanhToan() {
    }

    public ThanhToan(String id, String maSanPham, String tenSanPham , String soLuong , String size,double gia, String tinhTrang) {
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.gia = gia;
        this.size = size;
        this.soLuong = soLuong;
        this.gia = gia;
        this.id = id;
        this.tinhTrang = tinhTrang;
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

    public boolean isXacNhan() {
        return isXacNhan;
    }

    public void setXacNhan(boolean xacNhan) {
        isXacNhan = xacNhan;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
