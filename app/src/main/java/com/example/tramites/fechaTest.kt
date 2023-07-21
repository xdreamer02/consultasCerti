package com.example.tramites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.tramites.databinding.ActivityFechaTestBinding
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class fechaTest : AppCompatActivity() {
    private lateinit var binding :ActivityFechaTestBinding
    private lateinit var tools : Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFechaTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tools = binding.topAppBar257
        tools.setNavigationOnClickListener { finish() }

        binding.tiFechaNac2.setOnClickListener {
            val datePick = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccion la Fecha")
                .build()
            datePick.show(supportFragmentManager, "datePicker")

            datePick.addOnPositiveButtonClickListener {
                val simpleFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.tiFechaNac2.setText(simpleFormat.format(Date(it).time))
            }
            //binding.tiFechaNac.setText(formato)
        }

        binding.textInputLayout113.setEndIconOnClickListener {
            Toast.makeText(this,"Buscando...", Toast.LENGTH_SHORT).show()
            buscarFechas(binding.tiFechaNac2.text.toString())
        }
    }




    private fun buscarFechas(fecha:String){
        val url ="exec?spreadsheetId=1VZpNYJHxCU0CZO_PE0NS7r9AnSrmfGWSpbLvQ92D1Ic&sheet=POSTEST-INDICADOR2&fecha=${fecha}"
        val estado = checkInter()
        if(estado.checkForInternet(this)){
            CoroutineScope(Dispatchers.IO).launch {
                val data = getRetrofit().create(apiService::class.java).getbySatifaccion(url)
                val obt = data.body()
                runOnUiThread {
                    if(data != null){
                        val validar = obt?.numeroSatisfecho ?: "0"
                        binding.tv600.text = obt?.numeroSatisfecho ?: "0"
                        binding.tv601.text = obt?.aleatorio ?: "0"
                        binding.tv602.text = obt?.numeroEvaluado ?: "0"
                        binding.tv603.text = obt?.aleatorio1 ?: "0"
                        binding.tv604.text = obt?.nivelSatisfaccion ?: "0"

                        if(validar == "0"){
                            showMsj("No hay datos para la fecha seleccionada")
                        }
                    }else{
                        showMsj("Error al consultar la fecha")
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