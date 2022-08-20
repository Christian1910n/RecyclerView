package com.example.app5eartquake;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app5eartquake.API.EqApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Earthquake>> eqList= new MutableLiveData<>();
    public LiveData<List<Earthquake>> getEqList(){
        return eqList;
    }

    public void getEarthquakes(){
        /*ArrayList<Earthquake> eqList= new ArrayList<>();
        eqList.add(new Earthquake("001", "Carchi-Tulcan",5, 12082022, 100.5,198.3  ));
        eqList.add(new Earthquake("002", "Guayas-Guayaquil",6, 12082022, 100.5,154.8  ));
        eqList.add(new Earthquake("003", "Chimborazo- Alusi",3, 12082022, 100.5,154.8  ));
        eqList.add(new Earthquake("004", "Azuay-Cuenca",1, 12082022, 100.5,154.8  ));
        eqList.add(new Earthquake("005", "Azuay-Paute",4.3, 12082022, 100.5,154.8  ));

        this.eqList.setValue(eqList);*/

        EqApiClient.EqService service = EqApiClient.getInstance().getService();
        service.getEarthquakes().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Log.d("MainViewModel", response.body());
                List<Earthquake> earthquakesList=  parseEarthquake(response.body());
                eqList.setValue(earthquakesList);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("MainViewModel", t.getMessage());
            }
        });


    }


    private List<Earthquake> parseEarthquake(String responseString){
        DecimalFormat df = new DecimalFormat("#.00");
        ArrayList<Earthquake> eqList = new ArrayList<>();
        try {
            JSONObject jsonResponse = new JSONObject(responseString);
            JSONArray featuresJSONArray = jsonResponse.getJSONArray("features");

            for (int i=0; i<featuresJSONArray.length(); i++){
                JSONObject jsonFeatures = featuresJSONArray.getJSONObject(i);
                String id= jsonFeatures.getString("id");

                JSONObject jsonProperties= jsonFeatures.getJSONObject("properties");
                double magnitude = jsonProperties.getDouble("mag");
                String place = jsonProperties.getString("place");
                long time= jsonProperties.getLong("time");

                JSONObject jsonGeometry = jsonFeatures.getJSONObject("geometry");
                JSONArray coordinatesJSONArray = jsonGeometry.getJSONArray("coordinates");
                double longitude = coordinatesJSONArray.getDouble(0);
                double latitude = coordinatesJSONArray.getDouble(1);

                String x= df.format(magnitude);
                Earthquake earthquake=new Earthquake(id,place,Double.parseDouble(x),time,latitude,longitude);
                eqList.add(earthquake);

            }
            double magnitude = 5.1412;
            String x= df.format(magnitude);
            eqList.add(new Earthquake("001", "Carchi-Tulcan",Double.parseDouble(x), 12082022, 100.5,198.3  ));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eqList;
    }


}
