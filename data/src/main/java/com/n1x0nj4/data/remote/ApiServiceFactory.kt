package com.n1x0nj4.data.remote

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

const val MAX_AGE: Int = 5000

const val DISK_CACHE_SIZE: Long = 10 * 1024 * 1024

const val CONNECTION_TIMEOUT: Long = 3


object ApiServiceFactory {

    fun provideApiService(context: Context): ApiService {
        val okHttpClient = createOkHttpClient(context)
        val retrofit = createRetrofit(okHttpClient)
        return retrofit.create(ApiService::class.java)
    }

    private fun createOkHttpClient(context: Context): OkHttpClient {
        val cacheDir = File(context.cacheDir, "http")
        val cache = Cache(cacheDir, DISK_CACHE_SIZE)
        return OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(StethoInterceptor())
                .addNetworkInterceptor(rewriteOnlineResponseInterceptor())
                .addInterceptor(rewriteOfflineResponseInterceptor(context))
                .build()
    }

    private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("http://www.mocky.io/")
                .build()
    }

    private fun rewriteOnlineResponseInterceptor() = Interceptor { chain ->
        val originalResponse = chain.proceed(chain.request())
        val cacheControl = originalResponse.header("Cache-Control")
        if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
            originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=$MAX_AGE")
                    .build()
        } else {
            originalResponse
        }
    }

    private fun rewriteOfflineResponseInterceptor(context: Context) = Interceptor { chain ->
        var request = chain.request()
        if (!CheckInternetConnection().isNetworkAvailable(context)) {
            // d { "This is offline request" }
            request = request.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached")
                    .build()
        } else {
            // d { "This is online request" }
        }
        chain.proceed(request)
    }
}