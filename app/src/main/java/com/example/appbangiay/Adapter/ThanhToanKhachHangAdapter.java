package com.example.appbangiay.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.ThanhToanKhachHang;

import java.util.ArrayList;

public class ThanhToanKhachHangAdapter extends ArrayAdapter<ThanhToanKhachHang> {

    private Activity context;
        private int layoutID;
        private ArrayList<ThanhToanKhachHang> data;
        public ThanhToanKhachHangAdapter(Activity context, int resource, ArrayList<ThanhToanKhachHang> objects)
        {
            super(context,resource,objects);
            this.context = context;
            this.layoutID = resource;
            this.data = objects;

        }

        public class ViewHolder {


        EditText edt_tenkhachhang_thanhtoan,edt_sodienthoai_thanhtoan,edt_diachigiao_thanhtoan,edt_phivanchuyen_thanhtoan;
        TextView txt_tongtien_thanhtoan2;
        Button btnchinhsua;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ThanhToanKhachHangAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new ThanhToanKhachHangAdapter.ViewHolder();
            viewHolder.edt_tenkhachhang_thanhtoan = convertView.findViewById(R.id.edt_tenkhachhang_thanhtoan);
            viewHolder.edt_sodienthoai_thanhtoan = convertView.findViewById(R.id.edt_sodienthoai_thanhtoan);
            viewHolder.edt_diachigiao_thanhtoan = convertView.findViewById(R.id.edt_diachigiao_thanhtoan);
            viewHolder.txt_tongtien_thanhtoan2 = convertView.findViewById(R.id.txt_tongtien_thanhtoan2);
            viewHolder.btnchinhsua = convertView.findViewById(R.id.btnchinhsua);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ThanhToanKhachHangAdapter.ViewHolder) convertView.getTag();
        }
        final ThanhToanKhachHang thanhToan2Model = data.get(position);
        viewHolder.edt_tenkhachhang_thanhtoan.setText(thanhToan2Model.getTenkhachhang());
        viewHolder.edt_sodienthoai_thanhtoan.setText(thanhToan2Model.getSodienthoai());
        viewHolder.edt_diachigiao_thanhtoan.setText(thanhToan2Model.getDiachi());
        viewHolder.txt_tongtien_thanhtoan2.setText(String.valueOf(thanhToan2Model.getTongtien()));



        viewHolder.btnchinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return convertView;
    }
    public int getCount() {
        return data.size();
    }

    @Override
    public ThanhToanKhachHang getItem(int position) {
        return data.get(position);

    }

    public long getItemId(int position) {
        return position;
    }
}
