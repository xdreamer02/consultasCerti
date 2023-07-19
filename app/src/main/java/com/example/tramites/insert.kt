package com.example.tramites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.tramites.databinding.ActivityInsertBinding
import com.example.tramites.model.BodyDTO
import com.example.tramites.model.DatoDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class insert : AppCompatActivity() {
    private lateinit var binding: ActivityInsertBinding
    private lateinit var tools:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tools= binding.topAppBar6
        tools.setOnClickListener{finish()}

        binding.btnInsertData.setOnClickListener {
            var datosGUI = BodyDTO(
                binding.tie10.text.toString(),
                binding.tie12.text.toString(),
                binding.tie13.text.toString(),
                binding.tie14.text.toString(),
                binding.tie15.text.toString(),
                binding.tie16.text.toString(),
                binding.tie17.text.toString(),
                binding.tie18.text.toString(),
                binding.tie19.text.toString(),
            )
            addCertificado(datosGUI)
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
                        }

                        override fun onFailure(call: Call<DatoDTO>, t: Throwable) {
                            showToast("Error-Post-Certificados")
                        }

                    }
                )
        }
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://script.google.com/macros/s/AKfycbylxHE1oxv43VSs96exUIp7bbCSXmJOr0uAY4kmfsw0S6Q7rXdpF_6VPO2Uhxe2vq4D/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun showToast(msj:String){
        Toast.makeText(this,msj,Toast.LENGTH_SHORT).show()
    }
}