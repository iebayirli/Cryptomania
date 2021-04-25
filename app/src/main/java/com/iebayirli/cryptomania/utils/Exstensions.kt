package com.iebayirli.cryptomania.utils

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Retrofit
import java.util.*

fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, block: (T) -> Unit) {
    observe(owner, Observer { value ->
        value?.let { block(it) }
    })
}

fun String.compare(input: String): Boolean {
    val arr = this.toLowerCase(Locale.ROOT).trim().toCharArray()
    val ss = input.replace(' ', '-')
    val tmp = ss.toLowerCase(Locale.ROOT)
        .trim()
            .toCharArray()

    val len = ss.length
    var counter = 0
    return if (this.length >= len) {
        tmp.forEachIndexed { index, c ->
            if (c == arr[index] || c == ' ')
                counter++
        }
        len == counter
    } else
        false
}