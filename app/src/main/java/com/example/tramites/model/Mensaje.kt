package com.example.tramites.model

import com.google.gson.annotations.SerializedName

data class BodyDTO(
    @SerializedName("numexp")  var numexp:String,
    @SerializedName("ruc")  var ruc :String,
    @SerializedName("razsocial")  var razsocial :String,
    @SerializedName("fecNacimiento")  var fecNacimiento :String,
    @SerializedName("direccion")  var direccion :String,
    @SerializedName("distrito")  var distrito :String,
    @SerializedName("telefono")  var telefono :String,
    @SerializedName("email")  var email :String,
    @SerializedName("riesgo")  var riesgo :String

)

data class DatoDTO(
    //@SerializedName("id")val id:Int,
    @SerializedName("mensaje")  var mensaje: String,
)