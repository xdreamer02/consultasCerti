package com.example.tramites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.tramites.databinding.ActivityInsertBinding
import com.example.tramites.model.BodyDTO
import com.example.tramites.model.DatoDTO
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class insert : AppCompatActivity() {
    private lateinit var binding: ActivityInsertBinding
    private lateinit var tools:Toolbar
    private lateinit var autoTexto:AutoCompleteTextView
    private lateinit var  itemSelc:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tools= binding.topAppBar6
        tools.setOnClickListener{finish()}
        //dropdownlist

        val items = arrayOf("Bajo", "Medio", "Alto", "Muy alto")
        autoTexto = binding.autoTexto
        val adaptador = ArrayAdapter(this,R.layout.list_item,items)
        autoTexto.setAdapter(adaptador)
        autoTexto.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            itemSelc = adapterView.getItemAtPosition(i).toString()
            //showToast(itemSelc)


        }

        (binding.tilLista.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)



        binding.btnInsertData.setOnClickListener {
            if(binding.tie10.text!!.isEmpty() || binding.tie12.text!!.isEmpty()){
                showToast("Completar los campos")
            }else{
                var datosGUI = BodyDTO(
                    binding.tie10.text.toString(),
                    binding.tie12.text.toString(),
                    binding.tie13.text.toString(),
                    binding.tie14.text.toString(),
                    binding.tie15.text.toString(),
                    binding.tie16.text.toString(),
                    binding.tie17.text.toString(),
                    binding.tie18.text.toString(),
                    itemSelc
                )
                addCertificado(datosGUI)
            }


        }
    }


    private fun addCertificado(data:BodyDTO){
        val state = checkInter()
        if(state.checkForInternet(this)){
            val obten = getRetrofit().create(apiService::class.java).addCertificado(data)
            obten.enqueue(
                    object : Callback<DatoDTO>{
                        override fun onResponse(call: Call<DatoDTO>, response: Response<DatoDTO>) {
                            val res = response.body()
                            showToast("${res?.mensaje}")
                            finish()
                        }

                        override fun onFailure(call: Call<DatoDTO>, t: Throwable) {
                            showToast("Error-Agregando-Cetificados")
                        }

                    }
                )
        }
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://script.google.com/macros/s/AKfycbyErn-BSCdXLamR8gAB3UEfstLehcPYFJetu59kBh9CglLjc_gFitXChF2NQsGMpLrg/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun showToast(msj:String){
        Toast.makeText(this,msj,Toast.LENGTH_SHORT).show()
    }

}