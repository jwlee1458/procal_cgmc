package com.example.procal_cgmc

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BitKeyPadActivity : AppCompatActivity() { //비트패턴 기능
    var isFirstInput = true // 첫 번째 입력값인지 확인
    var isOperatorClick = false // 연산자가 클릭이 되어 있는지 체크
    var resultNumber = 0.0
    var inputNumber = 0.0
    var operator = "="
    var lastOperator = "+"
    var resultTextView: TextView? = null
    var mathTextView : TextView? = null
    var radix2Num: TextView? = null
    var radix8Num: TextView? = null
    var radix16Num: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bitkeypadactivity_main)

        resultTextView = findViewById<View>(R.id.resultTextView) as TextView
        mathTextView = findViewById<View>(R.id.mathTextView) as TextView
        radix2Num = findViewById<View>(R.id.radix2Num) as TextView
        radix8Num = findViewById<View>(R.id.radix8Num) as TextView
        radix16Num = findViewById<View>(R.id.radix16Num) as TextView
    }

    fun numButtonClick(view: View) {

            resultTextView?.text = view.tag.toString()
            isFirstInput = false

    }

    fun equalsButtonClick(view: View) {
         // =이 한번일 때
            inputNumber = resultTextView?.text.toString().toDouble()
            resultTextView?.text = resultNumber.toString() // operator 넣기
            isFirstInput = true
            operator = view.tag.toString()
            mathTextView?.append("$inputNumber $operator") // mathText에 출력
            radixTransformation()

    }


    private fun radixTransformation() {
        val strnum = resultTextView!!.text.toString()
        var num = -1
        if(strnum.indexOf('.') + 2 == strnum.length && strnum.get(strnum.length - 1) == '0') {
            num = strnum.toFloat().toInt()
        } else {
            Toast.makeText(this, "정수만 진수 변환이 가능합니다.", Toast.LENGTH_SHORT).show()
            radix2Num!!.text = ""
            radix8Num!!.text = ""
            radix16Num!!.text = ""
            return
        }

            val str2num = Integer.toBinaryString(num) //10진수 -> 2진수
            radix2Num!!.text = str2num

            val str8num = Integer.toOctalString(num) //10진수 -> 8진수
            radix8Num!!.text = str8num

            val str16num = Integer.toHexString(num) //10진수 -> 16진수
            radix16Num!!.text = str16num
    }
}