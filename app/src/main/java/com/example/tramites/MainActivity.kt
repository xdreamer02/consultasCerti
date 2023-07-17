package com.example.tramites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tramites.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //probar el !
        binding.btnfindestado.setOnClickListener{
            var expediente = binding.expediente.text.toString().replace("\\s".toRegex(), "")
            var ruc = binding.ruc.text.toString().replace("\\s".toRegex(), "")
            if (!expediente.equals("") && !ruc.equals("")){
                getData(expediente,ruc)
            }else{
                showToast("Complete los campos")
            }

        }

        binding.btnInit.setOnClickListener {
            val i = Intent(this,login::class.java)
            startActivity(i)
        }
    }

    private fun getData(exp:String,ruc:String){
        val check = checkInter()
        val link = "exec?spreadsheetId=1VZpNYJHxCU0CZO_PE0NS7r9AnSrmfGWSpbLvQ92D1Ic&sheet=Hoja1&expediente=$exp&ruc=$ruc"

        if (check.checkForInternet(this)) {
            CoroutineScope(Dispatchers.IO).launch {
                val calls = getRetrofit().create(apiService::class.java)
                    .getValidar(link)
                val tramite = calls.body()
                runOnUiThread {

                    if (calls != null) {
                        Certificado.expediente = tramite?.expediente ?: "No hay user"
                        Certificado.ruc = tramite?.ruc ?: "No hay user"
                        Certificado.razonsocial = tramite?.razon_social ?: "No hay user"
                        Certificado.fechanacimiento = tramite?.fecha_nac ?: "No hay user"
                        Certificado.Direccion = tramite?.dirrec ?: "No hay user"
                        Certificado.expediente = tramite?.expediente ?: "No hay user"
                        Certificado.fechaInspecc = tramite?.fecInspecion ?: "No data"
                        Certificado.riesgo = tramite?.riesgo ?: "No hay data"
                        Certificado.Distrito = tramite?.distrito ?: "No hay user"
                        Certificado.correo = tramite?.correo ?: "No hay user"
                        Certificado.Estadoexp = tramite?.estadoexp ?: "No hay user"
                        Certificado.Estadoexp2 = tramite?.estadoexp2 ?: "No hay user"
                        Certificado.dniInnspector = tramite?.dniInspector ?: "No data"
                        Certificado.inspectorAsignado = tramite?.inspectorAsig ?: "No data"
                        Certificado.Estadoinspeccion = tramite?.estadoInspecion ?: "No hay user"
                        if (Certificado.expediente.equals(exp) && Certificado.ruc.equals(ruc))
                            initActivity()
                        else {
                            val passwordLayout: TextInputLayout =
                                findViewById(R.id.textInputLayout2)
                            passwordLayout.error = "Datos incorrectos"
                            val passwordLayout2: TextInputLayout =
                                findViewById(R.id.textInputLayout3)
                            passwordLayout2.error = "Datos incorrectos"
                        }
                    } else {

                        showToast("Error al validar usuario")

                    }
                }
            }
            // some code
        } else {
            Toast.makeText(this, "Sin Red", Toast.LENGTH_SHORT).show()
        }


    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                //v8 https://script.google.com/macros/s/AKfycbw2VvjxTUqlMcU6_FQUGmxQdSPXsYYG-EfDzKx5IqD00BoZ6v75XOelpkPEx1Nbqthb/
                // v9 https://script.google.com/macros/s/AKfycbxJq-H79kCtBThnuqAA8ZWxCDJuSQ93j_iIn5Iff0cmfPKt_N4PrDOEUvg7WhEBtzcy/exec
            .baseUrl("https://script.google.com/macros/s/AKfycbxJq-H79kCtBThnuqAA8ZWxCDJuSQ93j_iIn5Iff0cmfPKt_N4PrDOEUvg7WhEBtzcy/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun initActivity() {
        val i = Intent(this,home::class.java)
        val passwordLayout: TextInputLayout = findViewById(R.id.textInputLayout2)
        passwordLayout.error = null
        val passwordLayout2: TextInputLayout = findViewById(R.id.textInputLayout3)
        passwordLayout2.error = null
        startActivity(i)

    }
    private fun showToast(dato1:String){
        Toast.makeText(this,dato1, Toast.LENGTH_SHORT).show()
    }
}