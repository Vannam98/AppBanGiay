package com.example.appbangiay.data_models;

public class ThanhToanKhachHang {

    private String id,tenkhachhang,sodienthoai,diachi;
    private double tongtien;

    public ThanhToanKhachHang() {
    }

    public ThanhToanKhachHang(String id, String tenkhachhang, String sodienthoai, String diachi, String phivanchuyen, double tongtien) {
        this.tenkhachhang = tenkhachhang;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
        this.tongtien = tongtien;
        this.id = id;
}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }
}
