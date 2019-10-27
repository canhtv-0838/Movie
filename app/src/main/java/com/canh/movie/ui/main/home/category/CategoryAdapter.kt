package com.canh.movie.ui.main.home.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.canh.movie.BR

class CategoryAdapter<T> constructor(private val layoutResource: Int) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder<T>>() {

    private var listener: OnCategoryClickListener? = null
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

    fun setListener(OnCategoryClickListener: OnCategoryClickListener){
        listener = OnCategoryClickListener
    }

    class ViewHolder<T>(
        private val viewBinding: ViewDataBinding,
        private val listener: OnCategoryClickListener?
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bindData(item: T) {
            viewBinding.setVariable(BR.item, item)
            listener?.let {
                viewBinding.setVariable(BR.categoryListener, listener)
            }
            viewBinding.executePendingBindings()
        }
    }

    interface OnCategoryClickListener
}
