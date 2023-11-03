package com.example.recyapplication

import com.example.recyapplication.data.Albums
import com.example.recyapplication.data.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class AlbumsRepository(private val remoteDataSource: RemoteDataSource) {

   suspend fun fetchAlbums() : Flow<List<Albums>> {
        return remoteDataSource.getAlbums()
    }
}