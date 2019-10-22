package com.example.appbangiay.data_models;

public class DonHang {
    private String masp,tensp,soluong, size, id;
    private double tongTien;

    public DonHang() {

    }

    public DonHang(String id, String masp, String tensp, String soluong, String size, double tongTien) {
        this.masp = masp;
        this.tensp = tensp;
        this.soluong = soluong;
        this.size = size;
        this.tongTien = tongTien;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
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
}
