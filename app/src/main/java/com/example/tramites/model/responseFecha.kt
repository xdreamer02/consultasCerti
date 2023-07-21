package com.example.tramites.model

import com.google.gson.annotations.SerializedName

data class responseFecha(

    // @SerializedName("NRO DE EXPEDIENTE") val numexpp: String? = null,
     @SerializedName("ALEATORIO") val  aleatorio: String,
     @SerializedName("ALEATORIO1") val aleatorio1: String,
     @SerializedName("FECHA") val fecha: String,
     @SerializedName("NIVEL DE CALIDAD EN PRESTACION DE SERVICO") val nivelCalidad: String,
     @SerializedName("TOTAL APOSTILLAS ELABORADAS") val totalElaboradas: String,
     @SerializedName("TOTAL APOSTILLAS SIN CORRECIONES") val totalSCorreciones: String
)

data class responseTest(

     // @SerializedName("NRO DE EXPEDIENTE") val numexpp: String? = null,
     @SerializedName("ALEATORIO") val  aleatorio: String,
     @SerializedName("ALEATORIO1") val aleatorio1: String,
     @SerializedName("FECHA") val fecha: String,
     @SerializedName("NUMEROS DE USUARIOS SATISFECHOS") val numeroSatisfecho: String,
     @SerializedName("NUMERO DE USUARIOS EVALUADOS") val numeroEvaluado: String,
     @SerializedName("NIVEL DE SATISFACCION DE LOS USUARIOS") val nivelSatisfaccion: String
)