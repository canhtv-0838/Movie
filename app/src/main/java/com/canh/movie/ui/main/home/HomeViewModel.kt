package com.canh.movie.ui.main.home

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.R
import com.canh.movie.coroutines.getData
import com.canh.movie.data.model.CategoryQuery
import com.canh.movie.data.model.MediaType
import com.canh.movie.data.model.TimeWindow
import com.canh.movie.data.model.pair.CategoryPair
import com.canh.movie.data.model.response.MovieResponse
import com.canh.movie.data.repository.MovieRepository
import com.canh.movie.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(private val movieRepository: MovieRepository, private val context: Context) :
    BaseViewModel() {
    private val language = Locale.getDefault().language
    private val pageLoading = 1

    private val _trendingResponse = MutableLiveData<MovieResponse>()
    private val _categories = MutableLiveData<MutableList<CategoryPair>>()
    private val _categoryPairs: MutableList<CategoryPair> = mutableListOf()

    val trendingResponse: LiveData<MovieResponse> = _trendingResponse
    val categories: LiveData<MutableList<CategoryPair>> = _categories

    val isAllLoadedObservable = ObservableBoolean(false)
    private var isTrendingLoadedObservable = ObservableBoolean(false)
    private var isMovieByCategoryLoadedObservable = ObservableBoolean(false)

    override fun onCreate() {
        getCategoryByType(CategoryQuery.NOW_PLAYING)
        getCategoryByType(CategoryQuery.POPULAR)
        getCategoryByType(CategoryQuery.TOP_RATED)
        getCategoryByType(CategoryQuery.UPCOMING)
        getTrending(MediaType.MOVIE, TimeWindow.WEEK)
    }

    private fun isAllLoaded() =
        isTrendingLoadedObservable.get() && isMovieByCategoryLoadedObservable.get()

    private fun getCategoryByType(@CategoryQuery categoryKey: String) = launch(Dispatchers.IO) {
        movieRepository.getMovieByCategory(categoryKey, language, pageLoading).getData(
            onSuccess = {
                postValueMovieByCategory(categoryKey, it)
                _categories.postValue(_categoryPairs)

                isMovieByCategoryLoadedObservable.set(true)
                isAllLoadedObservable.set(isAllLoaded())
            },
            onFailed = {
                messageNotification.postValue(it.message.toString())
            }
        )
    }

    private fun getTrending(@MediaType mediaType: String, @TimeWindow timeWindow: String) =
        launch(Dispatchers.IO) {
            movieRepository.getTrending(mediaType, timeWindow).getData(
                onSuccess = {
                    _trendingResponse.postValue(it)

                    isTrendingLoadedObservable.set(true)
                    isAllLoadedObservable.set(isAllLoaded())
                },
                onFailed = {
                    messageNotification.postValue(it.message.toString())
                }
            )
        }

    private fun postValueMovieByCategory(@CategoryQuery categoryKey: String, movieResponse: MovieResponse) {

        when (categoryKey) {
            CategoryQuery.NOW_PLAYING -> {
                _categoryPairs.add(
                    CategoryPair(
                        context.getString(R.string.title_now_playing),
                        movieResponse
                    )
                )
            }
            CategoryQuery.POPULAR -> {
                _categoryPairs.add(
                    CategoryPair(
                        context.getString(R.string.title_popular),
                        movieResponse
                    )
                )
            }
            CategoryQuery.TOP_RATED -> {
                _categoryPairs.add(
                    CategoryPair(
                        context.getString(R.string.title_top_rate),
                        movieResponse
                    )
                )
            }
            CategoryQuery.UPCOMING -> {
                _categoryPairs.add(
                    CategoryPair(
                        context.getString(R.string.title_up_coming),
                        movieResponse
                    )
                )
            }
        }
    }

}
