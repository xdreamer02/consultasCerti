package com.example.tramites

import com.example.tramites.model.BodyDTO
import com.example.tramites.model.DatoDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface apiService {
    @GET
    suspend fun getValidar(@Url url:String) : Response<respondeTramitr>

    @POST("exec")
    fun addCertificado(@Body msj:BodyDTO) : Call<DatoDTO>
}