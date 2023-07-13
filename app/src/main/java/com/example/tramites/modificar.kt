package com.example.tramites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.tramites.databinding.ActivityModificarBinding

class modificar : AppCompatActivity() {
    private lateinit var binding: ActivityModificarBinding
    private lateinit var toolb : Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.til70.setEndIconOnClickListener {
            //boton lupa
        }
    }
}