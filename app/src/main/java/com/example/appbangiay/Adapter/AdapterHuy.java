package com.example.appbangiay.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.DonHangHuy;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterHuy extends ArrayAdapter<DonHangHuy> {
    private Activity context;
    private ArrayList<DonHangHuy> donHangHuy;
    private int layoutID;

    DatabaseReference mDta = FirebaseDatabase.getInstance().getReference();
    public AdapterHuy(@NonNull Activity context, int resource, @NonNull ArrayList<DonHangHuy> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.donHangHuy = objects;
    }

    public class ViewHolder
    {
        TextView txt_maSP, txt_tenSP, txt_soLuong, txt_size,  txt_tenKhachHang, txt_soDienThoai, txt_diaChi, txt_tinhTrangDonHang, txt_lidoHuyDonHang, txt_tongTien;
        Button btn_XN;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.txt_maSP = convertView.findViewById(R.id.txt_MSP);
            viewHolder.txt_tenSP = convertView.findViewById(R.id.txt_TSP);
            viewHolder.txt_soLuong = convertView.findViewById(R.id.txt_SL);
            viewHolder.txt_size = convertView.findViewById(R.id.txt_S);
            viewHolder.txt_tenKhachHang = convertView.findViewById(R.id.txt_TKH);
            viewHolder.txt_soDienThoai = convertView.findViewById(R.id.txt_SDTKH);
            viewHolder.txt_diaChi = convertView.findViewById(R.id.txt_DCKH);
            viewHolder.txt_tongTien = convertView.findViewById(R.id.txt_TT);
            viewHolder.txt_tinhTrangDonHang = convertView.findViewById(R.id.txt_tinhTrangDonHang);
            viewHolder.txt_lidoHuyDonHang = convertView.findViewById(R.id.txt_liDoHuyDonHang);
            viewHolder.btn_XN = convertView.findViewById(R.id.btn_xacNhan);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final DonHangHuy dhHuy = donHangHuy.get(position);
        if(dhHuy.isXacNhan())
        {
            viewHolder.txt_tinhTrangDonHang.setText("Hủy đơn hàng");
            viewHolder.btn_XN.setVisibility(View.GONE);

        }
        else
        {
            //viewHolder.txt_tinhTrangDonHang.setText("Chưa xác định");
            viewHolder.btn_XN.setVisibility(View.VISIBLE);// hiện button xác nhận
        }
        viewHolder.txt_maSP.setText(dhHuy.getMaDonHang());
        viewHolder.txt_tenSP.setText(dhHuy.getTenSanPham());
        viewHolder.txt_soLuong.setText(dhHuy.getSoLuong());
        viewHolder.txt_size.setText(dhHuy.getSize());
        viewHolder.txt_tenKhachHang.setText(dhHuy.getTenKhachHang());
        viewHolder.txt_soDienThoai.setText(dhHuy.getSoDienThoai());
        viewHolder.txt_diaChi.setText(dhHuy.getDiaChi());
        viewHolder.txt_tongTien.setText(String.valueOf(dhHuy.getGia()));
        viewHolder.txt_lidoHuyDonHang.setText(dhHuy.getLiDoHuy());

        viewHolder.btn_XN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taoDialogHuyDonHang(dhHuy.getId(),dhHuy);
                notifyDataSetChanged();
                //delete(dhHuy.getId());
            }
        });


        return convertView;
    }


    public int getCount() {
        return donHangHuy.size();
    }

    @Override
    public DonHangHuy getItem(int position) {
        return donHangHuy.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    public void capNhatTinhTrang(String id, DonHangHuy donHangHuy, String tinhTrang)
    {
        donHangHuy.setTinhTrang("Hủy đơn hàng");
        donHangHuy.setXacNhan(true);
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("DonHangHuy").child(id);
        data.setValue(donHangHuy);
    }

//    public void delete(String id)
//    {
//        DatabaseReference data = FirebaseDatabase.getInstance().getReference("DonHangDatMua").child(id);
//        data.removeValue();
//    }

    public void taoDialogHuyDonHang(final String id, final DonHangHuy donHangHuy){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_lido_huy_donhang_layout);
        final EditText edtLiDo = dialog.findViewById(R.id.edtLiDo);
        final Button btn_xacNhan = dialog.findViewById(R.id.btn_xacnhan);
        final Button btn_Huy = dialog.findViewById(R.id.btn_Huy);
        dialog.show();

        btn_xacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donHangHuy.setLiDoHuy(edtLiDo.getText().toString());
                capNhatTinhTrang(id,donHangHuy,"Hủy đơn hàng");
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


}
