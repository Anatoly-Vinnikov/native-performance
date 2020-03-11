package com.avinnikov.nativeperformance.common.equality

import androidx.recyclerview.widget.DiffUtil

class DiffItemCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = true

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = true
}