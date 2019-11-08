package com.example.appbangiay.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.ThanhToan;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ThanhToanAdapter extends ArrayAdapter<ThanhToan> {
    private Activity context;
    private int layoutID;
    private ArrayList<ThanhToan> data;
    Tinhtong tinhtong;
    public ThanhToanAdapter(Activity context, int resource, ArrayList<ThanhToan> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.data = objects;
        tinhtong = (Tinhtong) context;

    }

    public class ViewHolder {

        Spinner spinner_thanhtoan;
        EditText edt_soluong_lsv;
        TextView txt_tensanpham_lsv, txt_gia_lsv, txt_masp_lsv,txt_size_lsv2;
        Button button_tang_lsv, button_xuong_lsv, btn_xoa_lsv;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_size_lsv2 = convertView.findViewById(R.id.txt_size_lsv2);
            viewHolder.edt_soluong_lsv = convertView.findViewById(R.id.edt_soluong_lsv);
            viewHolder.button_tang_lsv = convertView.findViewById(R.id.button_tang_lsv);
            viewHolder.button_xuong_lsv = convertView.findViewById(R.id.button_xuong_lsv);
            viewHolder.btn_xoa_lsv = convertView.findViewById(R.id.btn_xoa_lsv);
            viewHolder.txt_tensanpham_lsv = convertView.findViewById(R.id.txt_tensanpham_lsv);
            viewHolder.txt_gia_lsv = convertView.findViewById(R.id.txt_gia_lsv);
            viewHolder.txt_masp_lsv = convertView.findViewById(R.id.txt_masp_lsv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ThanhToan student = data.get(position);
        viewHolder.txt_masp_lsv.setText(student.getMaSanPham());
        viewHolder.txt_tensanpham_lsv.setText(student.getTenSanPham());
        viewHolder.txt_gia_lsv.setText(String.valueOf(student.getGia()));
        viewHolder.edt_soluong_lsv.setText(student.getSoLuong());
        viewHolder.txt_size_lsv2.setText(student.getSize());


        /***Xử lý tăng giảm tại dây***/
        viewHolder.button_xuong_lsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Integer.parseInt(student.getSoLuong()) <= 0) {
                    student.setSoLuong("0");
                } else {
                    student.setSoLuong((Integer.parseInt(student.getSoLuong()) - 1)+"");
                }
                viewHolder.edt_soluong_lsv.setText(student.getSoLuong());
            }
        });
        viewHolder.button_tang_lsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setSoLuong((Integer.parseInt(student.getSoLuong()) + 1)+"");
                viewHolder.edt_soluong_lsv.setText(student.getSoLuong());
               double tong = student.getGia() * (Integer.parseInt(student.getSoLuong()));
//
                tinhtong.Tong(tong);
            }
        });

        viewHolder.btn_xoa_lsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                remove(student);
                deletePay(student.getId());

            }
        });

        return convertView;
    }


    public int getCount() {
        return data.size();
    }

    @Override
    public ThanhToan getItem(int position) {
        return data.get(position);

    }

    public long getItemId(int position) {
        return position;
    }


    public void deletePay(String idPay){
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("DonHangDatMua");
        data.child(idPay).removeValue();
    }

    public interface Tinhtong {
        void Tong(double toTal);
    }

}
