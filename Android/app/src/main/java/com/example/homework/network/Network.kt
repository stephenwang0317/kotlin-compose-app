package com.example.homework.network

import com.example.homework.model.entity.ArticleModelItem
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {
    private const val baseUrl =
        "http://8.130.106.215:44444/demo2/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        ).build()


    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

}