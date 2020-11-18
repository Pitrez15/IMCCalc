package ipca.example.imccalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_calculator2.*
import kotlinx.android.synthetic.main.activity_main.*

class ActivityCalculator2 : AppCompatActivity() {

    var operand = 0.0
    var operator = ""

    var userIsInTheMiddleOfOperation = false
    var hasDot = false

    var displayFun : ((view : View) -> Unit) = { view : View ->

        var button = (view as Button)

        if(userIsInTheMiddleOfOperation) {

            if (textViewOperation.text.equals("0") && !button.text.equals(".")) {

                textViewOperation.text = ""
            }

            if (button.text.equals(".")) {

                if (!hasDot) {

                    textViewOperation.text = "${textViewOperation.text}."
                    hasDot = true
                }
            }
            else {

                textViewOperation.text = "${textViewOperation.text}${button.text}"
            }
        }
        else {

            textViewOperation.text = "${button.text}"
            userIsInTheMiddleOfOperation = true
        }
    }

    var operationFun : ((view : View) -> Unit) = { view: View ->

        if (operator.equals("")){

            operand = textViewOperation.text.toString().toDouble()
            operator = (view as Button).text.toString()
            userIsInTheMiddleOfOperation = false
            hasDot = false
        }
        else {

            var secondOperand = textViewOperation.text.toString().toDouble()
            var result = doOperation(operand, secondOperand, operator)
            operator = (view as Button).text.toString()
            operand = result
            textViewOperation.text = "${result}"
            userIsInTheMiddleOfOperation = false
            hasDot = false
        }
    }

    fun doOperation (op1:Double, op2:Double, operator:String) : Double {

        var result = 0.0
        when (operator){

            "+" -> {
                result = op1 + op2
            }
            "-" -> {
                result = op1 - op2
            }
            "*" -> {
                result = op1 * op2
            }
            "/" -> {
                result = op1 / op2
            }
        }
        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator2)

        button0.setOnClickListener(displayFun)
        button1.setOnClickListener(displayFun)
        button2.setOnClickListener(displayFun)
        button3.setOnClickListener(displayFun)
        button4.setOnClickListener(displayFun)
        button5.setOnClickListener(displayFun)
        button6.setOnClickListener(displayFun)
        button7.setOnClickListener(displayFun)
        button8.setOnClickListener(displayFun)
        button9.setOnClickListener(displayFun)
        buttonDot.setOnClickListener(displayFun)

        buttonSum.setOnClickListener (operationFun)
        buttonMinus.setOnClickListener (operationFun)
        buttonTimes.setOnClickListener (operationFun)
        buttonDivision.setOnClickListener (operationFun)

        buttonResult.setOnClickListener {

            var secondOperand = textViewOperation.text.toString().toDouble()
            var result = doOperation(operand, secondOperand, operator)
            textViewOperation.text = "${result}"
            userIsInTheMiddleOfOperation = false
        }

        buttonClear.setOnClickListener{

            textViewOperation.text = "0"
            operand = 0.0
            operator = ""
            userIsInTheMiddleOfOperation = false
            hasDot = false
        }
    }
}
