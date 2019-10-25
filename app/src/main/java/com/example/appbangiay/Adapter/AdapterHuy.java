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
        TextView txt_maSP, txt_tenSP, txt_soLuong, txt_size, txt_tinhTrangDonHang, txt_lidoHuyDonHang;
        Button btn_XN, btn_huy;
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
            viewHolder.txt_tinhTrangDonHang = convertView.findViewById(R.id.txt_tinhTrangDonHang);
            viewHolder.txt_lidoHuyDonHang = convertView.findViewById(R.id.txt_liDoHuyDonHang);
            viewHolder.btn_XN = convertView.findViewById(R.id.btn_xacNhan);
            viewHolder.btn_huy = convertView.findViewById(R.id.btn_Huy);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final DonHangHuy dhHuy = donHangHuy.get(position);
        viewHolder.txt_maSP.setText(dhHuy.getMaSanPham());
        viewHolder.txt_tenSP.setText(dhHuy.getTenSanPham());
        viewHolder.txt_soLuong.setText(dhHuy.getSoLuong());
        viewHolder.txt_size.setText(dhHuy.getSize());
        if(dhHuy.isTinhTrang())
        {
            viewHolder.txt_tinhTrangDonHang.setText("Đã Hủy");
            viewHolder.btn_XN.setVisibility(View.GONE);
            viewHolder.btn_huy.setVisibility(View.VISIBLE); //hiện button hủy
        }
        else
        {
            viewHolder.txt_tinhTrangDonHang.setText("Chưa xác định");
            viewHolder.btn_XN.setVisibility(View.VISIBLE);// hiện button xác nhận
            viewHolder.btn_huy.setVisibility(View.GONE);
        }
        viewHolder.txt_lidoHuyDonHang.setText(dhHuy.getLiDoHuy());


        viewHolder.btn_XN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taoDialogHuyDonHang(dhHuy.getId(),dhHuy,true);
            }
        });

        viewHolder.btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capNhatTinhTrang(dhHuy.getId(),dhHuy,false);
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


    public void capNhatTinhTrang(String id, DonHangHuy donHangHuy, boolean tinhTrang)
    {
        donHangHuy.setTinhTrang(tinhTrang);
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("DonHangHuy").child(id);
        data.setValue(donHangHuy);
    }


    public void taoDialogHuyDonHang(final String id, final DonHangHuy donHangHuy, final boolean tinhTrang){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_lido_huy_donhang_layout);
        final EditText edtLiDo = dialog.findViewById(R.id.edtLiDo);
        final Button btn_xacNhan = dialog.findViewById(R.id.btn_xacNhan);
        final Button btn_Huy = dialog.findViewById(R.id.btn_Huy);
        dialog.show();

        btn_xacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donHangHuy.setLiDoHuy(edtLiDo.getText().toString());
                capNhatTinhTrang(id,donHangHuy,tinhTrang);
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
