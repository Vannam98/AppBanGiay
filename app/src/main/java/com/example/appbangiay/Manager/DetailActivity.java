package com.example.appbangiay.Manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appbangiay.R;

public class DetailActivity extends AppCompatActivity {

    TextView shoeDescription;
    ImageView shoeImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        shoeImage =(ImageView)findViewById(R.id.ivImage2);
        shoeDescription = (TextView)findViewById(R.id.txtDescripTion);

        Bundle mBundle =getIntent().getExtras();
        if (mBundle!= null)
        {
            shoeDescription.setText(mBundle.getString("Description"));
//            shoeImage.setImageResource(mBundle.getInt("Image"));

            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(shoeImage);

        }
    }
}
