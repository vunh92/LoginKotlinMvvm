package com.vunh.login_kotlin_mvvm.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.vunh.login_kotlin_mvvm.R


object AppUtils {
//    val BASE_URL = "https://vunh-njs-mongo-01.herokuapp.com/pda/account/login"

    const val BASE_URL = "https://vunh-njs-mongo-01.herokuapp.com/"
    const val DATABASENAME = "database.db"

    fun String.validateEmail(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun Button.isEnable(context: Context, isEnable: Boolean) {
        if (isEnable) {
            background.setTint(ContextCompat.getColor(context, R.color.yellow))
        } else {
            background.setTint(ContextCompat.getColor(context, R.color.gray))
        }
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }
}