package com.radoslav.valigatorexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.radoslav.valigator.Validator
import com.radoslav.valigator.setValidator
import kotlinx.android.synthetic.main.activity_valigator.*

class ValigatorActivity : AppCompatActivity() {

    private val normalValidator = Validator("Optional Error Message") {
        text.length < 5
    }

    private val customValidator = Validator.Custom<RadioButton>("Custom Error Message") { button ->
        button?.isChecked == true && text.contains("spaceX")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valigator)

        edit_text_1.setValidator(normalValidator)
        edit_text_2.setValidator(customValidator.addDependency(button_dependency))
        edit_text_3.setValidator("Simple validation") {
            text.isNotEmpty()
        }

    }
}
