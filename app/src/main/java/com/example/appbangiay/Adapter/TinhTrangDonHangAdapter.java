package com.example.appbangiay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.TinhTrangDonHang;

import java.util.List;

public class TinhTrangDonHangAdapter extends BaseAdapter {

    Context context;
    int myLayout;
    List<TinhTrangDonHang> arraySanPham;


    public TinhTrangDonHangAdapter(Context context, int myLayout, List<TinhTrangDonHang> arraySanPham) {
        this.context = context;
        this.myLayout = myLayout;
        this.arraySanPham = arraySanPham;
    }

    @Override
    public int getCount() {
        return arraySanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return arraySanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txt_Madonhang;
        TextView txt_Tendonhang;
        TextView txt_Thongtindonhang;
        TextView txt_Tinhtrangvanchuyen;
        TextView txt_Tongtien;
        TextView txt_Ngay;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowview = convertView;
        ViewHolder holder = new ViewHolder();

        if (rowview == null)
        {
            rowview = inflater.inflate(myLayout,null);
            holder.txt_Madonhang = (TextView)rowview.findViewById(R.id.txt_Madonhang);
            holder.txt_Tendonhang = (TextView)rowview.findViewById(R.id.txt_Tendonhang);
            holder.txt_Thongtindonhang = (TextView)rowview.findViewById(R.id.txt_Thongtindonhang);
            holder.txt_Tinhtrangvanchuyen = (TextView)rowview.findViewById(R.id.txt_Tinhtrangvanchuyen);
            holder.txt_Tongtien = (TextView)rowview.findViewById(R.id.txt_Tongtien);
            holder.txt_Ngay = (TextView)rowview.findViewById(R.id.txt_Ngay);

            rowview.setTag(holder);
        }
        else {
            holder = (ViewHolder)rowview.getTag();
        }

        holder.txt_Madonhang.setText("Mã đơn hàng: " + arraySanPham.get(position).maDH);
        holder.txt_Tendonhang.setText("Tên đơn hàng: " + arraySanPham.get(position).tenDH);
        holder.txt_Thongtindonhang.setText("Thông tin đơn hàng: " + arraySanPham.get(position).thongTinDH);
        holder.txt_Tinhtrangvanchuyen.setText("Tình trạng: " + arraySanPham.get(position).tinhTrang);
        holder.txt_Tongtien.setText("Tổng tiền: " + arraySanPham.get(position).tongTien);
        holder.txt_Ngay.setText("Ngày: " + arraySanPham.get(position).ngay);



        return rowview;
    }
}

