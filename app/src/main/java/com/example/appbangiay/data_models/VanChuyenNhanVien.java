package com.example.appbangiay.data_models;

import android.os.Parcel;
import android.os.Parcelable;

public class VanChuyenNhanVien implements Parcelable {
    private String id, maDonHang,tenSanPham,soLuong, size,tinhTrang,tenKhachHang,soDienThoai,diaChi;
    private double tongtien;

    public VanChuyenNhanVien() {
    }

    public VanChuyenNhanVien(String id,String maDonHang, String tenSanPham, String soLuong, String size, String tinhTrang, String tenKhachHang, String soDienThoai, String diaChi, double tongtien) {
        this.maDonHang = maDonHang;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.size = size;
        this.tinhTrang = tinhTrang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.tongtien = tongtien;
        this.id = id;
    }

    protected VanChuyenNhanVien(Parcel in) {
        id = in.readString();
        maDonHang = in.readString();
        tenSanPham = in.readString();
        soLuong = in.readString();
        size = in.readString();
        tinhTrang = in.readString();
        tenKhachHang = in.readString();
        soDienThoai = in.readString();
        diaChi = in.readString();
        tongtien = in.readDouble();
    }

    public static final Creator<VanChuyenNhanVien> CREATOR = new Creator<VanChuyenNhanVien>() {
        @Override
        public VanChuyenNhanVien createFromParcel(Parcel in) {
            return new VanChuyenNhanVien(in);
        }

        @Override
        public VanChuyenNhanVien[] newArray(int size) {
            return new VanChuyenNhanVien[size];
        }
    };

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

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
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

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(maDonHang);
        dest.writeString(tenSanPham);
        dest.writeString(soLuong);
        dest.writeString(size);
        dest.writeString(tinhTrang);
        dest.writeString(tenKhachHang);
        dest.writeString(soDienThoai);
        dest.writeString(diaChi);
        dest.writeDouble(tongtien);
    }
}
