package com.avinnikov.nativeperformance.extensions

import android.content.Context
import android.widget.Toast
import com.avinnikov.nativeperformance.R

fun showToast(context: Context, message: String = context.getString(R.string.message)) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}