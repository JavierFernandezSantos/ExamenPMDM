package com.example.examenfinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoteriaViewModel extends ViewModel {
    private MutableLiveData<List<Loteria>> datos;

    public LiveData<List<Loteria>> obtenerDatos() {
        if (datos == null) {
            datos = new MutableLiveData<List<Loteria>>();
            datos.setValue(new ArrayList<Loteria>());
            loadData();
        }
        return datos;
    }

    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoteriaService service = retrofit.create(LoteriaService.class);

        Call<List<Loteria>> peticion = service.listarLoteria();

        peticion.enqueue(new Callback<List<Loteria>>() {
            @Override
            public void onResponse(Call<List<Loteria>> call, Response<List<Loteria>> response) {
                datos.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Loteria>> call, Throwable t) {
                //Toast.makeText(getApplicationContext(),"Error de red", Toast.LENGTH_LONG).show();
            }
        });
    }
}
