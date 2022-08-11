package com.example.app5eartquake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.app5eartquake.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.eqRecycler.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Earthquake> eqlist= new ArrayList<>();
        eqlist.add(new Earthquake("001", "Carchi-Tulcan",5, 12082022, 100.5,198.3  ));
        eqlist.add(new Earthquake("002", "Guayas-Guayaquil",6, 12082022, 100.5,154.8  ));
        eqlist.add(new Earthquake("003", "Chimborazo- Alusi",3, 12082022, 100.5,154.8  ));
        eqlist.add(new Earthquake("004", "Azuay-Cuenca",1, 12082022, 100.5,154.8  ));
        eqlist.add(new Earthquake("005", "Azuay-Paute",4.3, 12082022, 100.5,154.8  ));


        //Carga de datos
        EqAdapter adapter = new EqAdapter();

        adapter.setOnItemClickListener(earthquake -> abrir(earthquake.getId(),earthquake.getPlace(),earthquake.getMagnitude(), earthquake.getTime(), earthquake.getLatidude(),earthquake.getLongitude()));



        binding.eqRecycler.setAdapter(adapter);
        adapter.submitList(eqlist);

        if(eqlist.isEmpty()){
            binding.emptyView.setVisibility(View.VISIBLE);
        }else{
            binding.emptyView.setVisibility(View.GONE);

        }

    }
    public void abrir(String id, String place, double magnitude, long time, double lat, double longi){
        Toast.makeText(MainActivity.this,place , Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, Monitor.class);
        intent.putExtra("id", id);
        intent.putExtra("place", place);
        intent.putExtra("magn", magnitude);
        intent.putExtra("time", time);
        intent.putExtra("lat", lat);
        intent.putExtra("longitud", longi);
        startActivity(intent);
    }
}