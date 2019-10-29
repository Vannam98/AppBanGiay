package com.example.appbangiay.data_models;

public class DanhSachDagiao {
    private String id, masp,tensp,soluong,tenkhachhang,sdtkhachhang,diachi, tinhtrang;
    private double tongtien;
    public DanhSachDagiao() {
    }

    public DanhSachDagiao(String id, String masp, String tensp, String soluong, String tenkhachhang, String sdtkhachhang, String diachi, String tinhtrang, double tongtien) {
        this.id = id;
        this.masp = masp;
        this.tensp = tensp;
        this.soluong = soluong;
        this.tenkhachhang = tenkhachhang;
        this.sdtkhachhang = sdtkhachhang;
        this.diachi = diachi;
        this.tinhtrang = tinhtrang;
        this.tongtien = tongtien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getSdtkhachhang() {
        return sdtkhachhang;
    }

    public void setSdtkhachhang(String sdtkhachhang) {
        this.sdtkhachhang = sdtkhachhang;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }
}