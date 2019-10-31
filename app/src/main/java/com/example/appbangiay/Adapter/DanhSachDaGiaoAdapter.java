package com.example.appbangiay.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.DanhSachDagiao;
import com.example.appbangiay.data_models.HuyGiaoHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DanhSachDaGiaoAdapter extends ArrayAdapter<DanhSachDagiao> {

    public static Intent intent;
    private Activity context;
    private int layoutID;
    private ArrayList<DanhSachDagiao> data;
    private ArrayList<DanhSachDagiao> danhSachDagiao;
    HuyGiaoHang huyGiaoHang_;



    public DanhSachDaGiaoAdapter(Activity context, int resource, ArrayList<DanhSachDagiao> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.data = objects;
    }

    public class ViewHolder {

        TextView masanpham_dsdagiao_lsv2,size_dsdagiao_lsv2, tensanpham_dsdagiao_lsv2, soluong_dsdagiao_lsv2, tenkhachhang_dsdagiao_lsv2, sodienthoaikhachhang_dsdagiao_lsv2, diachi_dsdagiao_lsv2, tongtien_dsdagiao_lsv2, tinhtrang_dsdagiao_lsv2;
        Button btn_xacnhan_dsdagiao_lsv, btn_Huy_dsdagiao_lsv;
        SearchView sv_Dsdonhangdagiao;
        LinearLayout lnItem;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final DanhSachDaGiaoAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new DanhSachDaGiaoAdapter.ViewHolder();
            viewHolder.lnItem = convertView.findViewById(R.id.lnItem);
            viewHolder.masanpham_dsdagiao_lsv2 = convertView.findViewById(R.id.masanpham_dsdagiao_lsv2);
            viewHolder.tensanpham_dsdagiao_lsv2 = convertView.findViewById(R.id.tensanpham_dsdagiao_lsv2);
            viewHolder.soluong_dsdagiao_lsv2 = convertView.findViewById(R.id.soluong_dsdagiao_lsv2);
            viewHolder.tenkhachhang_dsdagiao_lsv2 = convertView.findViewById(R.id.tenkhachhang_dsdagiao_lsv2);
            viewHolder.sodienthoaikhachhang_dsdagiao_lsv2 = convertView.findViewById(R.id.sodienthoaikhachhang_dsdagiao_lsv2);
            viewHolder.diachi_dsdagiao_lsv2 = convertView.findViewById(R.id.diachi_dsdagiao_lsv2);
            viewHolder.tongtien_dsdagiao_lsv2 = convertView.findViewById(R.id.tongtien_dsdagiao_lsv2);
            viewHolder.tinhtrang_dsdagiao_lsv2 = convertView.findViewById(R.id.tinhtrang_dsdagiao_lsv2);
            viewHolder.btn_xacnhan_dsdagiao_lsv = convertView.findViewById(R.id.btn_xacnhan_dsdagiao_lsv);
            viewHolder.btn_Huy_dsdagiao_lsv = convertView.findViewById(R.id.btn_Huy_dsdagiao_lsv);
            viewHolder.sv_Dsdonhangdagiao = convertView.findViewById(R.id.sv_Dsdonhangdagiao);
            viewHolder.size_dsdagiao_lsv2 = convertView.findViewById(R.id.size_dsdagiao_lsv2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DanhSachDaGiaoAdapter.ViewHolder) convertView.getTag();
        }

        final DanhSachDagiao dagiaoModel = data.get(position);
        viewHolder.masanpham_dsdagiao_lsv2.setText(dagiaoModel.getMaDonHang());
        viewHolder.tensanpham_dsdagiao_lsv2.setText(dagiaoModel.getTenSanPham());
        viewHolder.soluong_dsdagiao_lsv2.setText(dagiaoModel.getSoLuong());
        viewHolder.tenkhachhang_dsdagiao_lsv2.setText(dagiaoModel.getTenKhachHang());
        viewHolder.sodienthoaikhachhang_dsdagiao_lsv2.setText(dagiaoModel.getSoDienThoai());
        viewHolder.diachi_dsdagiao_lsv2.setText(dagiaoModel.getDiaChi());
        viewHolder.tongtien_dsdagiao_lsv2.setText(String.valueOf(dagiaoModel.getTongtien()));
        viewHolder.size_dsdagiao_lsv2.setText(dagiaoModel.getSize());
        viewHolder.tinhtrang_dsdagiao_lsv2.setText(dagiaoModel.getTinhTrang());

        if(dagiaoModel.getTinhTrang().equalsIgnoreCase("da giao")){
            viewHolder.lnItem.setBackgroundColor(context.getResources().getColor(R.color.color_item_xac_nhan));
            viewHolder.btn_xacnhan_dsdagiao_lsv.setVisibility(View.GONE);
            viewHolder.btn_Huy_dsdagiao_lsv.setVisibility(View.GONE);

        }else
        {
            viewHolder.lnItem.setBackgroundColor(context.getResources().getColor(R.color.color_item_chua_xac_nhan));
            viewHolder.btn_xacnhan_dsdagiao_lsv.setVisibility(View.VISIBLE);
            viewHolder.btn_Huy_dsdagiao_lsv.setVisibility(View.VISIBLE);
        }

        viewHolder.btn_xacnhan_dsdagiao_lsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //xoa
        viewHolder.btn_Huy_dsdagiao_lsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyendata(dagiaoModel.getId());

//                taoDialogHuyDonHang(huyGiaoHang_.getId(),huyGiaoHang_,true);
                deletePay(dagiaoModel.getId());

            }
        });
        //xacnhan
        viewHolder.btn_xacnhan_dsdagiao_lsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanDonHang(dagiaoModel.getId(),dagiaoModel);
            }
        });


        return convertView;

    }

    public int getCount() {
        return data.size();
    }

    @Override
    public DanhSachDagiao getItem(int position) {
        return data.get(position);

    }

    public long getItemId(int position) {
        return position;
    }


    public void deletePay(String idPay) {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("dagiaonv");
        data.child(idPay).removeValue();
    }
    //dialog
//    public void taoDialogHuyDonHang(final String id, final HuyGiaoHang Huygiaohang, final boolean tinhTrang) {
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(R.layout.dialog_lido_huy_donhang_layout);
//        final EditText edtLiDo = dialog.findViewById(R.id.edtLiDo);
//        final Button btn_Huy_dsdagiao_lsv = dialog.findViewById(R.id.btn_Huy_dsdagiao_lsv);
//        final Button btn_Huy = dialog.findViewById(R.id.btn_Huy);
//        dialog.show();
//
//        btn_Huy_dsdagiao_lsv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                huyGiaoHang_.setLyDo(edtLiDo.getText().toString());
//                dialog.dismiss();
//            }
//        });
//    }

    public void chuyendata(String iddata){
        DatabaseReference data = FirebaseDatabase.getInstance().getReference();
        String id = data.child("donhanghuy").push().getKey();
        HuyGiaoHang huyhang = new HuyGiaoHang(id,"",danhSachDagiao);
        data.child("donhanghuy").child(id).setValue(huyhang).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
        });
    }
    public void xacNhanDonHang(String iddata, DanhSachDagiao value) {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference();

        //Cap nhat trang thai don hang
        value.setTinhTrang("da giao");
        data.child("dagiaonv").child(iddata).setValue(value);

        //Them du lieu vao don hang da giao
        String id = data.child("donhangdagiaoquanly").push().getKey();
        data.child("donhangdagiaoquanly").child(id).setValue(value);
    }
}
