package com.example.tramites.model

import com.google.gson.annotations.SerializedName

data class resDTO(
    @SerializedName("NRO DE EXPEDIENTE") val numexpp: String? = null,
    @SerializedName("RUC") val rucc: String? = null,
    @SerializedName("RAZON SOCIAL") val razsociall: String? = null,
    @SerializedName("FECHA DE NACIMIENTO") val fecNacc: String? = null,
    @SerializedName("DIRECCIÓN") val direccionn: String? = null,
    @SerializedName("DISTRITO") val distritoo: String? = null,
    @SerializedName("TELÉFONO") val telefonoo: String? = null,
    @SerializedName("CORREO ELECTRÓNICO") val emaill: String? = null,
    @SerializedName("RIESGO") val riesgoo: String? = null


)