package com.example.examenfinal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LoteriaService {
    @GET("jorgeduenaslerin/desarrollo-web/loto")
    Call<List<Loteria>> listarLoteria();
}
