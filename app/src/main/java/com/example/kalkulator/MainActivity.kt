package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var isNewOp = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSatu.setOnClickListener{keluaran("1",true)}
        tvDua.setOnClickListener{keluaran("2",true)}
        tvTiga.setOnClickListener{keluaran("3",true)}
        tvEmpat.setOnClickListener{keluaran("4",true)}
        tvLima.setOnClickListener{keluaran("5",true)}
        tvEnam.setOnClickListener{keluaran("6",true)}
        tvTujuh.setOnClickListener{keluaran("7",true)}
        tvDelapan.setOnClickListener{keluaran("8",true)}
        tvSembilan.setOnClickListener{keluaran("9",true)}
        tvnol.setOnClickListener{keluaran("0",true)}
        tvnolnol.setOnClickListener{keluaran("00",true)}
        tvtitik.setOnClickListener{keluaran(".",true)}

        //operator
        tvTambah.setOnClickListener{keluaran("+",false)}
        tvKurang.setOnClickListener{keluaran("-",false)}
        tvBagi.setOnClickListener{keluaran("/",false)}
        tvKali.setOnClickListener{keluaran("x",false)}
        tvminpuls.setOnClickListener{keluaran("-",false)}

        tvac.setOnClickListener{
            tvExpresion.text = ""
            tvResult.text = ""
        }

        tvc.setOnClickListener{
            val i = tvExpresion.text.toString()
            if (i.isNotEmpty()){
                tvExpresion.text= i.substring(0,i.length-1)
            }
            tvResult.text=""
        }

        tvsamadengan.setOnClickListener{
            try {
                val expression = ExpressionBuilder(tvExpresion.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            }catch (e:Exception){
                Log.d("Exception","message : " + e.message)
            }
        }

    }

    fun keluaran(string:String, clear:Boolean){
        if (tvExpresion.text.toString() == "0" || tvExpresion.text.toString() == "00"){
            tvExpresion.text=""
        }
        if (tvExpresion.text.toString() == "+" || tvExpresion.text.toString() == "-"|| tvExpresion.text.toString() == "/"|| tvExpresion.text.toString() == "X"){
            tvExpresion.text=""
        }

        if (isNewOp){
            tvExpresion.text=""
            isNewOp = false
        }

        if (tvResult.text.isNotEmpty()){
            tvExpresion.text=""
        }

        if(clear){
            tvResult.text = ""
            tvExpresion.append(string)
        }else{
            tvExpresion.append(tvResult.text)
            tvExpresion.append(string)
            tvResult.text = ""
        }
    }
}