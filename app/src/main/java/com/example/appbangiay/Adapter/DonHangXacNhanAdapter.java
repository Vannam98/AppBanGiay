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
import com.example.appbangiay.data_models.DonHangXacNhanQuanLy;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;


public class DonHangXacNhanAdapter extends ArrayAdapter<DonHangXacNhanQuanLy> {

    public static Intent intent;
    private Activity context;
    private int layoutID;
    private ArrayList<DonHangXacNhanQuanLy> data;


    public DonHangXacNhanAdapter(Activity context, int resource, ArrayList<DonHangXacNhanQuanLy> objects) {
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
        SearchView srTimKiem;
        LinearLayout lnItem;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final DonHangXacNhanAdapter.ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layoutID,parent,false);
            viewHolder = new DonHangXacNhanAdapter.ViewHolder();
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
            viewHolder.srTimKiem = convertView.findViewById(R.id.srTimKiem);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (DonHangXacNhanAdapter.ViewHolder)convertView.getTag();
        }

        final DonHangXacNhanQuanLy donHangXacNhanQuanLy = data.get(position);
        viewHolder.txt_Madonhang.setText("Mã đơn hàng:" + donHangXacNhanQuanLy.getMaDonHang());
        viewHolder.txt_Tensanpham.setText("Tên sản phẩm:" + donHangXacNhanQuanLy.getTenSanPham());
        viewHolder.txt_Soluong.setText("Số lượng:" + donHangXacNhanQuanLy.getSoLuong());
        viewHolder.txt_Size.setText("Size:" + donHangXacNhanQuanLy.getSize());
        viewHolder.txt_Tenkhachhang.setText("Tên khách hàng:" + donHangXacNhanQuanLy.getTenKhachHang());
        viewHolder.txt_Sodienthoai.setText("Số điện thoại:" + donHangXacNhanQuanLy.getSoDienThoai());
        viewHolder.txt_Diachi.setText("Địa chỉ:" + donHangXacNhanQuanLy.getDiaChi());
        viewHolder.txt_Tinhtrang.setText("Tình trạng:" + donHangXacNhanQuanLy.getTinhTrang());
        viewHolder.txt_Tongtien.setText("Tổng tiền: " + donHangXacNhanQuanLy.getTongTien());

        return convertView;
    }

    public int getCount(){
        return data.size();
    }

    public DonHangXacNhanQuanLy getItem(int position)
    {
        return data.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public void XacNhanDonHang(String iddata, DonHangXacNhanQuanLy value) {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference();
        value.setTinhTrang("đã giao");
        data.child("XacNhanDonHang").child(iddata).setValue(value);
    }
}
