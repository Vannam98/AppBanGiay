package com.example.appbangiay.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import com.example.appbangiay.data_models.DonHangXacNhan;
import com.example.appbangiay.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdapterXacNhan extends ArrayAdapter<DonHangXacNhan> {
    private Activity context;
    private ArrayList<DonHangXacNhan> donHangXacNhan;
    private int layoutID;


    //DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    public AdapterXacNhan(Activity context, int resource, @NonNull ArrayList<DonHangXacNhan> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.donHangXacNhan = objects;
    }

    public class ViewHolder
    {
        TextView txt_ID, txt_maDH, txt_tenSP, txt_soLuong, txt_size, txt_tenKH, txt_soDT, txt_diaChi, txt_tongTien;
        Button btn_XN, btn_Huy;
        LinearLayout lnBackgrounDHXN;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.txt_ID = convertView.findViewById(R.id.txt_id);
            viewHolder.lnBackgrounDHXN = convertView.findViewById(R.id.lnBackgrounDHXN);
            viewHolder.txt_maDH = convertView.findViewById(R.id.txt_MDH);
            viewHolder.txt_tenSP = convertView.findViewById(R.id.txt_TSP);
            viewHolder.txt_soLuong = convertView.findViewById(R.id.txt_SL);
            viewHolder.txt_size = convertView.findViewById(R.id.txt_S);
            viewHolder.txt_tenKH = convertView.findViewById(R.id.txt_TKH);
            viewHolder.txt_soDT = convertView.findViewById(R.id.txt_SDTKH);
            viewHolder.txt_diaChi = convertView.findViewById(R.id.txt_DCKH);
            viewHolder.txt_tongTien = convertView.findViewById(R.id.txt_TT);
            viewHolder.btn_XN = convertView.findViewById(R.id.btn_XN);
            viewHolder.btn_Huy = convertView.findViewById(R.id.btn_huy);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final DonHangXacNhan dhxn = donHangXacNhan.get(position);

        if(dhxn.isXacNhan()){
            viewHolder.lnBackgrounDHXN.setBackgroundColor(context.getResources().getColor(R.color.bg_item_dhxn));
            viewHolder.btn_XN.setVisibility(View.GONE); //aN nut xac nhan
        }
//        if(dhxn.isHuy())
//        {
//            viewHolder.lnBackgrounDHXN.setBackgroundColor(context.getResources().getColor(R.color.bg_item_dhxn1));
//            viewHolder.btn_Huy.setVisibility(View.GONE);
//        }

        viewHolder.txt_ID.setText(dhxn.getId());
        viewHolder.txt_maDH.setText(dhxn.getMaDonHang());
        viewHolder.txt_tenSP.setText(dhxn.getTenSanPham());
        viewHolder.txt_soLuong.setText(dhxn.getSoLuong());
        viewHolder.txt_size.setText(dhxn.getSize());
        viewHolder.txt_tenKH.setText(dhxn.getTenKhachHang());
        viewHolder.txt_soDT.setText(dhxn.getSoDT());
        viewHolder.txt_diaChi.setText(dhxn.getDiaChi());
        viewHolder.txt_tongTien.setText(String.valueOf(dhxn.getTongTien()));
        viewHolder.btn_XN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capNhatTinhTrang(dhxn.getId(),dhxn);
               notifyDataSetChanged();
            }
        });

        viewHolder.btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(dhxn.getId());
                notifyDataSetChanged();
            }
        });

        return  convertView;
    }
    public int getCount() {
        return donHangXacNhan.size();
    }

    @Override
    public DonHangXacNhan getItem(int position) {
        return donHangXacNhan.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public void delete(String id)
    {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("DonHangXacNhan").child(id);
        data.removeValue();
    }

    public void capNhatTinhTrang(String id, DonHangXacNhan donHangXacNhan)
    {
        donHangXacNhan.setXacNhan(true);
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("DonHangXacNhan").child(id);
    }

    public interface CallBackDHXN{
        void XacNhanThanhCong();
        void XacNhanThatBai();
    }
}
