package com.example.appbangiay.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.DanhSachDagiao;
import com.example.appbangiay.data_models.DanhSachNguoiGiao;
import com.example.appbangiay.data_models.HuyGiaoHang;
import com.example.appbangiay.data_models.HuyNguoiGiao;
import com.example.appbangiay.data_models.HuyVanChuyen;
import com.example.appbangiay.data_models.ThanhToan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DanhSachNguoiGiaoAdapter extends ArrayAdapter<DanhSachNguoiGiao> {

    private Activity context;
    private int layoutID;
    private ArrayList<DanhSachNguoiGiao> data;
    private ArrayList<DanhSachNguoiGiao> danhSachNguoiGiaos;

    public DanhSachNguoiGiaoAdapter(Activity context, int resource, ArrayList<DanhSachNguoiGiao> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.data = objects;

    }

    public class ViewHolder {
        LinearLayout lnItemNguoiGiao;
        TextView txt_hoten_dsnguoigiao_lsv2, txt_sodienthoai_dsnguoigiao_lsv2, txt_tinhtrang_dsnguoigiao_lsv2;
        Button btn_giao_dsnguoigiao_lsv, btn_Huy_dsnguoigiao_lsv;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final DanhSachNguoiGiaoAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new DanhSachNguoiGiaoAdapter.ViewHolder();
            viewHolder.txt_hoten_dsnguoigiao_lsv2 = convertView.findViewById(R.id.txt_hoten_dsnguoigiao_lsv2);
            viewHolder.txt_sodienthoai_dsnguoigiao_lsv2 = convertView.findViewById(R.id.txt_sodienthoai_dsnguoigiao_lsv2);
            viewHolder.txt_tinhtrang_dsnguoigiao_lsv2 = convertView.findViewById(R.id.txt_tinhtrang_dsnguoigiao_lsv2);
            viewHolder.lnItemNguoiGiao = convertView.findViewById(R.id.lnItemNguoiGiao);
            viewHolder.btn_giao_dsnguoigiao_lsv = convertView.findViewById(R.id.btn_giao_dsnguoigiao_lsv);
            viewHolder.btn_Huy_dsnguoigiao_lsv = convertView.findViewById(R.id.btn_Huy_dsnguoigiao_lsv);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DanhSachNguoiGiaoAdapter.ViewHolder) convertView.getTag();
        }
        final DanhSachNguoiGiao nguoiGiao = data.get(position);
        viewHolder.txt_hoten_dsnguoigiao_lsv2.setText(nguoiGiao.getHoTen());
        viewHolder.txt_sodienthoai_dsnguoigiao_lsv2.setText(nguoiGiao.getSoDienThoai());
        viewHolder.txt_tinhtrang_dsnguoigiao_lsv2.setText(nguoiGiao.getTinhTrang());


        if(nguoiGiao.getTinhTrang().equalsIgnoreCase("Giao")){
            viewHolder.lnItemNguoiGiao.setBackgroundColor(context.getResources().getColor(R.color.color_item_xac_nhan));
            viewHolder.btn_giao_dsnguoigiao_lsv.setVisibility(View.GONE);
            viewHolder.btn_Huy_dsnguoigiao_lsv.setVisibility(View.GONE);

        }else
        {
            viewHolder.lnItemNguoiGiao.setBackgroundColor(context.getResources().getColor(R.color.color_item_chua_xac_nhan));
            viewHolder.btn_giao_dsnguoigiao_lsv.setVisibility(View.VISIBLE);
            viewHolder.btn_Huy_dsnguoigiao_lsv.setVisibility(View.VISIBLE);
        }

        viewHolder.btn_giao_dsnguoigiao_lsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //xoa
        viewHolder.btn_Huy_dsnguoigiao_lsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                chuyendata(nguoiGiao.getId());

//                taoDialogHuyDonHang(huyGiaoHang_.getId(),huyGiaoHang_,true);

                deletePay(nguoiGiao.getId());
                HuyNguoiGiao(nguoiGiao.getId(),nguoiGiao);

            }
        });
        //xacnhan
        viewHolder.btn_giao_dsnguoigiao_lsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanDonHang(nguoiGiao.getId(),nguoiGiao);
            }
        });


        return convertView;

    }



    public int getCount() {
        return data.size();
    }

    @Override
    public DanhSachNguoiGiao getItem(int position) {
        return data.get(position);

    }

    public long getItemId(int position) {
        return position;
    }


    public void deletePay(String idPay){
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("DanhSachNguoiGiao");
        data.child(idPay).removeValue();
    }

public void HuyNguoiGiao(String iddata, DanhSachNguoiGiao value) {
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    //Them du lieu vao don hang da giao
    String id = data.child("DanhSachHuyNguoiGiao").push().getKey();
    value.setId(id);
    value.setTinhTrang("huy");
    data.child("DanhSachHuyNguoiGiao").child(id).setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
        }
    });
}
    public void xacNhanDonHang(String iddata, DanhSachNguoiGiao value) {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference();

        //Cap nhat trang thai don hang
        value.setTinhTrang("giao");
        data.child("DanhSachNguoiGiao").child(iddata).setValue(value);
        //Them du lieu vao don hang da giao
        String id = data.child("DanhSachChonNguoiGiao").push().getKey();
        data.child("DanhSachChonNguoiGiao").child(id).setValue(value);
    }
}