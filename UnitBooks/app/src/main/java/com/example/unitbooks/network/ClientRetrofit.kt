package com.example.unitbooks.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val URL = "https://www.googleapis.com/books/v1/"

class ClientRetrofit {
    companion object {
        fun create(context: Context?): BookService {
//            val logger = HttpLoggingInterceptor()
//
//            logger.level = if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor.Level.BODY
//            } else {
//                HttpLoggingInterceptor.Level.NONE
//            }
//            val client = OkHttpClient.Builder()
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .readTimeout(60, TimeUnit.SECONDS)
//                .addInterceptor(OAuthInterceptor("Bearer", token))
//                .build()
//
            val gson = GsonBuilder()
                .create()

            return Retrofit.Builder()
                .baseUrl(URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(BookService::class.java)
        }
    }
}