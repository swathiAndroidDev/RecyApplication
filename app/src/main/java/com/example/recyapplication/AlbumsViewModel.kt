package com.example.recyapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyapplication.data.Albums
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class AlbumsViewModel(private val albumsRepository: AlbumsRepository) : ViewModel() {

    private val _albumsList = MutableStateFlow<AlbumsResult>(AlbumsResult.Loading)
    val albumsList : Flow<AlbumsResult> = _albumsList


    fun fetchAlbums(){
        viewModelScope.launch(Dispatchers.IO){
            albumsRepository.fetchAlbums().collect{
                try{
                    _albumsList.value = AlbumsResult.Success(it)
                }catch (e: Exception){
                    _albumsList.value = AlbumsResult.Error(e)
                }
            }
        }
    }

    sealed class AlbumsResult {
        data class Success(val list: List<Albums>) : AlbumsResult()
        data object Loading  : AlbumsResult()
        data class Error(val t: Throwable) : AlbumsResult()
    }


}