package com.example.app5eartquake.API;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

//Singleton- Se mantenga activa una unica instancia
public class EqApiClient {

    private static final EqApiClient ourInstance = new EqApiClient();
    private EqService service;

    public static EqApiClient getInstance(){
        return ourInstance;
    }

    private EqApiClient(){
    }

    public interface EqService {
        @GET("all_hour.geojson")
        Call<String> getEarthquakes();
    }

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    public EqService getService() {
        if(service == null){
            service = retrofit.create(EqService.class);
        }
        return service;
    }
}
