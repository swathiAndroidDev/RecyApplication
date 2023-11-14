package com.example.recyapplication.di

import com.example.recyapplication.data.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule
{

    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit{
        val Base_Url = "https://jsonplaceholder.typicode.com/"

        return Retrofit.Builder()
                .baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create()).build()

    }

    @Provides
    @Singleton
     fun providesAPIClient(retrofit: Retrofit) : ApiClient{
         return retrofit.create(ApiClient::class.java)
     }
}