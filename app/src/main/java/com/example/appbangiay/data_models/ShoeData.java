package com.example.appbangiay.data_models;

public class ShoeData {

    private String tenSanPham;
    private String moTa;
    private String size;
    private String soLuong;
    private String gia;
    private String itemImage;

    public ShoeData() {
    }

    public ShoeData(String tenSanPham, String moTa, String size, String soLuong, String gia, String itemImage) {
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.size = size;
        this.soLuong = soLuong;
        this.gia = gia;
        this.itemImage = itemImage;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
