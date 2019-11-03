package com.example.appbangiay.data_models;

public class QuanLyNhanVien {
    private String maNhanVien;
    private String tenNhanVien;
    private String soDienThoai;
    private String diaChi;
    private String email;
    private String chucVu;
    private String id;
    private String image;
    private String passWord;
    private String ngaySinh;

    public QuanLyNhanVien() {

    }


    public QuanLyNhanVien(String id, String maNhanVien, String tenNhanVien, String ngaySinh, String soDienThoai, String diaChi, String email, String chucVu, String image, String passWord) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.email = email;
        this.chucVu = chucVu;
        this.id = id;
        this.image = image;
        this.passWord = passWord;
        this.ngaySinh = ngaySinh;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}

