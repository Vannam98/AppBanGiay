package com.example.appbangiay.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.appbangiay.R;
import com.example.appbangiay.data_models.DonHangHuy;
import com.example.appbangiay.data_models.TinhTrangDonHang;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class TinhTrangDonHangAdapter extends ArrayAdapter<TinhTrangDonHang> {

    public static Intent intent;
    private Activity context;
    private int layoutID;
    private ArrayList<TinhTrangDonHang> data;

    public TinhTrangDonHangAdapter(Activity context, int resource, ArrayList<TinhTrangDonHang> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.data = objects;
    }

    private class ViewHolder{
        TextView txt_Tenkhachhang;
        TextView txt_SodienthoaiKH;
        TextView txt_Diachigiao;
        TextView txt_Tongtien;
        ListView lvDanhSachsanpham;
        //TextView txt_TinhTrang;
        //Button btn_capNhat;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_Tenkhachhang = convertView.findViewById(R.id.txt_Tenkhachhang);
            viewHolder.txt_SodienthoaiKH = convertView.findViewById(R.id.txt_SodienthoaiKH);
            viewHolder.txt_Diachigiao = convertView.findViewById(R.id.txt_Diachigiao);
            viewHolder.txt_Tongtien = convertView.findViewById(R.id.txt_Tongtien);
            viewHolder.lvDanhSachsanpham = convertView.findViewById(R.id.lvDanhSachsanpham);



            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final TinhTrangDonHang tinhtrangModel = data.get(position);

        TinhTrangDonHangSanPhamAdapter donHangSanPhamAdapter = new TinhTrangDonHangSanPhamAdapter(context,R.layout.listview_tinhtrangdonhangsanpham_layout,tinhtrangModel.getDsSanPham());
        viewHolder.lvDanhSachsanpham.setAdapter(donHangSanPhamAdapter);
        viewHolder.txt_Tenkhachhang.setText("Tên khách hàng: "+tinhtrangModel.getThongTinKH().getTenkhachhang());
        viewHolder.txt_SodienthoaiKH.setText("Số điện thoại: "+tinhtrangModel.getThongTinKH().getSodienthoai());
        viewHolder.txt_Diachigiao.setText("Địa chỉ: "+ tinhtrangModel.getThongTinKH().getDiachi());
        viewHolder.txt_Tongtien.setText("Tổng tiền: "+ tinhtrangModel.getThongTinKH().getTongtien()+"");


        return convertView;

    }

    public int getCount() {
        return data.size();
    }

    @Override
    public TinhTrangDonHang getItem(int position) {
        return data.get(position);

    }

    public long getItemId(int position) {
        return position;
    }


}



