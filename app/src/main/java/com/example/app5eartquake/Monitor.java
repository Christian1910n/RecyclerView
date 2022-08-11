package com.example.app5eartquake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.app5eartquake.databinding.ActivityMonitorBinding;

public class Monitor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMonitorBinding binding= ActivityMonitorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
         String id= extras.getString("id");

         binding.txtLatitude.setText(extras.getDouble("lat")+" ºN");
         binding.txtFechahora.setText(extras.getLong("time")+"");
         binding.txtLongitud.setText(extras.getDouble("longitud")+" ºW");
         binding.txtMagnitud.setText(extras.getDouble("magn")+"");
         binding.txtUbicacion.setText(extras.getString("place"));


    }
}