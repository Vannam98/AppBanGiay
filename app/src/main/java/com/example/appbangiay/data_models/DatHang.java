package com.example.appbangiay.data_models;

import java.util.ArrayList;

public class DatHang {
        private  String id ;
        private ThanhToanKhachHang thongTinKH;
        private ArrayList<ThanhToan> dsSanPham;

        public DatHang(String id , ThanhToanKhachHang thongTinKH, ArrayList<ThanhToan> dsSanPham) {
            this.id = id;
            this.thongTinKH = thongTinKH;
            this.dsSanPham = dsSanPham;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public DatHang() {
        }

        public ThanhToanKhachHang getThongTinKH() {
            return thongTinKH;
        }

        public void setThongTinKH(ThanhToanKhachHang thongTinKH) {
            this.thongTinKH = thongTinKH;
        }

        public ArrayList<ThanhToan> getDsSanPham() {
            return dsSanPham;
        }

        public void setDsSanPham(ArrayList<ThanhToan> dsSanPham) {
            this.dsSanPham = dsSanPham;
        }
    }

