package com.radoslav.valigator

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.NullPointerException

/**Created by Radoslav Yankov on 10.12.2018
 * radoslavyankov@gmail.com
 */


/**
 * The 2 main validation classes.
 *
 *  [Validator] is used for simple validation
 *  @param errorText - optional error text
 *  @param validate - validation function, returns boolean. Part of the EditText class, so you have access to the
 *  EditText inside of the function via "this."
 *
 *  [Validator.Custom] is used for validation which requires external data, outside of the EditText scope.
 *  The external data is passed with the addDependency function.
 *  @param <T> - the type of the external data
 */
open class Validator(val errorText: String = VALIDATOR_DEFAULT_ERROR, val validate: EditText.() -> Boolean) {
    class Custom<T>(val errorText: String = VALIDATOR_DEFAULT_ERROR, val validate: EditText.(arg: T?) -> Boolean) {
        var dep: T? = null
        //IMPORTANT: If you use the Custom class, calling addDependency is necessary.
        // You can only have one dependency, but it can be anything (a List for views, for example)
        fun addDependency(dependency: T): Custom<T> {
            this.dep = dependency
            return this
        }
    }
}

const val VALIDATOR_DEFAULT_ERROR = "Validation Error"

/**
 * Function for setting a Validator to an EditText.
 * @param validator - an existing Validator of the [Validator] Class
 */
fun EditText.setValidator(validator: Validator) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            if (!validator.validate(this@setValidator)) this@setValidator.error = validator.errorText else {
                this@setValidator.error = null
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {};
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    })
}

/**
 * Function for setting a Custom Validator to an EditText
 * @param validator - an existing Validator of the [Validator.Custom] Class
 */
fun <T> EditText.setValidator(validator: Validator.Custom<T>) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            if (validator.dep != null) {
                if (!validator.validate(this@setValidator, validator.dep)) this@setValidator.error =
                        validator.errorText else {
                    this@setValidator.error = null
                }
            } else {
                throw NullPointerException("Dependencies are null. Use Validator.Custom's addDependency() function to add dependencies")
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {};
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    })
}

/**
 * Simple extension function which adds a validator immediately, without the need of any other classes
 */
fun EditText.setValidator(errorText: String = VALIDATOR_DEFAULT_ERROR, validate: EditText.() -> Boolean) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            if (!validate()) this@setValidator.error = errorText else {
                this@setValidator.error = null
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {};
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    })
}