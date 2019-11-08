package com.example.appbangiay.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.DonHangHoanThanhNhanVien;
import com.example.appbangiay.data_models.HuyGiaoHang;
import com.example.appbangiay.data_models.HuyHoanThanh;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;


public class DonHangHoanThanhNhanVienAdapter extends ArrayAdapter<DonHangHoanThanhNhanVien> {

    public static Intent intent;
    private Activity context;
    private int layoutID;
    private ArrayList<DonHangHoanThanhNhanVien> data;
    private ArrayList<DonHangHoanThanhNhanVien> danhSachHoanThanh;
    HuyHoanThanh huyHoanThanh;


    public DonHangHoanThanhNhanVienAdapter(Activity context, int resource, ArrayList<DonHangHoanThanhNhanVien> objects) {
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


        Button btn_Hoanthanh;
        Button btn_Huy;
        SearchView srTimKiem;
        LinearLayout lnItem;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final DonHangHoanThanhNhanVienAdapter.ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layoutID,parent,false);
            viewHolder = new DonHangHoanThanhNhanVienAdapter.ViewHolder();
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
            viewHolder.btn_Hoanthanh = convertView.findViewById(R.id.btn_Hoanthanh);
            viewHolder.btn_Huy = convertView.findViewById(R.id.btn_Huy);
            viewHolder.srTimKiem = convertView.findViewById(R.id.srTimKiem);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (DonHangHoanThanhNhanVienAdapter.ViewHolder)convertView.getTag();
        }

        final DonHangHoanThanhNhanVien donHangHoanThanh = data.get(position);
        viewHolder.txt_Madonhang.setText("Mã đơn hàng:" + donHangHoanThanh.getMaDonHang());
        viewHolder.txt_Tensanpham.setText("Tên sản phẩm:" + donHangHoanThanh.getTenSanPham());
        viewHolder.txt_Soluong.setText("Số lượng:" + donHangHoanThanh.getSoLuong());
        viewHolder.txt_Size.setText("Size:" + donHangHoanThanh.getSize());
        viewHolder.txt_Tenkhachhang.setText("Tên khách hàng:" + donHangHoanThanh.getTenKhachHang());
        viewHolder.txt_Sodienthoai.setText("Số điện thoại:" + donHangHoanThanh.getSoDienThoai());
        viewHolder.txt_Diachi.setText("Địa chỉ" +  donHangHoanThanh.getDiaChi());
        viewHolder.txt_Tinhtrang.setText("Tình trạng:" + donHangHoanThanh.getTinhTrang());
        viewHolder.txt_Tongtien.setText("Tổng tiền: " + donHangHoanThanh.getTongTien());


        if (donHangHoanThanh.getTinhTrang().equalsIgnoreCase("Hoàn thành")){
            viewHolder.lnItem.setBackgroundColor(context.getResources().getColor(R.color.color_item_xac_nhan));
            viewHolder.btn_Hoanthanh.setEnabled(false);
            viewHolder.btn_Huy.setVisibility(View.GONE);
        }
        else {
            viewHolder.lnItem.setBackgroundColor(context.getResources().getColor(R.color.color_item_chua_xac_nhan));
            viewHolder.btn_Hoanthanh.setEnabled(true);
            viewHolder.btn_Huy.setVisibility(View.VISIBLE);
        }

        viewHolder.btn_Hoanthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanDonHang(donHangHoanThanh.getId(),donHangHoanThanh);
            }
        });

        viewHolder.btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePay(donHangHoanThanh.getId());

            }
        });
        return convertView;
    }

    public int getCount(){
        return data.size();
    }

    public DonHangHoanThanhNhanVien getItem(int position)
    {
        return data.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public void deletePay(String idPay)
    {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("HoanThanhNhanVien");
        data.child(idPay).removeValue();
    }


    public void xacNhanDonHang(String iddata, DonHangHoanThanhNhanVien value){
        DatabaseReference data = FirebaseDatabase.getInstance().getReference();

        //Cap nhat
        value.setTinhTrang("Hoàn thành");
        data.child("HoanThanhNhanVien").child(iddata).setValue(value);

        //Them
        String id = data.child("DonHangHoanThanhQuanLy").push().getKey();
        data.child("DonHangHoanThanhQuanLy").child(id).setValue(value);
    }


}
