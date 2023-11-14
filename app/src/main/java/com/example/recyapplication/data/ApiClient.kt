package com.example.recyapplication.data

import retrofit2.http.GET

interface ApiClient {
    @GET("/photos")
    suspend fun getSongs(): List<Albums>
}