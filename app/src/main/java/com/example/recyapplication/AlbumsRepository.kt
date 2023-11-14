package com.example.recyapplication

import com.example.recyapplication.data.Albums
import com.example.recyapplication.data.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumsRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

   suspend fun fetchAlbums() : Flow<List<Albums>> {
        return remoteDataSource.getAlbums()
    }
}