package com.example.procal_cgmc

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import kotlin.math.*


class BitKeyPadActivity : AppCompatActivity() { //비트패턴 기능
    var isFirstInput = true // 첫 번째 입력값인지 확인
    var isCheckButton = true    // 버튼이 클릭되었는지 체크
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
    var resultD:Double = 0.0
    var resultL:Long = 0
    var button_0: TextView? = null
    var button_1: TextView? = null
    var button_2: TextView? = null
    var button_3: TextView? = null
    var button_4: TextView? = null
    var button_5: TextView? = null
    var button_6: TextView? = null
    var button_7: TextView? = null
    var button_8: TextView? = null
    var button_9: TextView? = null
    var button_10: TextView? = null
    var button_11: TextView? = null
    var button_12: TextView? = null
    var button_13: TextView? = null
    var button_14: TextView? = null
    var button_15: TextView? = null
    var button_16: TextView? = null
    var button_17: TextView? = null
    var button_18: TextView? = null
    var button_19: TextView? = null
    var button_20: TextView? = null
    var button_21: TextView? = null
    var button_22: TextView? = null
    var button_23: TextView? = null
    var button_24: TextView? = null
    var button_25: TextView? = null
    var button_26: TextView? = null
    var button_27: TextView? = null
    var button_28: TextView? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bitkeypadactivity_main)

        resultTextView = findViewById<View>(R.id.resultTextView) as TextView
        mathTextView = findViewById<View>(R.id.mathTextView) as TextView
        radix2Num = findViewById<View>(R.id.radix2Num) as TextView
        radix8Num = findViewById<View>(R.id.radix8Num) as TextView
        radix16Num = findViewById<View>(R.id.radix16Num) as TextView
        button_0 = findViewById<View>(R.id.button_0) as TextView
        button_1 = findViewById<View>(R.id.button_1) as TextView
        button_2 = findViewById<View>(R.id.button_2) as TextView
        button_3 = findViewById<View>(R.id.button_3) as TextView
        button_4 = findViewById<View>(R.id.button_4) as TextView
        button_5 = findViewById<View>(R.id.button_5) as TextView
        button_6 = findViewById<View>(R.id.button_6) as TextView
        button_7 = findViewById<View>(R.id.button_7) as TextView
        button_8 = findViewById<View>(R.id.button_8) as TextView
        button_9 = findViewById<View>(R.id.button_9) as TextView
        button_10 = findViewById<View>(R.id.button_10) as TextView
        button_11 = findViewById<View>(R.id.button_11) as TextView
        button_12 = findViewById<View>(R.id.button_12) as TextView
        button_13 = findViewById<View>(R.id.button_13) as TextView
        button_14 = findViewById<View>(R.id.button_14) as TextView
        button_15 = findViewById<View>(R.id.button_15) as TextView
        button_16 = findViewById<View>(R.id.button_16) as TextView
        button_17 = findViewById<View>(R.id.button_17) as TextView
        button_18 = findViewById<View>(R.id.button_18) as TextView
        button_19 = findViewById<View>(R.id.button_19) as TextView
        button_20 = findViewById<View>(R.id.button_20) as TextView
        button_21 = findViewById<View>(R.id.button_21) as TextView
        button_22 = findViewById<View>(R.id.button_22) as TextView
        button_23 = findViewById<View>(R.id.button_23) as TextView
        button_24 = findViewById<View>(R.id.button_24) as TextView
        button_25 = findViewById<View>(R.id.button_25) as TextView
        button_26 = findViewById<View>(R.id.button_26) as TextView
        button_27 = findViewById<View>(R.id.button_27) as TextView
        button_28 = findViewById<View>(R.id.button_28) as TextView


    }

    fun B_NumButtonClick(view: View) {
        var num = view.tag.toString()
        resultD = (2.0).pow(num.toInt())
        resultL = resultD.toLong()

        if(view?.isSelected == false){  //버튼이 클릭 안되었을 때

            when (view.tag.toString()) {
                "0" -> button_0?.setText("1")
                "1" -> button_1?.setText("1")
                "2" -> button_2?.setText("1")
                "3" -> button_3?.setText("1")
                "4" -> button_4?.setText("1")
                "5" -> button_5?.setText("1")
                "6" -> button_6?.setText("1")
                "7" -> button_7?.setText("1")
                "8" -> button_8?.setText("1")
                "9" -> button_9?.setText("1")
                "10" -> button_10?.setText("1")
                "11" -> button_11?.setText("1")
                "12" -> button_12?.setText("1")
                "13" -> button_13?.setText("1")
                "14" -> button_14?.setText("1")
                "15" -> button_15?.setText("1")
                "16" -> button_16?.setText("1")
                "17" -> button_17?.setText("1")
                "18" -> button_18?.setText("1")
                "19" -> button_19?.setText("1")
                "20" -> button_20?.setText("1")
                "21" -> button_21?.setText("1")
                "22" -> button_22?.setText("1")
                "23" -> button_23?.setText("1")
                "24" -> button_24?.setText("1")
                "25" -> button_25?.setText("1")
                "26" -> button_26?.setText("1")
                "27" -> button_27?.setText("1")
                "28" -> button_28?.setText("1")
            }

            if (isFirstInput) {

                resultTextView?.text = resultL.toString()
                isFirstInput = false
                view?.isSelected = view?.isSelected != true

            }
            else{
                var existingResult = resultTextView?.text.toString() //기존 값
                var newNumber:Double = (2.0).pow(num.toInt())
                resultL = existingResult.toLong() + newNumber.toLong()
                resultTextView?.text = resultL.toString()
                view?.isSelected = view?.isSelected != true

            }
            radixTransformation()
        }
        else{   // 클릭 되었을 때
            when (view.tag.toString()) {
                "0" -> button_0?.setText("0")
                "1" -> button_1?.setText("0")
                "2" -> button_2?.setText("0")
                "3" -> button_3?.setText("0")
                "4" -> button_4?.setText("0")
                "5" -> button_5?.setText("0")
                "6" -> button_6?.setText("0")
                "7" -> button_7?.setText("0")
                "8" -> button_8?.setText("0")
                "9" -> button_9?.setText("0")
                "10" -> button_10?.setText("0")
                "11" -> button_11?.setText("0")
                "12" -> button_12?.setText("0")
                "13" -> button_13?.setText("0")
                "14" -> button_14?.setText("0")
                "15" -> button_15?.setText("0")
                "16" -> button_16?.setText("0")
                "17" -> button_17?.setText("0")
                "18" -> button_18?.setText("0")
                "19" -> button_19?.setText("0")
                "20" -> button_20?.setText("0")
                "21" -> button_21?.setText("0")
                "22" -> button_22?.setText("0")
                "23" -> button_23?.setText("0")
                "24" -> button_24?.setText("0")
                "25" -> button_25?.setText("0")
                "26" -> button_26?.setText("0")
                "27" -> button_27?.setText("0")
                "28" -> button_28?.setText("0")
            }

            var existingResult = resultTextView?.text.toString() //기존 값
            var newNumber:Double = (2.0).pow(num.toInt())
            resultL = existingResult.toLong() - newNumber.toLong()
            resultTextView?.text = resultL.toString()
            view?.isSelected = view?.isSelected != true
            radixTransformation()

        }
        


    }


    private fun radixTransformation() {
        val strnum = resultTextView!!.text.toString()
        var num = strnum.toFloat().toInt()

            val str2num = Integer.toBinaryString(num) //10진수 -> 2진수
            radix2Num!!.text = str2num

            val str8num = Integer.toOctalString(num) //10진수 -> 8진수
            radix8Num!!.text = str8num

            val str16num = Integer.toHexString(num) //10진수 -> 16진수
            radix16Num!!.text = str16num
    }
}