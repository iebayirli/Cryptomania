package com.iebayirli.cryptomania.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


abstract class BaseListAdapter<BINDING : ViewDataBinding, T : ListAdapterItem>(
        diffCallback: DiffUtil.ItemCallback<T> = ListAdapterItemDiffCallback()
) : ListAdapter<T, BaseViewHolder<BINDING>>(diffCallback) {

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected abstract fun bind(binding: BINDING, item: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BINDING> {
        val binding = DataBindingUtil.inflate<BINDING>(
                LayoutInflater.from(parent.context),
                layoutRes,
                parent,
                false
        )

        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        bind(holder.binder, getItem(position))
    }
}