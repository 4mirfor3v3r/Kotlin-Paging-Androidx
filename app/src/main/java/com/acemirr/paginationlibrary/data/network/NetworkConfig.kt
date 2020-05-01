package com.acemirr.paginationlibrary.data.network

import com.acemirr.paginationlibrary.BuildConfig
import com.acemirr.paginationlibrary.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkConfig {
    companion object {
        private fun getClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.NONE
            }
            return OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
        }

        private fun getNetwork(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun api(): ApiService = getNetwork().create(ApiService::class.java)
    }
}