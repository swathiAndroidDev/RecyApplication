package com.example.recyapplication.di

import com.example.recyapplication.AlbumsRepository
import com.example.recyapplication.data.ApiClient
import com.example.recyapplication.data.RemoteDataSource
import com.example.recyapplication.data.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesRemoteDataSource(apiClient: ApiClient) : RemoteDataSource{
        return RemoteDataSourceImpl(apiClient)
    }

    @Provides
    fun provideAlbumsRepository(remoteDataSource: RemoteDataSource) : AlbumsRepository{
        return AlbumsRepository(remoteDataSource)
    }
}