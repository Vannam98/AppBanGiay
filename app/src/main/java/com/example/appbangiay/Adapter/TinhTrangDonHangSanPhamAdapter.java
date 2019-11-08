package com.example.appbangiay.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.ThanhToan;
import com.example.appbangiay.data_models.TinhTrangDonHang;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TinhTrangDonHangSanPhamAdapter extends ArrayAdapter<ThanhToan> {

    public static Intent intent;
    private Activity context;
    private int layoutID;
    private ArrayList<ThanhToan> data;

    public TinhTrangDonHangSanPhamAdapter(Activity context, int resource, ArrayList<ThanhToan> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.data = objects;
    }
    private class ViewHolder {
        TextView txt_Tensanpham;
        TextView txt_Soluong;
        TextView txt_Size;
        TextView txt_Tongtien;
        TextView txt_tinhTrang;
        Button btn_capNhat;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final TinhTrangDonHangSanPhamAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new TinhTrangDonHangSanPhamAdapter.ViewHolder();
            viewHolder.txt_Tensanpham = convertView.findViewById(R.id.txt_Tensanpham);
            viewHolder.txt_Soluong = convertView.findViewById(R.id.txt_Soluong);
            viewHolder.txt_Size = convertView.findViewById(R.id.txt_Size);
            viewHolder.txt_Tongtien = convertView.findViewById(R.id.txt_Tongtien);
            viewHolder.txt_tinhTrang = convertView.findViewById(R.id.txt_TT);
            viewHolder.btn_capNhat = convertView.findViewById(R.id.btn_capNhat);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TinhTrangDonHangSanPhamAdapter.ViewHolder) convertView.getTag();
        }

        final ThanhToan sp = data.get(position);
        if(sp.isXacNhan()){
            viewHolder.txt_tinhTrang.setText("Đang giao");
            viewHolder.btn_capNhat.setVisibility(View.GONE);
        }
        else
        {
            viewHolder.btn_capNhat.setVisibility(View.VISIBLE);
        }
        viewHolder.txt_Tensanpham.setText("Tên sản phẩm:" + sp.getTenSanPham());
        viewHolder.txt_Soluong.setText("Số lượng: "+ sp.getSoLuong());
        viewHolder.txt_Size.setText("Size:"+sp.getSize());
        viewHolder.txt_Tongtien.setText("Tổng tiền: "+sp.getGia()+"");

        viewHolder.btn_capNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capNhatTinhTrang(sp.getId(),sp,"Đang giao");
            }
        });



        return convertView;

    }

    public int getCount() {
        return data.size();
    }

    @Override
    public ThanhToan getItem(int position) {
        return data.get(position);

    }

    public long getItemId(int position) {
        return position;
    }

    public void capNhatTinhTrang(String id, ThanhToan thanhToan, String tinhTrang)
    {
        thanhToan.setTinhTrang(tinhTrang);
        thanhToan.setXacNhan(true);
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("TinhTrangDonHang").child(id);
        data.setValue(thanhToan);
    }
}
