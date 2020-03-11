package com.avinnikov.nativeperformance.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avinnikov.nativeperformance.common.adapter.BindingViewHolder
import com.avinnikov.nativeperformance.databinding.RecyclerTextItemBinding

class TextListAdapter(
    diffCallback: DiffUtil.ItemCallback<String>,
    private val clickListener: OnRecyclerClickListener<Int>
) : ListAdapter<String, TextListAdapter.TextListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextListViewHolder {
        val inflatedView = RecyclerTextItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TextListViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: TextListViewHolder, position: Int) {
        val item: String = getItem(position)

        holder.bindTo(item)

        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                clickListener.onItemClicked(holder.adapterPosition)
            }
        }
    }

    class TextListViewHolder(binding: ViewDataBinding) :
        BindingViewHolder<String>(binding) {
        override fun bindTo(data: String) {
            (binding as RecyclerTextItemBinding).itemText = data
        }
    }
}