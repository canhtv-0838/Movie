package com.canh.movie.utils

import android.os.Build
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.canh.movie.R
import com.canh.movie.data.model.pair.CategoryPair
import com.canh.movie.ui.base.BaseAdapter
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.main.home.HomeFragment
import com.canh.movie.ui.main.home.category.CategoryAdapter
import java.text.SimpleDateFormat
import java.util.*

private const val PROGRESS_UNIT = 10f

object BindingUtils : BaseAdapter.OnItemClickListener {
    @BindingAdapter("progressValue")
    @JvmStatic
    fun bindProgress(progressBar: ProgressBar, value: Double) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress((value * PROGRESS_UNIT).toInt(), true)
        } else {
            progressBar.progress = (value * PROGRESS_UNIT).toInt()
        }
    }

    @BindingAdapter("bindItems", "bindLayoutRes", "bindListener")
    @JvmStatic
    fun <T> bindItems(
        recycler: RecyclerView,
        items: List<T>?,
        layoutRes: Int,
        listener: BaseAdapterItemClickListener<T>
    ) {
        val adapter = BaseAdapter<T>(layoutRes)
        items?.let {
            adapter.apply {
                setItems(items)
                setListener(listener)
            }
        }
        recycler.apply {
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    @BindingAdapter("bindCategories")
    @JvmStatic
    fun bindCategories(recycler: RecyclerView, categories: List<CategoryPair>?) {
        val adapter = CategoryAdapter<CategoryPair>(R.layout.item_category)
        categories?.let {
            adapter.apply {
                setItems(categories)
                setListener(HomeFragment.categoryListener)
            }
        }
        recycler.apply {
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    @BindingAdapter("bindImage")
    @JvmStatic
    fun bindImageFromUrl(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(
                CenterCrop(),
                RoundedCorners(imageView.context.resources.getDimensionPixelSize(R.dimen.dp_8))
            )
            Glide.with(imageView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(StringUtils.getImage(it))
                .into(imageView)
        }
    }

    @BindingAdapter("bindImageNotRoundCorners")
    @JvmStatic
    fun bindImageFromUrlNotRoundCorners(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(
                CenterCrop()
            )
            Glide.with(imageView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(StringUtils.getImage(it))
                .into(imageView)
        }
    }

    @BindingAdapter("bindDate")
    @JvmStatic
    fun formatDate(textView: TextView, date: String) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = inputFormat.parse(date)
        val outputFormatString = outputFormat.format(date)
        textView.text = outputFormatString
    }
}
