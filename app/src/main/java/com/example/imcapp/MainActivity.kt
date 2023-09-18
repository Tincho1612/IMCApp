package com.example.imcapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var maleCard:CardView
    private lateinit var femaleCard:CardView
    private var isMaleSelected:Boolean=true
    private var isFemaleSelected:Boolean=false
    private var currentWeight:Int= 60
    private var currentAge:Int=20
    private var currentHeight:Int=120

    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:RangeSlider
    private lateinit var btnSubstractWeight:FloatingActionButton
    private lateinit var btnPlusWeight:FloatingActionButton
    private lateinit var tvWeight:TextView
    private lateinit var tvAge:TextView
    private lateinit var btnSubstractAge:FloatingActionButton
    private lateinit var btnPlusAge:FloatingActionButton
    private lateinit var btnCalculate:Button
    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
        initUI()

    }



    private fun initListeners() {
        maleCard.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        femaleCard.setOnClickListener {
            changeGender()
            setGenderColor()}
        
        rsHeight.addOnChangeListener { slider, value, fromUser ->
            currentHeight=value.toInt()
            val df=DecimalFormat("#.##")
            val resultado = df.format(value)
            tvHeight.text="${resultado} cm"
        }
        btnSubstractWeight.setOnClickListener {
            currentWeight--
            setWeight()
        }
        btnPlusWeight.setOnClickListener {
            currentWeight++
            setWeight()
        }
        btnPlusAge.setOnClickListener {
            currentAge++
            setAge()
        }
        btnSubstractAge.setOnClickListener {
            currentAge--
            setAge()
        }
        btnCalculate.setOnClickListener {
            navigateToResult(calcular())
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this,ResultImcActivity::class.java)
        intent.putExtra(IMC_KEY,result)
        startActivity(intent)
    }

    private fun calcular():Double {
        val df= DecimalFormat("#.##")
        val imc= currentWeight/(currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()
        
    }

    private fun setAge() {
        tvAge.text=currentAge.toString()
    }

    private fun setWeight() {
        tvWeight.text=currentWeight.toString()
    }

    private fun initComponents(){
        maleCard=findViewById(R.id.male_icn)
        femaleCard=findViewById(R.id.famale_icn)
        tvHeight=findViewById(R.id.tvHeight)
        rsHeight=findViewById(R.id.rsHeight)
        btnSubstractWeight=findViewById(R.id.btnSubstractWeight)
        btnPlusWeight=findViewById(R.id.btnPlusWeight)
        tvWeight=findViewById(R.id.tvWeight)
        tvAge=findViewById(R.id.tvAge)
        btnSubstractAge=findViewById(R.id.btnSubstractAge)
        btnPlusAge=findViewById(R.id.btnPlusAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }
    private fun changeGender(){
        isMaleSelected=!isMaleSelected
        isFemaleSelected=!isFemaleSelected
    }
    private fun setGenderColor(){
        maleCard.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        femaleCard.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent:Boolean): Int {
        val colorReference = if(isSelectedComponent){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }
        return ContextCompat.getColor(this,colorReference)

    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }

}