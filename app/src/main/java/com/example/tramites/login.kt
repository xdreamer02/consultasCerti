package com.example.tramites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.tramites.databinding.ActivityLoginBinding

class login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var toolv : Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolv = binding.topAppBar5
        toolv.setNavigationOnClickListener(){finish()}

        binding.btnIngresar.setOnClickListener {
            val e = Intent(this,menu::class.java)
            startActivity(e)
        }
    }
}