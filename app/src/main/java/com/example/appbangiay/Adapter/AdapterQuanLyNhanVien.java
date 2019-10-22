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
        TextView txt_maNV, txt_tenNV, txt_soDT, txt_diaChi, txt_email, txt_chucVu, txt_id, txt_ngaySinh;
        ImageView img_hinhAnh;
        Button btn_chinhSua;
    }
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_id = convertView.findViewById(R.id.txt_id);
            viewHolder.txt_maNV = convertView.findViewById(R.id.txt_MNV);
            viewHolder.txt_tenNV = convertView.findViewById(R.id.txt_TNV);
            viewHolder.txt_ngaySinh = convertView.findViewById(R.id.txt_NS);
            viewHolder.txt_soDT = convertView.findViewById(R.id.txt_SDTNV);
            viewHolder.txt_diaChi = convertView.findViewById(R.id.txt_DCNV);
            viewHolder.txt_email = convertView.findViewById(R.id.txt_E);
            viewHolder.txt_chucVu = convertView.findViewById(R.id.txt_CV);
            viewHolder.img_hinhAnh = convertView.findViewById(R.id.img_hinhAnh);
            viewHolder.btn_chinhSua = convertView.findViewById(R.id.btn_chinhSua);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        QuanLyNhanVien ql = quanLyNhanVien.get(position);
        viewHolder.txt_id.setText(ql.getId());
        viewHolder.txt_maNV.setText(ql.getMaNhanVien());
        viewHolder.txt_tenNV.setText(ql.getTenNhanVien());
        viewHolder.txt_ngaySinh.setText(ql.getNgaySinh());
        viewHolder.txt_soDT.setText(ql.getSoDienThoai());
        viewHolder.txt_diaChi.setText(ql.getDiaChi());
        viewHolder.txt_email.setText(ql.getEmail());
        viewHolder.txt_chucVu.setText(ql.getChucVu());

        Glide.with(context)
                .load(ql.getImage())
                .centerCrop()
                .into(viewHolder.img_hinhAnh);


        viewHolder.btn_chinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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

