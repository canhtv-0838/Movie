package com.canh.movie.network

import android.content.Context
import android.net.ConnectivityManager
import com.canh.movie.BuildConfig.API_KEY
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import com.google.gson.Gson




object RetrofitBuilder {

    private const val QUERY_PARAMETER_API_KEY = "api_key"
    private const val READ_TIMEOUT = 50L
    private const val WRITE_TIMEOUT = 50L
    private const val CONNECT_TIMEOUT = 50L
    private const val CACHE_SIZE = (10 * 1024 * 1024).toLong()
    private const val TIME_CACHE_ONLINE = "public, max-age = 60"// 1 minute
    private const val TIME_CACHE_OFFLINE = "public, only-if-cached, max-stale = 86400"//1 day
    private const val CACHE_CONTROL = "Cache-Control"

    private val interceptorLogging : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    fun <T> builderApi(
        context: Context,
        baseUrl: String,
        requestApi: Class<T>,
        converterFactory: GsonConverterFactory,
        callAdapterFactory: CoroutineCallAdapterFactory
    ): T =
        Retrofit.Builder().baseUrl(baseUrl)
            .client(initClient(context))
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
            .create(requestApi)

    private fun initClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .cache(Cache(context.codeCacheDir, CACHE_SIZE))
            .addInterceptor(interceptorLogging)
            .addInterceptor {
                var request = it.request()

                if (isNetworkAvailable(context)) {
                    request = request
                        .newBuilder()
                        .header(CACHE_CONTROL, TIME_CACHE_ONLINE)
                        .build();
                } else run {
                    request = request
                        .newBuilder()
                        .header(CACHE_CONTROL, TIME_CACHE_OFFLINE)
                        .build()
                }

                val httpUrl = request.url()
                    .newBuilder()
                    .addQueryParameter(QUERY_PARAMETER_API_KEY, API_KEY)
                    .build()
                val requestBuilder = request
                    .newBuilder()
                    .url(httpUrl)
                it.proceed(requestBuilder.build())

            }.build()

    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

}
