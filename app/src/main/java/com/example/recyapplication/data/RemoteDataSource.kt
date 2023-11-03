package com.example.recyapplication.data

import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getAlbums() : Flow<List<Albums>>
}