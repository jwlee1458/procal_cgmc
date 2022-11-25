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
        if (isFirstInput) {
            resultTextView?.text = view.tag.toString()
            isFirstInput = false
            if (operator == "=") {
                mathTextView?.text = null
                isOperatorClick = false
            }
        } else {
            if (resultTextView?.text.toString() == "0") {
                Toast.makeText(this, "0으로 시작되는 숫자는 없습니다.", Toast.LENGTH_SHORT).show()
                isFirstInput = true
            } else {
                resultTextView?.append(view.tag.toString())
            }
        }
    }

    fun allClearButtonClick(view: View?) {
        resultTextView?.text = "0"
        mathTextView?.text = ""
        resultNumber = 0.0
        operator = "="
        isFirstInput = true
        isOperatorClick = false
    }

    fun setOperatorClick(view: View) {
        isOperatorClick = true
        lastOperator = view.tag.toString()
        if (isFirstInput) { // 서로 다른 연산자를 연속으로 입력시 연산자 변경
            if (operator == "=") { // =이 눌리면 mathText 연살 결과 값으로 변경
                operator = view.tag.toString()
                resultNumber = resultTextView?.text.toString().toDouble()
                mathTextView?.text = "$resultNumber $operator "
            } else {
                operator = view.tag.toString()
                val getMathText: String = mathTextView?.text.toString()
                val subString = getMathText.substring(0, getMathText.length - 2) // 공백 + 연산자
                mathTextView?.text = subString
                mathTextView?.append("$operator ")
            }
        } else {
            inputNumber = resultTextView?.text.toString().toDouble()
            resultNumber = calculator(resultNumber, inputNumber, operator)
            resultTextView?.text = resultNumber.toString() // operator 넣기
            isFirstInput = true
            operator = view.tag.toString()
            mathTextView?.append("$inputNumber $operator") // mathText에 출력
        }
    }

    fun equalsButtonClick(view: View) {
        if (isFirstInput) { // =이 두번 입력되었을 때
            if (isOperatorClick) {
                mathTextView?.text = "$resultNumber $lastOperator $inputNumber "
                resultNumber = calculator(resultNumber, inputNumber, lastOperator)
                resultTextView?.text = resultNumber.toString()
                radixTransformation()
            }
        } else { // =이 한번일 때
            inputNumber = resultTextView?.text.toString().toDouble()
            resultNumber = calculator(resultNumber, inputNumber, operator)
            resultTextView?.text = resultNumber.toString() // operator 넣기
            isFirstInput = true
            operator = view.tag.toString()
            mathTextView?.append("$inputNumber $operator") // mathText에 출력
            radixTransformation()
        }
    }

    fun pointButtonClick(view: View) {
        if (isFirstInput) {
            resultTextView?.text = "0" + view.tag.toString()
            isFirstInput = false
        } else {
            if (resultTextView?.text.toString().contains(".")) {
                Toast.makeText(this, "이미 소숫점이 존재합니다.", Toast.LENGTH_SHORT).show()
            } else {
                resultTextView?.append(view.tag.toString())
            }
        }
    }

    private fun calculator(resultNumber: Double, inputNumber: Double, operator: String): Double {
        var resultNumber = resultNumber
        when (operator) {
            "=" -> resultNumber = inputNumber
            "+" -> resultNumber += inputNumber
            "-" -> resultNumber -= inputNumber
            "x" -> resultNumber *= inputNumber
            "÷" -> resultNumber /= inputNumber
        }
        return resultNumber
    }

    fun backspaceButtonClick(view: View?) {
        if (!isFirstInput) {
            val getResultText: String = resultTextView?.text.toString()
            if (getResultText.length > 1) {
                val subString = getResultText.substring(0, getResultText.length - 1)
                resultTextView?.text = subString
            } else {
                resultTextView?.text = "0"
                isFirstInput = true
            }
        }
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