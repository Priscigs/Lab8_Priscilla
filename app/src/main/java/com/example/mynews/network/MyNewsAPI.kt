package com.example.mynews.network

import com.example.mynews.models.NewsAnswer
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://hn.algolia.com/api/v1/search"

enum class GithubApiStatus {
    START,
    LOADING,
    ERROR,
    DONE
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface MyNewsApiService {
    @GET("items/v1/search")
    fun getProperties(@Query("items") items: String?):
            Call<List<NewsAnswer>>

}

object MyNewsApi {
    val retrofitService : MyNewsApiService by lazy { retrofit.create(MyNewsApiService::class.java) }
}