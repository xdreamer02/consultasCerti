package com.example.tramites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.tramites.databinding.ActivityModificarBinding
import com.example.tramites.model.DatoDTO
import com.example.tramites.model.resDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class modificar : AppCompatActivity() {
    private lateinit var binding: ActivityModificarBinding
    private lateinit var toolb : Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolb = binding.topAppBar6
        toolb.setNavigationOnClickListener { finish() }

        binding.til70.setEndIconOnClickListener {
            searchCertificado(binding.tie10.text.toString())
        }

        binding.btnUpdateData.setOnClickListener {
            val bodyDatos = resDTO(
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

            updateData(bodyDatos)
            
        }
    }

    private fun searchCertificado(id:String){
        val link = "exec?spreadsheetId=1VZpNYJHxCU0CZO_PE0NS7r9AnSrmfGWSpbLvQ92D1Ic&sheet=Hoja1&expediente=$id"
        val status = checkInter()
        if(status.checkForInternet(this)){
            CoroutineScope(Dispatchers.IO).launch {
                val dat = getRetrofit().create(apiService::class.java).getbyId(link)
                val datass = dat.body()

                runOnUiThread{
                    if(dat != null){
                        binding.tie12.setText(datass?.rucc ?: "No Data")
                        binding.tie13.setText(datass?.razsociall ?: "No Data")
                        binding.tie14.setText(datass?.fecNacc ?: "No Data")
                        binding.tie15.setText(datass?.direccionn ?: "No Data")
                        binding.tie16.setText(datass?.distritoo ?: "No Data")
                        binding.tie17.setText(datass?.telefonoo ?: "No Data")
                        binding.tie18.setText(datass?.emaill ?: "No Data")
                        binding.tie19.setText(datass?.riesgoo ?: "No Data")

                    }else{
                        showMsg("No existe el certificado",2000)
                    }
                }
            }

        }
    }

    private fun updateData(datas:resDTO){
        val stado = checkInter()
        if(stado.checkForInternet((this))){
            val open = getRetrofit2().create(apiService::class.java).uptCertificado(datas)
            open.enqueue(
                object :Callback<DatoDTO>{
                    override fun onResponse(call: Call<DatoDTO>, response: Response<DatoDTO>) {
                       val resp = response.body()
                        showMsg("${resp?.mensaje}",4000)
                        finish()
                    }

                    override fun onFailure(call: Call<DatoDTO>, t: Throwable) {
                        showMsg("Error-Actualizando-Certificado",2000)
                    }

                }
            )
        }
    }

    //get link getbyid
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://script.google.com/macros/s/AKfycbx1iEsHNCSV6dOfH8_rgGHPoz7CHztXWZjn4TGAYp2EN1A_-mgD-7CBFSHZzybd7EBM/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //url update
    private fun getRetrofit2():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://script.google.com/macros/s/AKfycbxGFitf9onsu0cMNBKBs0--Us1iYZOsIj_q87H4xaDT6SsaUwL3noAi9f2rMDQ5wdYp/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun showMsg(msg:String,duration:Int){
        Toast.makeText(this,msg,duration).show()
    }

}