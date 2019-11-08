package com.example.appbangiay.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.example.appbangiay.data_models.QuanLyNhanVien;
import com.example.appbangiay.R;

public class AdapterQuanLyNhanVien extends ArrayAdapter<QuanLyNhanVien>
{
    private Activity context;
    private ArrayList<QuanLyNhanVien> quanLyNhanVien;
    private int layoutID;

    public AdapterQuanLyNhanVien(@NonNull Activity context, int resource, @NonNull ArrayList<QuanLyNhanVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.quanLyNhanVien = objects;
    }

    public class ViewHolder
    {
        TextView txt_hoTen, txt_tenTK, txt_email, txt_gioiTinh, txt_chucVu;
        //ImageView img_hinhAnh;
    }
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_hoTen = convertView.findViewById(R.id.txt_HVT);
            viewHolder.txt_tenTK = convertView.findViewById(R.id.txt_TTK);
            viewHolder.txt_email = convertView.findViewById(R.id.txt_E);
            viewHolder.txt_gioiTinh = convertView.findViewById(R.id.txt_GT);
            viewHolder.txt_chucVu = convertView.findViewById(R.id.txt_CV);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        QuanLyNhanVien ql = quanLyNhanVien.get(position);
        viewHolder.txt_hoTen.setText(ql.getFullName());
        viewHolder.txt_tenTK.setText(ql.getUsername());
        viewHolder.txt_email.setText(ql.getEmail());
        viewHolder.txt_gioiTinh.setText(ql.getGender());
        viewHolder.txt_chucVu.setText(ql.getJob());
//        Glide.with(context)
//                .load(ql.getImage())
//                .centerCrop()
//                .into(viewHolder.img_hinhAnh);



        return convertView;
    }

    public int getCount() {
        return quanLyNhanVien.size();
    }

    @Override
    public QuanLyNhanVien getItem(int position) {
        return quanLyNhanVien.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
}

