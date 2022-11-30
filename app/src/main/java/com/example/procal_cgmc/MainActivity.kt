package com.example.procal_cgmc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.procal_cgmc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var isFirstInput = true // 첫 번째 입력값인지 확인
    var isOperatorClick = false // 연산자가 클릭이 되어 있는지 체크
    var resultNumber = 0.0
    var inputNumber = 0.0
    var operator = "="
    var lastOperator = "+"
    var activityMainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(activityMainBinding!!.root)

        val imageButton = findViewById<View>(R.id.button4) as Button
        imageButton.setOnClickListener { view: View? ->
            val intent = Intent(applicationContext, BitCalActivity::class.java)
            startActivity(intent)
        }

        val image2Button = findViewById<View>(R.id.button5) as Button
        image2Button.setOnClickListener { view: View? ->
            val intent = Intent(applicationContext, BitKeyPadActivity::class.java)
            startActivity(intent)
        }
    }

    fun numButtonClick(view: View) {
        if (isFirstInput) {
            activityMainBinding?.resultTextView?.text = view.tag.toString()
            isFirstInput = false
            if (operator == "=") {
                activityMainBinding?.mathTextView?.text = null
                isOperatorClick = false
            }
        } else {
            if (activityMainBinding?.resultTextView?.text.toString() == "0") {
                Toast.makeText(this, "0으로 시작되는 숫자는 없습니다.", Toast.LENGTH_SHORT).show()
                isFirstInput = true
            } else {
                activityMainBinding?.resultTextView?.append(view.tag.toString())
            }
        }
    }

    fun allClearButtonClick(view: View?) {
        activityMainBinding?.resultTextView?.text = "0"
        activityMainBinding?.mathTextView?.text = ""
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
                resultNumber = activityMainBinding?.resultTextView?.text.toString().toDouble()
                activityMainBinding?.mathTextView?.text = "$resultNumber $operator "
            } else {
                operator = view.tag.toString()
                val getMathText: String = activityMainBinding?.mathTextView?.text.toString()
                val subString = getMathText.substring(0, getMathText.length - 2) // 공백 + 연산자
                activityMainBinding?.mathTextView?.text = subString
                activityMainBinding?.mathTextView?.append("$operator ")
            }
        } else {
            inputNumber = activityMainBinding?.resultTextView?.text.toString().toDouble()
            resultNumber = calculator(resultNumber, inputNumber, operator)
            activityMainBinding?.resultTextView?.text = resultNumber.toString() // operator 넣기
            isFirstInput = true
            operator = view.tag.toString()
            activityMainBinding?.mathTextView?.append("$inputNumber $operator") // mathText에 출력
        }
    }

    fun equalsButtonClick(view: View) {
        if (isFirstInput) { // =이 두번 입력되었을 때
            if (isOperatorClick) {
                activityMainBinding?.mathTextView?.text = "$resultNumber $lastOperator $inputNumber "
                resultNumber = calculator(resultNumber, inputNumber, lastOperator)
                activityMainBinding?.resultTextView?.text = resultNumber.toString()
            }
        } else { // =이 한번일 때
            inputNumber = activityMainBinding?.resultTextView?.text.toString().toDouble()
            resultNumber = calculator(resultNumber, inputNumber, operator)
            activityMainBinding?.resultTextView?.text = resultNumber.toString() // operator 넣기
            isFirstInput = true
            operator = view.tag.toString()
            activityMainBinding?.mathTextView?.append("$inputNumber $operator") // mathText에 출력
        }
    }

    fun pointButtonClick(view: View) {
        if (isFirstInput) {
            activityMainBinding?.resultTextView?.text = "0" + view.tag.toString()
            isFirstInput = false
        } else {
            if (activityMainBinding?.resultTextView?.text.toString().contains(".")) {
                Toast.makeText(this, "이미 소숫점이 존재합니다.", Toast.LENGTH_SHORT).show()
            } else {
                activityMainBinding?.resultTextView?.append(view.tag.toString())
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
            val getResultText: String = activityMainBinding?.resultTextView?.text.toString()
            if (getResultText.length > 1) {
                val subString = getResultText.substring(0, getResultText.length - 1)
                activityMainBinding?.resultTextView?.text = subString
            } else {
                activityMainBinding?.resultTextView?.text = "0"
                isFirstInput = true
            }
        }
    }


}
