package com.example.recyapplication.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class RemoteDataSourceImpl(private val apiClient: ApiClient) :RemoteDataSource {
    override suspend fun getAlbums(): Flow<List<Albums>>  = flow {
            emit(apiClient.getSongs())
    }
}