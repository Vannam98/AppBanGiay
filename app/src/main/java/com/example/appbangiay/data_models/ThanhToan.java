package com.example.appbangiay.data_models;

public class ThanhToan {
    private String id, name, hang, gia, size ,soluong;

    public ThanhToan() {
    }

    public ThanhToan(String id, String name, String hang, String gia, String size, String soluong ) {
        this.name = name;
        this.hang = hang;
        this.gia = gia;
        this.size = size;
        this.soluong = soluong;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getMau() {
        return gia;
    }

    public void setMau(String mau) {
        this.gia = mau;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
