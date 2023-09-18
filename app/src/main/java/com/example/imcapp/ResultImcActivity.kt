package com.example.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.imcapp.MainActivity.Companion.IMC_KEY

class ResultImcActivity : AppCompatActivity() {
    private lateinit var tvResult:TextView
    private lateinit var tvIMC:TextView
    private lateinit var tvDescription:TextView
    private lateinit var btnRecalculate:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)
        val result = intent.extras?.getDouble(IMC_KEY)?:-1.0
        initComponents()
        initIU(result)
    }

    private fun initIU(result: Double) {
        tvIMC.text=result.toString()
        when (result){
            in 0.00..18.50-> {
                tvResult.text=getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_Bajo))
                tvDescription.text=getString(R.string.description_bajo_peso)

            }
            in 18.51..24.99->{
                tvResult.text=getString(R.string.title_normal_peso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_normal))
                tvDescription.text=getString(R.string.description_normal_peso)

            }
            in 25.00..29.99->{
                tvResult.text=getString(R.string.title_alto_peso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_alto))
                tvDescription.text=getString(R.string.description_alto_peso)

            }
            in 30.00..99.00->{
                tvResult.text=getString(R.string.title_muyAlto_peso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                tvDescription.text=getString(R.string.description_muyAlto_peso)

            }
            else->{
                tvResult.text=getString(R.string.Error)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                tvDescription.text=getString(R.string.Error)
                tvIMC.text=getString(R.string.Error)

            }
        }
    }

    private fun initComponents(){
        tvIMC=findViewById(R.id.tvIMC)
        tvDescription=findViewById(R.id.tvDetails)
        tvResult=findViewById(R.id.tvResult)
        btnRecalculate=findViewById(R.id.btnReCalculate)
    }
    private fun initListeners(){
        btnRecalculate.setOnClickListener {
            onBackPressed()
        }
    }
}