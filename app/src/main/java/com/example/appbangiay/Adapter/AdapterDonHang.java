package com.example.appbangiay.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.DonHang;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterDonHang extends ArrayAdapter<DonHang>
{
    private Activity context;
    private ArrayList<DonHang> donHang;
    private int layoutID;

    public AdapterDonHang( Activity context, int resource , ArrayList<DonHang> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.donHang = objects;
    }

    public class ViewHolder
    {
        TextView txt_maSP, txt_tenSP, txt_soLuong, txt_size, txt_tongTien, txt_id, txt_tenKhachHang, txt_soDienThoai, txt_diaChi;
        CheckBox chk_XN;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layoutID,parent,false);
            viewHolder = new ViewHolder();
            //viewHolder.txt_id = convertView.findViewById(R.id.txt_id);
            viewHolder.txt_maSP = convertView.findViewById(R.id.txt_MSP);
            viewHolder.txt_tenSP = convertView.findViewById(R.id.txt_TSP);
            viewHolder.txt_soLuong = convertView.findViewById(R.id.txt_SL);
            viewHolder.txt_size = convertView.findViewById(R.id.txt_S);
            viewHolder.txt_tenKhachHang = convertView.findViewById(R.id.txt_TKH);
            viewHolder.txt_soDienThoai = convertView.findViewById(R.id.txt_SDTKH);
            viewHolder.txt_diaChi = convertView.findViewById(R.id.txt_DCKH);
            viewHolder.txt_tongTien = convertView.findViewById(R.id.txt_TT);
            viewHolder.chk_XN = convertView.findViewById(R.id.chk_xacnhan);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final DonHang dh = donHang.get(position);
        //viewHolder.txt_id.setText(dh.getId());
        viewHolder.txt_maSP.setText(dh.getMaDonHang());
        viewHolder.txt_tenSP.setText(dh.getTenSanPham());
        viewHolder.txt_soLuong.setText(dh.getSoLuong());
        viewHolder.txt_size.setText(dh.getSize());
        viewHolder.txt_tenKhachHang.setText(dh.getTenKhachHang());
        viewHolder.txt_soDienThoai.setText(dh.getSoDienThoai());
        viewHolder.txt_diaChi.setText(dh.getDiaChi());
        viewHolder.txt_tongTien.setText(String.valueOf(dh.getTongTien()));
        viewHolder.chk_XN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(viewHolder.chk_XN.isChecked())
                {
                    xacNhanDonHang(dh.getId(), dh);
                }
                else
                {
                    delete(dh.getId());
                }
            }
        });
        return convertView;

    }

    public int getCount() {
        return donHang.size();
    }

    @Override
    public DonHang getItem(int position) {
        return donHang.get(position);
    }

    public long getItemId(int position) {
        return position;
    }



    public void xacNhanDonHang(String id, DonHang donHang)
    {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference();
        //String id = data.child("DonHangDatMua").push().getKey();
        data.child("DonHangDatMua").child(id).setValue(donHang);
    }

    public void delete(String id)
    {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("DonHangDatMua").child(id);
        data.removeValue();
    }

}
