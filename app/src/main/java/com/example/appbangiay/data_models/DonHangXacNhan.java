package com.example.appbangiay.data_models;

public class DonHangXacNhan {
    private String id;
    private String maDonHang;
    private String tenSanPham;
    private String soLuong;
    private String size;
    private String tenKhachHang;
    private String soDT;
    private String diaChi;
    private double tongTien;
    private boolean tinhTrang = false;
    private boolean isXacNhan = false;

    public DonHangXacNhan()
    {

    }

    public DonHangXacNhan(String id, String maDonHang, String tenSanPham, String soLuong, String size, String tenKhachHang, String soDT, String diaChi, double tongTien) {
        this.maDonHang = maDonHang;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.size = size;
        this.tenKhachHang = tenKhachHang;
        this.soDT = soDT;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
        this.id = id;
    }

    public boolean isXacNhan() {
        return isXacNhan;
    }

    public void setXacNhan(boolean xacNhan) {
        isXacNhan = xacNhan;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
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

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}

