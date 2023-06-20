package com.example.tramites

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface apiService {
    @GET
    suspend fun getValidar(@Url url:String) : Response<respondeTramitr>
}