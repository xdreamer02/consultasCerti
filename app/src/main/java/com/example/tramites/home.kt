package com.example.tramites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.tramites.databinding.ActivityHomeBinding

class home : AppCompatActivity() {

    private lateinit var  binding:ActivityHomeBinding
    private lateinit var tool: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tool = binding.topAppBar1
        tool.setNavigationOnClickListener(){finish()}
        binding.tvRazon.text = Certificado.razonsocial
        cardViewSelect()

        binding.btnCalidad.setOnClickListener {
            val a = Intent(this,fechaTabla::class.java)
            startActivity(a)
        }

        binding.btnSatisfaccion.setOnClickListener {
            val z = Intent(this,fechaTest::class.java)
            startActivity(z)
        }

    }

    private fun cardViewSelect() {
        binding.cv1.setOnClickListener {

            val a = Intent(this,detalle::class.java)
            startActivity(a)
        }
        binding.cv2.setOnClickListener {

            val b = Intent(this,riesgo::class.java)
            startActivity(b)
        }
        binding.cv3.setOnClickListener {
            val a = Intent(this,requisito::class.java)
            startActivity(a)
        }
    }


    private fun showToast(texto:String){
        Toast.makeText(this,texto,Toast.LENGTH_SHORT).show()
    }
}