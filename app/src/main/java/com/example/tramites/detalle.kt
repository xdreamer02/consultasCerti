package com.example.tramites

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.tramites.databinding.ActivityDetalleBinding

class detalle : AppCompatActivity() {
    private lateinit var binding:ActivityDetalleBinding
    private lateinit var tool:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tool = binding.topAppBar2
        binding.tvRazon.text = Certificado.razonsocial
        binding.tvRiesgo.text = Certificado.riesgo

        tool.setNavigationOnClickListener(){finish()}
        // val saturation = 0.5f // Valor de saturación (entre 0 y 1)
        imageColor(binding.iv1, 0f)
        imageColor(binding.iv2, 0f)
        imageColor(binding.iv3, 0f)
        imageColor(binding.iv4, 0f)
        imageColor(binding.iv5, 0f)
        imageColor(binding.iv6, 0f)

        //color y % progressBar
        extracted(Certificado.riesgo!!)



        if(Certificado.riesgo.equals("Bajo") || Certificado.riesgo.equals("Medio")){
            binding.fabMain.isVisible = false
            //Comercializacion
            binding.view1.setBackgroundColor(ContextCompat.getColor(this, R.color.azulate));

            when(Certificado.Estadoexp){
                "En trámite"->  {
                    imageColor(binding.iv1, 1f)

                }
                "Con observaciones"->  {
                    imageColor(binding.iv2, 1f)
                    binding.fabMain.isVisible = true
                    //Agrear numero
                    binding.fabMain.setOnClickListener { chatObs("+51912307990") }
                    binding.tvDesaprobado.text = "Tiene 2 semana para subsanar"
                }
                "Finalizado"->  {
                    binding.view2.setBackgroundColor(ContextCompat.getColor(this, R.color.azulate));
                    imageColor(binding.iv3, 1f)

                    //Catastro
                    when(Certificado.Estadoinspeccion){
                        "En revision" ->imageColor(binding.iv4, 1f)
                        "Programado"->  {
                            imageColor(binding.iv5, 1f)
                            binding.tvDesaprobado.text = "Fecha: "+ Certificado.fechaInspecc +" - Inspector Asignado: " + Certificado.inspectorAsignado

                        }
                        "Aprobado"-> {
                            imageColor(binding.iv5, 1f)
                            imageColor(binding.iv6, 1f)
                            binding.iv5.setImageResource(R.drawable.cheque)
                            binding.tvDatos.text = Certificado.Estadoinspeccion
                        }
                        "Desaprobado"->  {
                            imageColor(binding.iv5, 1f)
                            binding.iv5.setImageResource(R.drawable.cancelar)
                            imageColor(binding.iv6, 1f)
                            binding.tvDesaprobado.text = "Intente denuevo el tramite"
                        }
                        "Con observaciones"->  {
                            imageColor(binding.iv5, 1f)
                            binding.iv5.setImageResource(R.drawable.observacion)
                            binding.tvDatos.text = "Con Observaciones"
                            binding.tvDesaprobado.text = "Tiene 2 semana para subsanar"
                            binding.fabMain.isVisible = true
                            //Agrear numero
                            binding.fabMain.setOnClickListener { chatObs("+51912307990") }

                        }
                    }
                }
            }



        }else {
            binding.fabMain.isVisible = false
            binding.view2.setBackgroundColor(ContextCompat.getColor(this, R.color.azulate));
            //Catastro
            when(Certificado.Estadoinspeccion){
                "En revision" ->imageColor(binding.iv4, 1f)
                "Programado"->  {
                    imageColor(binding.iv5, 1f)
                    binding.tvDesaprobado.text = "Fecha: "+ Certificado.fechaInspecc +" Inspector Asignado: " + Certificado.inspectorAsignado
                    binding.tvDatos.text = "Inspecion Prograda"
                }
                "Aprobado"-> {
                    imageColor(binding.iv5, 1f)
                    imageColor(binding.iv6, 1f)
                    binding.iv5.setImageResource(R.drawable.cheque)
                    binding.tvDatos.text = Certificado.Estadoinspeccion

                    binding.view1.setBackgroundColor(ContextCompat.getColor(this, R.color.azulate))

                    //Comercializacion
                    when(Certificado.Estadoexp){
                        "En trámite"->  {
                            imageColor(binding.iv1, 1f)

                        }
                        "Con observaciones"->  {
                            imageColor(binding.iv2, 1f)
                            binding.fabMain.isVisible = true
                            //Agrear numero
                            binding.fabMain.setOnClickListener { chatObs("+51912307990") }
                            binding.tvDesaprobado.text = "Tiene 2 semana para subsanar"
                        }
                        "Finalizado"->  {
                            binding.view2.setBackgroundColor(ContextCompat.getColor(this, R.color.azulate));
                            imageColor(binding.iv3, 1f)

                        }
                    }

                }
                "Desaprobado"->  {
                    imageColor(binding.iv5, 1f)
                    binding.iv5.setImageResource(R.drawable.cancelar)
                    binding.tvDatos.text = "Desaprobado"
                    binding.tvDesaprobado.text = "Espere una reprogramacion de inspeccion"
                }
                "Con observaciones"->  {
                    imageColor(binding.iv5, 1f)
                    binding.iv5.setImageResource(R.drawable.observacion)
                    binding.tvDatos.text = "Con Observaciones"
                    binding.tvDesaprobado.text = "Tiene 2 semana para subsanar"
                    binding.fabMain.isVisible = true
                    //Agrear numero
                    binding.fabMain.setOnClickListener { chatObs("+51912307990") }

                }

            }

        }
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

    private fun showToast(){
        Toast.makeText(this,"Riesgo Bajo - Medio", Toast.LENGTH_LONG).show()
    }

    private fun imageColor(img:ImageView, saturation:Float){
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



}