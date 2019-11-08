package com.example.appbangiay.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appbangiay.Manager.DetailActivity;
import com.example.appbangiay.R;
import com.example.appbangiay.data_models.ShoeData;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<ShoeViewHolder>{
    private Context mcontext;
    private List<ShoeData> myShoeList;

    public MyAdapter(Context mcontext, List<ShoeData> myShoeList) {
        this.mcontext = mcontext;
        this.myShoeList = myShoeList;
    }

    @Override
    public ShoeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item_layout,viewGroup,false);
        return new ShoeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShoeViewHolder shoeViewHolder,  int i) {

        Glide.with(mcontext)
                .load(myShoeList.get(i).getItemImage())
                .into(shoeViewHolder.imageView);

//        shoeViewHolder.imageView.setImageResource(myShoeList.get(i).getItemImage());
        shoeViewHolder.mTitle.setText(myShoeList.get(i).getTenSanPham());
        shoeViewHolder.mDescription.setText(myShoeList.get(i).getMoTa());
        shoeViewHolder.mPrice.setText(myShoeList.get(i).getGia());

        shoeViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, DetailActivity.class);
                intent.putExtra("Image",myShoeList.get(shoeViewHolder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description",myShoeList.get(shoeViewHolder.getAdapterPosition()).getMoTa());
                mcontext.startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return myShoeList.size();
    }
}
class  ShoeViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView mTitle,mDescription,mPrice;
        CardView cardView;
    public ShoeViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDescription= itemView.findViewById(R.id.tvDescription);
        mPrice = itemView.findViewById(R.id.tvPrice);
        cardView = itemView.findViewById(R.id.myCardView);
    }
}