package com.example.tramites

import android.content.res.ColorStateList
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
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

        if(Certificado.riesgo.equals("Bajo") || Certificado.riesgo.equals("Medio")){
            //Comercializacion
            binding.view1.setBackgroundColor(ContextCompat.getColor(this, R.color.azulate));

            when(Certificado.Estadoexp){
                "En trámite"->  {
                    imageColor(binding.iv1, 1f)

                }
                "Con observaciones"->  {
                    imageColor(binding.iv2, 1f)
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
                            binding.tvDesaprobado.text = "Intente denuevo el tramite"
                        }
                    }
                }
            }



        }else {
            binding.view2.setBackgroundColor(ContextCompat.getColor(this, R.color.azulate));
            //Catastro
            when(Certificado.Estadoinspeccion){
                "En revision" ->imageColor(binding.iv4, 1f)
                "Programado"->  {
                    imageColor(binding.iv5, 1f)
                    binding.tvDesaprobado.text = "Fecha: "+ Certificado.fechaInspecc +" Inspector Asignado: " + Certificado.inspectorAsignado
                    binding.tvDatos.text = Certificado.Estadoinspeccion
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
                    binding.tvDesaprobado.text = "Espere una reprogramacion de inspeccion"
                }
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
}