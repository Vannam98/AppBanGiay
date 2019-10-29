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
import com.example.appbangiay.data_models.DanhSachDaGiaoQuanLy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DanhSachDaGiaoQuanLyAdapter extends ArrayAdapter<DanhSachDaGiaoQuanLy> {

    public static Intent intent;
    private Activity context;
    private int layoutID;
    private ArrayList<DanhSachDaGiaoQuanLy> data;
    private ArrayList<DanhSachDaGiaoQuanLy> danhSachDagiao;



    public DanhSachDaGiaoQuanLyAdapter(Activity context, int resource, ArrayList<DanhSachDaGiaoQuanLy> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.data = objects;
    }

    public class ViewHolder {
        TextView txt_madonhang_dsdagiao2, txt_tensanpham_dsdagiao2, txt_soluong_dsdagiao2, txt_tenkhachhang_dsdagiao2, txt_sodienthoaikhachhang_dsdagiao2, txt_diachi_dsdagiao2, txt_tongtien_dsdagiao2, tinhtrang_dsdagiaoqly_lsv2;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final DanhSachDaGiaoQuanLyAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new DanhSachDaGiaoQuanLyAdapter.ViewHolder();
            viewHolder.txt_madonhang_dsdagiao2 = convertView.findViewById(R.id.txt_madonhang_dsdagiao2);
            viewHolder.txt_tensanpham_dsdagiao2 = convertView.findViewById(R.id.txt_tensanpham_dsdagiao2);
            viewHolder.txt_soluong_dsdagiao2 = convertView.findViewById(R.id.txt_soluong_dsdagiao2);
            viewHolder.txt_tenkhachhang_dsdagiao2 = convertView.findViewById(R.id.txt_tenkhachhang_dsdagiao2);
            viewHolder.txt_sodienthoaikhachhang_dsdagiao2 = convertView.findViewById(R.id.txt_sodienthoaikhachhang_dsdagiao2);
            viewHolder.txt_diachi_dsdagiao2 = convertView.findViewById(R.id.txt_diachi_dsdagiao2);
            viewHolder.txt_tongtien_dsdagiao2 = convertView.findViewById(R.id.txt_tongtien_dsdagiao2);
            viewHolder.tinhtrang_dsdagiaoqly_lsv2 = convertView.findViewById(R.id.tinhtrang_dsdagiaoqly_lsv2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DanhSachDaGiaoQuanLyAdapter.ViewHolder) convertView.getTag();
        }


        final DanhSachDaGiaoQuanLy dagiaoqlyModel = data.get(position);
        viewHolder.txt_madonhang_dsdagiao2.setText(dagiaoqlyModel.getMasp());
        viewHolder.txt_tensanpham_dsdagiao2.setText(dagiaoqlyModel.getTensp());
        viewHolder.txt_soluong_dsdagiao2.setText(dagiaoqlyModel.getSoluong());
        viewHolder.txt_tenkhachhang_dsdagiao2.setText(dagiaoqlyModel.getTenkhachhang());
        viewHolder.txt_sodienthoaikhachhang_dsdagiao2.setText(dagiaoqlyModel.getSdtkhachhang());
        viewHolder.txt_diachi_dsdagiao2.setText(dagiaoqlyModel.getDiachi());
        viewHolder.txt_tongtien_dsdagiao2.setText(String.valueOf(dagiaoqlyModel.getTongtien()));
        viewHolder.tinhtrang_dsdagiaoqly_lsv2.setText(dagiaoqlyModel.getTinhtrang());



        return convertView;

    }

    public int getCount() {
        return data.size();
    }

    @Override
    public DanhSachDaGiaoQuanLy getItem(int position) {
        return data.get(position);

    }

    public long getItemId(int position) {
        return position;
    }


}

