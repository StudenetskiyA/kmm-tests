package com.example.kmm_test.androidApp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmm_test.shared.LatestSongResult
import com.example.kmm_test.shared.ZLog
import com.example.kmm_test.shared.di.EngineSDK
import com.example.kmm_test.shared.di.latestSongInteractor
import com.example.kmm_test.shared.di.logger
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
//    private val _toastState = SingleLiveEvent<String>()
//    val toastState: LiveData<String> get() = _toastState

    private val _state = MutableLiveData<String>().apply {
        value = "Not load yet"
    }
    val state: LiveData<String> = _state

    private val _intState = MutableLiveData<Int>().apply {
        value = 0
    }
    val intState: LiveData<Int> = _intState

    fun test3() {
        EngineSDK.logger.d("pageGenerator","started")
        //EngineSDK.latestSongInteractor.getTest()
        viewModelScope.launch {
            _state.postValue("loading...")
            when (val result = EngineSDK.latestSongInteractor.getLatestSongsListOrException()) {
                is LatestSongResult.Result -> {
                    val song = result.latestSongList[0]
                    _state.postValue("${song.artist} - ${song.title}")
                    //_toastState.emit( "New toast")
                }
                is LatestSongResult.Exception -> {
                    _state.postValue(result.exception)
                    //_toastState.emit(result.exception)
                }
            }
        }
    }

//    fun increment() {
//        _intState.postValue(_intState.value?.plus(1))
//    }
}