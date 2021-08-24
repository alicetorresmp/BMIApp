package com.alice.bmiapp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var weight : EditText
    lateinit var height: EditText
    lateinit var text_view: TextView
    lateinit var health_state: String
    lateinit var body_image : ImageView

    var bmi : Float = 0f
    var new_weight : Float = 0f
    var new_height : Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weight = findViewById(R.id.input_weight)
        height = findViewById(R.id.input_height)
        text_view = findViewById(R.id.text_view)
        body_image = findViewById(R.id.image_body)

        val btnCalculate = findViewById<Button>(R.id.button_calculate)
        btnCalculate.setOnClickListener {
            buttonClicked()
        }
    }


    private fun buttonClicked() {
        new_weight = weight.text.toString().toFloat()
        new_height = height.text.toString().toFloat()/100f
        bmi = new_weight/ (new_height * new_height)
        defineState()
    }

    private fun getImage(state : String) {
        val image = when(state) {
            "underweight" -> R.drawable.underweight
            "healthy" -> R.drawable.healthy
            "overweight" -> R.drawable.overweight
            "obese" -> R.drawable.obesity
            else -> R.drawable.healthy
        }
        body_image.setImageResource(image)
    }

    private fun defineState() {
        body_image.visibility = View.VISIBLE
        when(bmi.toInt()) {
            in 0..18 -> {
                health_state = "underweight"
                getImage(health_state)
                showPhrase()
            }
            in 18..25-> {
                health_state = "healthy"
                getImage(health_state)
                showPhrase()
            }
            in 25..30-> {
                health_state = "overweight"
                getImage(health_state)
                showPhrase()
            }
            in 30..50-> {
                health_state = "obese"
                getImage(health_state)
                showPhrase()
            }
            else-> {
                health_state = "invalid"

            }
        }
    }

    private fun showPhrase() {

        var newText = String.format(resources.getString(R.string.str_your_bmi) + " " + health_state + "!")
        text_view.text = newText
        text_view.visibility = View.VISIBLE
    }
}
