package com.ksoft.ms.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ksoft.ms.BR

abstract class BaseListAdapter<T : BaseItem>(
    diffCallback: DiffUtil.ItemCallback<T> = BaseDiffItemCallback()
) : ListAdapter<T, BaseViewHolder<T>>(diffCallback) {

    private var itemClickListener: ((View, T) -> Unit)? = null

    abstract fun getItemViewTypeByItem(item: T): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val binding = createDataBinding(parent, LayoutInflater.from(parent.context), getLayoutIdByViewType(viewType))

        val viewHolder = createViewHolder(binding, viewType)

        binding.setVariable(BR.clickListener, View.OnClickListener {
            if (viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                itemClickListener?.invoke(it, getItem(viewHolder.adapterPosition))
            }
        })

        onBindingAndHolderCreated(viewHolder, binding, viewType)

        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int, payloads: MutableList<Any>) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItemViewTypeByItem(getItem(position))
    }

    open fun onBindingAndHolderCreated(viewHolder: BaseViewHolder<T>, binding: ViewDataBinding, viewType: Int) {}

    open fun createViewHolder(binding: ViewDataBinding, viewType: Int): BaseViewHolder<T> {
        return BaseViewHolder(binding)
    }

    open fun createDataBinding(parent: ViewGroup, inflater: LayoutInflater, layoutIdByViewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate(inflater, layoutIdByViewType, parent, false)
    }

    open fun getLayoutIdByViewType(viewType: Int): Int {
        return viewType
    }

    fun setItemClickListener(itemClickListener: (View, T) -> Unit) {
        this.itemClickListener = itemClickListener
    }
}
