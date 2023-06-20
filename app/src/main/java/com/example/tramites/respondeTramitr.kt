package com.example.tramites

import com.google.gson.annotations.SerializedName

data class respondeTramitr(

    @SerializedName("NRO DE EXPEDIENTE") val expediente: String? = null,
    @SerializedName("RUC") val ruc: String? = null,
    @SerializedName("RAZON SOCIAL") val razon_social: String? = null,
    @SerializedName("FECHA DE NACIMIENTO") val fecha_nac: String? = null,
    @SerializedName("DIRECCIÓM") val dirrec: String? = null,
    @SerializedName("DISTRITO") val distrito: String? = null,
    @SerializedName("TELÉFONO") val telf: String? = null,
    @SerializedName("CORREO ELECTRÓNICO") val correo: String? = null,
    @SerializedName("ESTADO DE EXPEDIENTE") val estadoexp: String? = null,
    @SerializedName("ESTADO DE EXPEDIENTE2") val estadoexp2: String? = null,
    @SerializedName("RIESGO") val riesgo: String? = null,
    @SerializedName("FECHA DE INSPECCIÓN") val fecInspecion: String? = null,
    @SerializedName("INSPECTOR ASIGNADO") val inspectorAsig: String? = null,
    @SerializedName("DNI DE INSPECTOR") val dniInspector: String? = null,
    @SerializedName("ESTADO DE INSPECCIÓN") val estadoInspecion: String? = null,

)