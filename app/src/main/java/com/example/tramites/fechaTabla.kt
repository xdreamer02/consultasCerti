package com.example.tramites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.tramites.databinding.ActivityFechaTablaBinding
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class fechaTabla : AppCompatActivity() {
    private lateinit var bindings : ActivityFechaTablaBinding
    private lateinit var tools:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityFechaTablaBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        tools = bindings.topAppBar250
        tools.setNavigationOnClickListener { finish() }

        bindings.tiFechaNac.setOnClickListener {
            val datePick = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccion la Fecha")
                .build()
            datePick.show(supportFragmentManager, "datePicker")

            datePick.addOnPositiveButtonClickListener {
                val simpleFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                bindings.tiFechaNac.setText(simpleFormat.format(Date(it).time))
            }
            //bindings.tiFechaNac.setText(formato)
        }

        bindings.textInputLayout110.setEndIconOnClickListener {
            Toast.makeText(this,"Buscando...", Toast.LENGTH_SHORT).show()
            searchFechas(bindings.tiFechaNac.text.toString())
        }
    }

    private fun searchFechas(fecha:String){
        val url ="exec?spreadsheetId=1VZpNYJHxCU0CZO_PE0NS7r9AnSrmfGWSpbLvQ92D1Ic&sheet=POST-TEST-INDICADOR1&fecha=${fecha}"
        val estado = checkInter()
        if(estado.checkForInternet(this)){
            CoroutineScope(Dispatchers.IO).launch {
                val data = getRetrofit().create(apiService::class.java).getbyFecha(url)
                val obt = data.body()
                runOnUiThread {
                    if(data != null){
                        val validar = obt?.totalSCorreciones ?: "0"
                        bindings.textView800.text = obt?.totalSCorreciones ?: "0"
                        bindings.textView801.text = obt?.aleatorio ?: "0"
                        bindings.textView802.text = obt?.totalElaboradas ?: "0"
                        bindings.textView803.text = obt?.aleatorio1 ?: "0"
                        bindings.textView804.text = obt?.nivelCalidad ?: "0"

                        if(validar == "0"){
                            showMsj("No hay datos para la fecha seleccionada")
                        }
                    }else{
                        showMsj("No hay datos para la fecha seleccionada")
                    }
                }
            }
        }
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://script.google.com/macros/s/AKfycbz7Gia2O8l8begnvr024OJS_OJsi-aOzgQQUMikenDXfwUYSq9M1LPgWvywe-5VVswf/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun showMsj(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}