package com.avinnikov.nativeperformance.ui.binding

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("drawableRes")
    fun setImage(view: AppCompatImageView, @DrawableRes drawableRes: Int) {
        view.setImageResource(drawableRes)
    }
}