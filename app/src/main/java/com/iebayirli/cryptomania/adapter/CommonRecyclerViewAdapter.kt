package com.iebayirli.cryptomania.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.iebayirli.cryptomania.BR

class CommonRecyclerViewAdapter<T>(
    @LayoutRes val resourceId: Int,
    var data: List<T>,
    val listener: Any? = null,
    val favouriteSelectListener: Any? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    fun updateData(data: List<T>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            resourceId,
            parent,
            false
        )
        return CommonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CommonRecyclerViewAdapter<*>.CommonViewHolder) {
            val item = data[position]
            if (item != null) {
                holder.setupData(item)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    inner class CommonViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setupData(model: Any) {
            binding.setVariable(BR.data, model)
            binding.setVariable(BR.listener, listener)
            binding.setVariable(BR.favouriteSelectListener, favouriteSelectListener)
            binding.executePendingBindings()
        }
    }
}