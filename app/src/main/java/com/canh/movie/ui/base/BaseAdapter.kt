package com.canh.movie.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class BaseAdapter<T> constructor(private val layoutResource: Int) :
    RecyclerView.Adapter<BaseAdapter.ViewHolder<T>>() {

    private var listener: OnItemClickListener? = null
    private var items = emptyList<T>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutResource,
                parent,
                false
            ),
            listener
        )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bindData(items[position])
    }

    fun setItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun addItems(items: List<T>) {
        try {
            val lastIndex = this.items.lastIndex
            this.items.toMutableList().addAll(items)
            notifyItemRangeInserted(lastIndex, itemCount)
        } catch (e: Exception){
            e.printStackTrace()
        }

    }

    fun setListener(onItemClickListener: OnItemClickListener){
        listener = onItemClickListener
    }

    class ViewHolder<T>(
        private val viewBinding: ViewDataBinding,
        private val listener: OnItemClickListener?
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bindData(item: T) {
            viewBinding.setVariable(BR.item, item)
            listener?.let {
            viewBinding.setVariable(BR.listener, listener)
            }
            viewBinding.executePendingBindings()
        }
    }

    interface OnItemClickListener
}