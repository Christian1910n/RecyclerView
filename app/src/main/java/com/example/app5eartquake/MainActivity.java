package com.example.app5eartquake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.app5eartquake.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainViewModel viewModel= new ViewModelProvider(this).get(MainViewModel.class);

        binding.eqRecycler.setLayoutManager(new LinearLayoutManager(this));


        //Carga de datos
        EqAdapter adapter = new EqAdapter();

        adapter.setOnItemClickListener(earthquake -> abrir(earthquake.getId(),earthquake.getPlace(),earthquake.getMagnitude(), earthquake.getTime(), earthquake.getLatidude(),earthquake.getLongitude()));





        binding.eqRecycler.setAdapter(adapter);




        viewModel.getEqList().observe(this,eqList ->{
            adapter.submitList(eqList);

            if(eqList.isEmpty()){
                binding.emptyView.setVisibility(View.VISIBLE);
            }else{
                binding.emptyView.setVisibility(View.GONE);

            }
        });
        viewModel.getEarthquakes();

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