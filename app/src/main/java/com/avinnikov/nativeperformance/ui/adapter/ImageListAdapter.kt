package com.avinnikov.nativeperformance.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avinnikov.nativeperformance.common.adapter.BindingViewHolder
import com.avinnikov.nativeperformance.databinding.RecyclerImageItemBinding

class ImageListAdapter(
    diffCallback: DiffUtil.ItemCallback<Int>,
    private val clickListener: OnRecyclerClickListener<Int>
) : ListAdapter<Int, ImageListAdapter.ImageListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val inflatedView = RecyclerImageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ImageListViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        val item: Int = getItem(position)

        holder.bindTo(item)

        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                clickListener.onItemClicked(holder.adapterPosition)
            }
        }
    }

    class ImageListViewHolder(binding: ViewDataBinding) :
        BindingViewHolder<Int>(binding) {
        override fun bindTo(data: Int) {
            (binding as RecyclerImageItemBinding).drawableRes = data
        }
    }
}