package com.example.recyapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyapplication.data.Albums
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val albumsRepository: AlbumsRepository) : ViewModel() {

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
        object Loading  : AlbumsResult()
        data class Error(val t: Throwable) : AlbumsResult()
    }


}