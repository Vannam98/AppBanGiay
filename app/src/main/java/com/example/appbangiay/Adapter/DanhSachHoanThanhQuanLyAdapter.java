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
import com.example.appbangiay.data_models.DanhSachHoanThanhQuanLy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class DanhSachHoanThanhQuanLyAdapter extends ArrayAdapter<DanhSachHoanThanhQuanLy>{


    public static Intent intent;
    private Activity context;
    private int layoutID;
    private ArrayList<DanhSachHoanThanhQuanLy> data;
    private ArrayList<DanhSachHoanThanhQuanLy> danhSachHoanThanhQuanLys;

    public DanhSachHoanThanhQuanLyAdapter(Activity context, int resource, ArrayList<DanhSachHoanThanhQuanLy> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.data = objects;
    }

    public class ViewHolder {
        TextView txt_madonhang_dsdonhanghoanthanh1,txt_tensanpham_dsdonhanghoanthanh1,txt_tenkhachhang_dsdonhanghoanthanh1,txt_sodienthoaikhachhang_dsdonhanghoanthanh1,txt_diachikhachhang_dsdonhanghoanthanh1,txt_size_dsdonhanghoanthanh,txt_soluong_dsdonhanghoanthanh,txt_tongtien_dsdonhanghoanthanh1,txt_tinhtrang_dsdonhanghoanthanh1  ;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final DanhSachHoanThanhQuanLyAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new DanhSachHoanThanhQuanLyAdapter.ViewHolder();
            viewHolder.txt_madonhang_dsdonhanghoanthanh1 = convertView.findViewById(R.id.txt_madonhang_dsdonhanghoanthanh1);
            viewHolder.txt_tensanpham_dsdonhanghoanthanh1 = convertView.findViewById(R.id.txt_tensanpham_dsdonhanghoanthanh1);
            viewHolder.txt_tenkhachhang_dsdonhanghoanthanh1 = convertView.findViewById(R.id.txt_tenkhachhang_dsdonhanghoanthanh1);
            viewHolder.txt_sodienthoaikhachhang_dsdonhanghoanthanh1 = convertView.findViewById(R.id.txt_sodienthoaikhachhang_dsdonhanghoanthanh1);
            viewHolder.txt_diachikhachhang_dsdonhanghoanthanh1 = convertView.findViewById(R.id.txt_diachikhachhang_dsdonhanghoanthanh1);
            viewHolder.txt_size_dsdonhanghoanthanh = convertView.findViewById(R.id.txt_size_dsdonhanghoanthanh);
            viewHolder.txt_soluong_dsdonhanghoanthanh = convertView.findViewById(R.id.txt_soluong_dsdonhanghoanthanh);
            viewHolder.txt_tongtien_dsdonhanghoanthanh1 = convertView.findViewById(R.id.txt_tongtien_dsdonhanghoanthanh1);
            viewHolder.txt_tinhtrang_dsdonhanghoanthanh1 = convertView.findViewById(R.id.txt_tinhtrang_dsdonhanghoanthanh1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DanhSachHoanThanhQuanLyAdapter.ViewHolder) convertView.getTag();
        }


        final DanhSachHoanThanhQuanLy hoanThanhQuanLyModel = data.get(position);
        viewHolder.txt_madonhang_dsdonhanghoanthanh1.setText(hoanThanhQuanLyModel.getMaDonHang());
        viewHolder.txt_tensanpham_dsdonhanghoanthanh1.setText(hoanThanhQuanLyModel.getTenSanPham());
        viewHolder.txt_tenkhachhang_dsdonhanghoanthanh1.setText(hoanThanhQuanLyModel.getTenKhachHang());
        viewHolder.txt_sodienthoaikhachhang_dsdonhanghoanthanh1.setText(hoanThanhQuanLyModel.getSoDienThoai());
        viewHolder.txt_diachikhachhang_dsdonhanghoanthanh1.setText(hoanThanhQuanLyModel.getDiaChi());
        viewHolder.txt_size_dsdonhanghoanthanh.setText(hoanThanhQuanLyModel.getSize());
        viewHolder.txt_soluong_dsdonhanghoanthanh.setText(hoanThanhQuanLyModel.getSoLuong());
        viewHolder.txt_tongtien_dsdonhanghoanthanh1.setText(String.valueOf(hoanThanhQuanLyModel.getTongtien()));
        viewHolder.txt_tinhtrang_dsdonhanghoanthanh1.setText(hoanThanhQuanLyModel.getTinhTrang());

        return convertView;

    }

    public int getCount() {
        return data.size();
    }

    @Override
    public DanhSachHoanThanhQuanLy getItem(int position) {
        return data.get(position);

    }

    public long getItemId(int position) {
        return position;
    }


}
