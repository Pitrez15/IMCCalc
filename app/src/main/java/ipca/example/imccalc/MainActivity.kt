package ipca.example.imccalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalcular.setOnClickListener {

            var calcular =
                editTextPeso.text.toString().toFloat() / (editTextAltura.text.toString().toFloat() * editTextAltura.text.toString().toFloat());
            textResultado.text = calcular.toString()
        }
    }
}
