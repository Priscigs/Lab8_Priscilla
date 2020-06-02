package com.example.mynews.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsAnswer (
    val id: Int,
    @Json(name = "created_at") val created_at: String,
    @Json(name = "title") val title: String,
    @Json(name = "author") val author: String,
    @Json(name = "points") val points: String
) : Parcelable