package com.example.appbangiay.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.DonHangHuyQuanLy;


import java.util.ArrayList;


public class DonHangHuyAdapter extends ArrayAdapter<DonHangHuyQuanLy> {

    public static Intent intent;
    private Activity context;
    private int layoutID;
    private ArrayList<DonHangHuyQuanLy> data;

    public DonHangHuyAdapter(Activity context, int resource, ArrayList<DonHangHuyQuanLy> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.data = objects;
    }

    public class ViewHolder{
        TextView txt_Madonhang;
        TextView txt_Tensanpham;
        TextView txt_Soluong;
        TextView txt_Size;
        TextView txt_Tenkhachhang;
        TextView txt_Sodienthoai;
        TextView txt_Diachi;
        TextView txt_Tinhtrang;
        TextView txt_Tongtien;
        TextView txt_Lydohuy;
        SearchView srTimKiem;
        LinearLayout lnItem;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final DonHangHuyAdapter.ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layoutID,parent,false);
            viewHolder = new DonHangHuyAdapter.ViewHolder();
            viewHolder.lnItem = convertView.findViewById(R.id.lnItem);
            viewHolder.txt_Madonhang = convertView.findViewById(R.id.txt_Madonhang);
            viewHolder.txt_Tensanpham = convertView.findViewById(R.id.txt_Tensanpham);
            viewHolder.txt_Soluong = convertView.findViewById(R.id.txt_Soluong);
            viewHolder.txt_Size = convertView.findViewById(R.id.txt_Size);
            viewHolder.txt_Tenkhachhang= convertView.findViewById(R.id.txt_Tenkhachhang);
            viewHolder.txt_Sodienthoai = convertView.findViewById(R.id.txt_Sodienthoai);
            viewHolder.txt_Diachi = convertView.findViewById(R.id.txt_Diachi);
            viewHolder.txt_Tongtien = convertView.findViewById(R.id.txt_Tongtien);
            viewHolder.txt_Tinhtrang = convertView.findViewById(R.id.txt_Tinhtrang);
            viewHolder.txt_Lydohuy = convertView.findViewById(R.id.txt_Lydohuy);
            viewHolder.srTimKiem = convertView.findViewById(R.id.srTimKiem);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (DonHangHuyAdapter.ViewHolder)convertView.getTag();
        }

        final DonHangHuyQuanLy donHangHuyQuanLy = data.get(position);
        viewHolder.txt_Madonhang.setText("Mã đơn hàng:" + donHangHuyQuanLy.getMaDonHang());
        viewHolder.txt_Tensanpham.setText("Tên sản phẩm:" + donHangHuyQuanLy.getTenSanPham());
        viewHolder.txt_Soluong.setText("Số lượng:" + donHangHuyQuanLy.getSoLuong());
        viewHolder.txt_Size.setText("Size:" + donHangHuyQuanLy.getSize());
        viewHolder.txt_Tenkhachhang.setText("Tên khách hàng:" + donHangHuyQuanLy.getTenKhachHang());
        viewHolder.txt_Sodienthoai.setText("Số điện thoại:" + donHangHuyQuanLy.getSoDienThoai());
        viewHolder.txt_Diachi.setText("Địa chỉ:" + donHangHuyQuanLy.getDiaChi());
        viewHolder.txt_Tinhtrang.setText("Tình trạng:" + donHangHuyQuanLy.getTinhTrang());
        viewHolder.txt_Lydohuy.setText("Lý do hủy: " + donHangHuyQuanLy.getLiDoHuy());
        viewHolder.txt_Tongtien.setText("Tổng tiền: " + donHangHuyQuanLy.getTongTien());

        return convertView;
    }

    public int getCount(){
        return data.size();
    }

    public DonHangHuyQuanLy getItem(int position)
    {
        return data.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }


}
