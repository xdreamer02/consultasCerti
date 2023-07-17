package com.example.tramites

import com.example.tramites.model.BodyDTO
import com.example.tramites.model.DatoDTO
import com.example.tramites.model.resDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface apiService {
    //validar certificado existente
    @GET
    suspend fun getValidar(@Url url:String) : Response<respondeTramitr>

    //getbyID
    @GET
    suspend fun getbyId(@Url link:String):Response<resDTO>

    //agregar nuevo certificado
    @POST("exec")
    fun addCertificado(@Body msj:BodyDTO) : Call<DatoDTO>

    //update certificado
    @POST("exec")
    fun uptCertificado(@Body msj: resDTO)  : Call<DatoDTO>
}