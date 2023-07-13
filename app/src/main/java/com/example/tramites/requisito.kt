package com.example.tramites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tramites.databinding.ActivityRequisitoBinding

class requisito : AppCompatActivity() {
    private lateinit var binding : ActivityRequisitoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequisitoBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}