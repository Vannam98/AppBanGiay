package com.example.appbangiay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.DonHangHuyQuanLy;


import java.util.List;

public class DonHangHuyAdapter extends BaseAdapter {

    Context contextHuy;
    int LayoutHuy;
    List<DonHangHuyQuanLy> arrayDonHangHuy;

    public DonHangHuyAdapter(Context contextHuy, int layoutHuy, List<DonHangHuyQuanLy> arrayDonHangHuy) {
        this.contextHuy = contextHuy;
        LayoutHuy = layoutHuy;
        this.arrayDonHangHuy = arrayDonHangHuy;
    }


    @Override
    public int getCount() {
        return arrayDonHangHuy.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayDonHangHuy.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class  ViewHolder{
        TextView txt_Madonhang;
        TextView txt_Tensanpham;
        TextView txt_Soluong;
        TextView txt_Size;
        TextView txt_Tenkhachhang;
        TextView txt_SodienthoaiKH;
        TextView txt_DiachiKH;
        TextView txt_Tongtien;
        TextView txt_Tinhtrang;
        TextView txt_Lydohuy;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) contextHuy.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowview = convertView;

        ViewHolder holder = new ViewHolder();

        if (rowview == null)
        {
            rowview = inflater.inflate(LayoutHuy,null);
            holder.txt_Madonhang = (TextView)rowview.findViewById(R.id.txt_Madonhang);
            holder.txt_Tensanpham = (TextView)rowview.findViewById(R.id.txt_Tensanpham);
            holder.txt_Soluong= (TextView)rowview.findViewById(R.id.txt_Soluong);
            holder.txt_Size = (TextView)rowview.findViewById(R.id.txt_Size);
            holder.txt_Tenkhachhang = (TextView)rowview.findViewById(R.id.txt_Tenkhachhang);
            holder.txt_SodienthoaiKH = (TextView)rowview.findViewById(R.id.txt_SodienthoaiKH);
            holder.txt_DiachiKH= (TextView)rowview.findViewById(R.id.txt_DiachiKH);
            holder.txt_Tongtien = (TextView)rowview.findViewById(R.id.txt_Tongtien);
            holder.txt_Tinhtrang = (TextView)rowview.findViewById(R.id.txt_Tinhtrang);
            holder.txt_Lydohuy = (TextView)rowview.findViewById(R.id.txt_Lydohuy);


            rowview.setTag(holder);
        }
        else {
            holder = (ViewHolder)rowview.getTag();
        }

        holder.txt_Madonhang.setText("Mã đơn hàng:" + arrayDonHangHuy.get(position).maDH);
        holder.txt_Tensanpham.setText("Tên sản phẩm:" + arrayDonHangHuy.get(position).tenSP);
        holder.txt_Soluong.setText("Số lượng:" + arrayDonHangHuy.get(position).soLuong);
        holder.txt_Size.setText("Size:" + arrayDonHangHuy.get(position).size);
        holder.txt_Tenkhachhang.setText("Tên khách hàng:" + arrayDonHangHuy.get(position).tenKH);
        holder.txt_SodienthoaiKH.setText("Số điện thoại KH:" + arrayDonHangHuy.get(position).soDTKH);
        holder.txt_DiachiKH.setText("Địa chỉ khách hàng:" + arrayDonHangHuy.get(position).diaChiKH);
        holder.txt_Tongtien.setText("Tổng tiền:" + arrayDonHangHuy.get(position).tongTien);
        holder.txt_Tinhtrang.setText("Tình trạng:" + arrayDonHangHuy.get(position).tinhTrang);
        holder.txt_Lydohuy.setText("Lý do hủy:" + arrayDonHangHuy.get(position).lyDoHuy);

        return rowview;

    }
}

