package com.radoslav.valigatorexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.radoslav.valigator.Validator
import com.radoslav.valigator.setValidator
import kotlinx.android.synthetic.main.activity_valigator.*

class ValigatorActivity : AppCompatActivity() {

    val normalValidator = Validator("Optional Error Message") {
        text.length < 5
    }

    val customValidator = Validator.Custom<ImageView>("Custom Error Message") { image ->
        image != null && text.contains("spaceX")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valigator)

        edit_text_1.setValidator(normalValidator)
        edit_text_2.setValidator(customValidator.addDependency(image_dependency))

    }
}
