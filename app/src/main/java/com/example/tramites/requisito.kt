package com.example.tramites

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.tramites.databinding.ActivityRequisitoBinding

class requisito : AppCompatActivity() {
    private lateinit var binding : ActivityRequisitoBinding
    private lateinit var url :String
    private lateinit var tools:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequisitoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tools = binding.topAppBar15
        tools.setNavigationOnClickListener { finish() }

        binding.iv20.setOnClickListener {
             url = "https://saponsapaso159.000webhostapp.com/wp-content/uploads/2023/07/FORMATO-LICENCIA-DE-FUNCIONAMIENTO.pdf"
            activateURL(url)
        }
        binding.iv23.setOnClickListener {
            url = "https://saponsapaso159.000webhostapp.com/wp-content/uploads/2023/07/Declaracion-Jurada-para-informar-el-desarrollo-de-actividades-simultaneas-y-adicionales-a-la-licencia-de-funcionamiento.pdf"
            activateURL(url)
        }
        binding.iv24.setOnClickListener {
            url = "https://saponsapaso159.000webhostapp.com/wp-content/uploads/2023/07/PLANO_ZONIFICACION_ATE_2019.pdf"
            activateURL(url)
        }
        binding.iv25.setOnClickListener {
            url = "https://saponsapaso159.000webhostapp.com/wp-content/uploads/2023/07/Declaracion-Jurada-para-informar-el-cambio-de-giro.pdf"
            activateURL(url)
        }

    }

    private fun activateURL(link:String){
        val intent = Intent(Intent.ACTION_VIEW,Uri.parse(link))
        startActivity(intent)
    }
}