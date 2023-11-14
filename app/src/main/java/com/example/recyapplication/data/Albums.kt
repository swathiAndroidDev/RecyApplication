package com.example.recyapplication.data

import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String)
