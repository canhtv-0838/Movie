package com.canh.movie.utils

import android.graphics.Color
import android.os.Build
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.canh.movie.R
import com.canh.movie.data.model.CategoryName
import com.canh.movie.data.model.pair.CategoryPair
import com.canh.movie.ui.base.BaseAdapter
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.main.home.HomeFragment
import com.canh.movie.ui.main.home.category.CategoryAdapter
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.fragment_splash.view.*
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

    @BindingAdapter("bindSmallImage")
    @JvmStatic
    fun bindSmallImageFromUrl(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(imageView)
                .load(StringUtils.getSmallImage(it))
                .thumbnail(Glide.with(imageView).load(R.drawable.gif_preloader))
                .error(R.drawable.im_no_image)
                .into(imageView)
        }
    }

//    @BindingAdapter("blurImage")
//    @JvmStatic
//    fun setBlurImage(imageView: ImageView?) {
//        imageView?.let {
//            Blurry.with(imageView.context)
//                .radius(10)
//                .sampling(8)
//                .color(Color.argb(66, 255, 255, 0))
//                .async()
//                .animate(500)
//                .onto()
//        }
//    }

    @BindingAdapter("bindRating")
    @JvmStatic
    fun bindRating(rating: RatingBar, voteAverage: Double) {
        if (voteAverage != 0.0) {
            rating.rating = (voteAverage * (rating.numStars) / 10).toFloat()
        }
    }

    @BindingAdapter("bindCategoryName")
    @JvmStatic
    fun bindCategoryName(textView: TextView, @CategoryName categoryName: String) {
        when (categoryName) {
            CategoryName.NOW_PLAYING -> textView.text =
                textView.context.getString(R.string.title_now_playing)
            CategoryName.POPULAR -> textView.text =
                textView.context.getString(R.string.title_popular)
            CategoryName.TOP_RATED -> textView.text =
                textView.context.getString(R.string.title_top_rate)
            CategoryName.UPCOMING -> textView.text =
                textView.context.getString(R.string.title_upcoming)
        }
    }

    @BindingAdapter("bindYouTubeThumbnail")
    @JvmStatic
    fun setYouTubeThumbnailViewForTrailer(
        thumbnailView: ImageView,
        trailerKey: String
    ) {
        Glide.with(thumbnailView)
            .load(StringUtils.getThumbnail(trailerKey))
            .thumbnail(Glide.with(thumbnailView).load(R.drawable.gif_preloader))
            .error(R.drawable.im_no_image)
            .into(thumbnailView)
    }

    @BindingAdapter("bindDate")
    @JvmStatic
    fun formatDate(textView: TextView, date: String?) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        date?.let {
            val dateOuput: Date = inputFormat.parse(it)
            val outputFormatString = outputFormat.format(dateOuput)
            textView.text = outputFormatString
        }
    }

    @BindingAdapter("bindGender")
    @JvmStatic
    fun formatGender(textView: TextView, gender : Int?){
        gender?.let {
            if (it == 0) textView.text = textView.context.getString(R.string.cast_detail_text_female)
            if (it == 2) textView.text = textView.context.getString(R.string.cast_detail_text_male)
        }
    }
}
