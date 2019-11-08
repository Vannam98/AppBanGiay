package com.example.appbangiay.data_models;

public class DanhSachHoanThanhQuanLy {
    private  String id, maDonHang,tenSanPham,tenKhachHang,soDienThoaiKH,diaChiKhachHang,nguoiGiao,soDienThoaiNguoiGiao,tinhTrang;
    private double tongTien;

    public DanhSachHoanThanhQuanLy() {
    }

    public DanhSachHoanThanhQuanLy(String id, String maDonHang, String tenSanPham, String tenKhachHang, String soDienThoaiKH, String diaChiKhachHang, String nguoiGiao, String soDienThoaiNguoiGiao, String tinhTrang, double tongTien) {
        this.id = id;
        this.maDonHang = maDonHang;
        this.tenSanPham = tenSanPham;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoaiKH = soDienThoaiKH;
        this.diaChiKhachHang = diaChiKhachHang;
        this.nguoiGiao = nguoiGiao;
        this.soDienThoaiNguoiGiao = soDienThoaiNguoiGiao;
        this.tinhTrang = tinhTrang;
        this.tongTien = tongTien;
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

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoaiKH() {
        return soDienThoaiKH;
    }

    public void setSoDienThoaiKH(String soDienThoaiKH) {
        this.soDienThoaiKH = soDienThoaiKH;
    }

    public String getDiaChiKhachHang() {
        return diaChiKhachHang;
    }

    public void setDiaChiKhachHang(String diaChiKhachHang) {
        this.diaChiKhachHang = diaChiKhachHang;
    }

    public String getNguoiGiao() {
        return nguoiGiao;
    }

    public void setNguoiGiao(String nguoiGiao) {
        this.nguoiGiao = nguoiGiao;
    }

    public String getSoDienThoaiNguoiGiao() {
        return soDienThoaiNguoiGiao;
    }

    public void setSoDienThoaiNguoiGiao(String soDienThoaiNguoiGiao) {
        this.soDienThoaiNguoiGiao = soDienThoaiNguoiGiao;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}
