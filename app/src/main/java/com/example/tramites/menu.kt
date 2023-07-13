package com.example.tramites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.tramites.databinding.ActivityMenuBinding

class menu : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding
    private lateinit var tools: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tools = binding.topAppBar7
        tools.setNavigationOnClickListener { finish() }

        cardViewSelect1()


    }

    private fun cardViewSelect1() {
        binding.cv20.setOnClickListener {

            val a = Intent(this,insert::class.java)
            startActivity(a)
        }
        binding.cv21.setOnClickListener {
            val z = Intent(this,modificar::class.java)
            startActivity(z)
        }
    }


    private fun showToast(texto:String){
        Toast.makeText(this,texto, Toast.LENGTH_SHORT).show()
    }
}