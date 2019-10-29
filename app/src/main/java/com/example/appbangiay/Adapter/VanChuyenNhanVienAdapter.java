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
import com.example.appbangiay.data_models.HuyGiaoHang;
import com.example.appbangiay.data_models.HuyVanChuyen;
import com.example.appbangiay.data_models.VanChuyenNhanVien;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class VanChuyenNhanVienAdapter extends ArrayAdapter<VanChuyenNhanVien> {
    private Activity context;
    private int layoutID;
    private ArrayList<VanChuyenNhanVien> data;
    private ArrayList<VanChuyenNhanVien> vanChuyenNhanViens;


    public VanChuyenNhanVienAdapter(Activity context, int resource, ArrayList<VanChuyenNhanVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.data = objects;

    }

    public class ViewHolder {
        LinearLayout lnItem_vanchuyen;
        TextView txt_madonhang_vanchuyennv2, txt_tensanpham_vanchuyennv2, txt_soluong_vanchuyennv2,txt_size_vanchuyennv2,txt_tinhtrang_vanchuyennv2,txt_tenkhachhang_vanchuyennv2,txt_sodienthoai_vanchuyennv2,txt_diachikhachhang_vanchuyennv2,txt_tongtien_vanchuyen;
        Button btn_xacnhan_dsvanchuyen, btn_Huy_dsvanchuyen_lsv;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_madonhang_vanchuyennv2 = convertView.findViewById(R.id.txt_madonhang_vanchuyennv2);
            viewHolder.txt_tensanpham_vanchuyennv2 = convertView.findViewById(R.id.txt_tensanpham_vanchuyennv2);
            viewHolder.txt_soluong_vanchuyennv2 = convertView.findViewById(R.id.txt_soluong_vanchuyennv2);
            viewHolder.txt_size_vanchuyennv2 = convertView.findViewById(R.id.txt_size_vanchuyennv2);
            viewHolder.txt_tinhtrang_vanchuyennv2 = convertView.findViewById(R.id.txt_tinhtrang_vanchuyennv2);
            viewHolder.txt_tenkhachhang_vanchuyennv2 = convertView.findViewById(R.id.txt_tenkhachhang_vanchuyennv2);
            viewHolder.txt_tongtien_vanchuyen = convertView.findViewById(R.id.txt_tongtien_vanchuyen);
            viewHolder.txt_sodienthoai_vanchuyennv2 = convertView.findViewById(R.id.txt_sodienthoai_vanchuyennv2);
            viewHolder.txt_diachikhachhang_vanchuyennv2 = convertView.findViewById(R.id.txt_diachikhachhang_vanchuyennv2);
            viewHolder.btn_xacnhan_dsvanchuyen = convertView.findViewById(R.id.btn_xacnhan_dsvanchuyen);
            viewHolder.btn_Huy_dsvanchuyen_lsv = convertView.findViewById(R.id.btn_Huy_dsvanchuyen_lsv);
            viewHolder.lnItem_vanchuyen = convertView.findViewById(R.id.lnItem_vanchuyen);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final VanChuyenNhanVien vanchuyen = data.get(position);
        viewHolder.txt_madonhang_vanchuyennv2.setText(vanchuyen.getMaDonHang());
        viewHolder.txt_tensanpham_vanchuyennv2.setText(vanchuyen.getTenSanPham());
        viewHolder.txt_soluong_vanchuyennv2.setText(vanchuyen.getSoLuong());
        viewHolder.txt_size_vanchuyennv2.setText(vanchuyen.getSize());
         viewHolder.txt_tinhtrang_vanchuyennv2.setText(vanchuyen.getTinhTrang());
        viewHolder.txt_tenkhachhang_vanchuyennv2.setText(vanchuyen.getTenKhachHang());
        viewHolder.txt_sodienthoai_vanchuyennv2.setText(vanchuyen.getSoDienThoai());
        viewHolder.txt_diachikhachhang_vanchuyennv2.setText(vanchuyen.getDiaChi());
        viewHolder.txt_tongtien_vanchuyen.setText(String.valueOf(vanchuyen.getTongtien()));

        if(vanchuyen.getTinhTrang().equalsIgnoreCase("xac nhan")){
            viewHolder.lnItem_vanchuyen.setBackgroundColor(context.getResources().getColor(R.color.color_item_xac_nhan));
            viewHolder.btn_xacnhan_dsvanchuyen.setVisibility(View.GONE);
            viewHolder.btn_Huy_dsvanchuyen_lsv.setVisibility(View.GONE);

        }
        else
        {
            viewHolder.lnItem_vanchuyen.setBackgroundColor(context.getResources().getColor(R.color.color_item_chua_xac_nhan));
            viewHolder.btn_xacnhan_dsvanchuyen.setVisibility(View.VISIBLE);
            viewHolder.btn_Huy_dsvanchuyen_lsv.setVisibility(View.VISIBLE);
        }

        viewHolder.btn_xacnhan_dsvanchuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //xoa
        viewHolder.btn_Huy_dsvanchuyen_lsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyendata(vanchuyen.getId());

//                taoDialogHuyDonHang(huyGiaoHang_.getId(),huyGiaoHang_,true);
                deletePay(vanchuyen.getId());

            }
        });
        //xacnhan
        viewHolder.btn_xacnhan_dsvanchuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanDonHang(vanchuyen.getId(),vanchuyen);
            }
        });

        return convertView;
    }


    public int getCount() {
        return data.size();
    }

    @Override
    public VanChuyenNhanVien getItem(int position) {
        return data.get(position);

    }

    public long getItemId(int position) {
        return position;
    }


    public void deletePay(String idPay){
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("Vanchuyennhanvien");
        data.child(idPay).removeValue();
    }
    public void chuyendata(String iddata){
        DatabaseReference data = FirebaseDatabase.getInstance().getReference();
        String id = data.child("donhanghuyVC").push().getKey();
        HuyVanChuyen huyvanchuyen = new HuyVanChuyen(id,vanChuyenNhanViens);
        data.child("donhanghuyVC").child(id).setValue(huyvanchuyen).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
        });
    }
    public void xacNhanDonHang(String iddata, VanChuyenNhanVien value) {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference();

        //Cap nhat trang thai don hang
        value.setTinhTrang("Xac Nhan");
        data.child("Vanchuyennhanvien").child(iddata).setValue(value);

        //Them du lieu vao don hang da giao
        String id = data.child("XacNhanVanchuyen").push().getKey();
        data.child("XacNhanVanchuyen").child(id).setValue(value);
    }
}
