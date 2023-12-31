package com.example.tramites

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.tramites.databinding.ActivityRiesgoBinding

class riesgo : AppCompatActivity() {
    private lateinit var binding: ActivityRiesgoBinding
    private lateinit var tool: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiesgoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tool = binding.topAppBar3

        binding.tvRiesgo1.text = Certificado.riesgo

        tool.setNavigationOnClickListener(){finish()}


        // val saturation = 0.5f // Valor de saturación (entre 0 y 1)
        imageColor(binding.iv4, 0f)
        imageColor(binding.iv5, 0f)
        imageColor(binding.iv6, 0f)
        //color y % progressBar
        extracted(Certificado.riesgo!!)

        //binding.view2.setBackgroundColor(ContextCompat.getColor(this, R.color.azulate));
        //Catastro
        if(Certificado.riesgo.equals("Alto") || Certificado.riesgo.equals("Muy alto")){
            binding.cv18.isVisible = false
            binding.btnFlotante1.isVisible = false
            when(Certificado.Estadoinspeccion){
                "En revision" ->imageColor(binding.iv4, 1f)
                "Programado"->  {
                    imageColor(binding.iv5, 1f)
                    binding.cv18.isVisible = true
                    binding.tvDesaprobado.text = "Fecha: "+ Certificado.fechaInspecc +"\nInspector Asignado: " + Certificado.inspectorAsignado+"\nDni Inspector: " + Certificado.dniInnspector
                    binding.tvDatos.text = "Inspección Programada"
                }
                "Aprobado"-> {
                    imageColor(binding.iv5, 1f)
                    imageColor(binding.iv6, 1f)
                    binding.iv5.setImageResource(R.drawable.cheque)
                    binding.tvDatos.text = Certificado.Estadoinspeccion

                    if(Certificado.Estadoexp2 == "Finalizado"){
                        imageColor(binding.iv6, 1f)
                        binding.cv18.isVisible = true
                        binding.tvDesaprobado.text =" EL CERTIFICADO DE INSPECCIÓN TECNICA DE EDIFICICACIÓN SE ENCUENTRA EMITIDO, ACERCARSE A LA MUNICIPALIDAD PARA SU RECOJO"
                    }
                }
                "Desaprobado"->  {
                    imageColor(binding.iv5, 1f)
                    binding.iv5.setImageResource(R.drawable.cancelar)
                    binding.tvDatos.text = "Desaprobado"
                    binding.tvDesaprobado.text = "Espere una reprogramacion de inspeccion"
                    binding.cv18.isVisible = true
                    if(Certificado.Estadoexp2 == "Finalizado"){
                        imageColor(binding.iv6, 1f)
                    }
                }
                "Con observaciones"->  {
                    binding.btnFlotante1.setOnClickListener {chatObs("+51969509757") }
                    imageColor(binding.iv5, 1f)
                    binding.iv5.setImageResource(R.drawable.observacion)
                    binding.tvDatos.text = "Con Observaciones"
                    binding.tvDesaprobado.text = "Tiene 2 semana para subsanar"
                    binding.btnFlotante1.isVisible = true
                    binding.cv18.isVisible = true


                }

            }
        }else{
            binding.cv18.isVisible = false
            binding.btnFlotante1.isVisible = false
        }
    }

    private fun imageColor(img: ImageView, saturation:Float){
        val colorMatrix = ColorMatrix().apply {
            setSaturation(saturation)
        }

        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        img.colorFilter = colorFilter
    }

    private fun chatObs(toNumber:String){

        val url = "https://api.whatsapp.com/send?phone=$toNumber"
        try {
            val pm: PackageManager = this.packageManager
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            this.startActivity(i)
        } catch (e: PackageManager.NameNotFoundException) {
            this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }

    private fun showMessage(msj:String){
        Toast.makeText(this,msj,Toast.LENGTH_SHORT).show()
    }
    private fun extracted(estadoR:String) {
        binding.pbColores.progress = 100

        when(estadoR){
            "Bajo"-> {
                binding.pbColores.setProgressCompat(25, true)
                binding.pbColores.setIndicatorColor(ContextCompat.getColor(this,R.color.color1))
            }
            "Medio"-> {
                binding.pbColores.setProgressCompat(50, true)
                binding.pbColores.setIndicatorColor(ContextCompat.getColor(this,R.color.color2))
            }
            "Alto"-> {
                binding.pbColores.setProgressCompat(75, true)
                binding.pbColores.setIndicatorColor(ContextCompat.getColor(this,R.color.color3))
            }
            "Muy alto"-> {
                binding.pbColores.setProgressCompat(100, true)
                binding.pbColores.setIndicatorColor(ContextCompat.getColor(this,R.color.color4))
            }
        }
    }
}