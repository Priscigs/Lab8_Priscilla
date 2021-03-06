package com.example.mynews.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _stateInitialized = MutableLiveData<Boolean>()
    val stateInitialized: LiveData<Boolean>
        get()=_stateInitialized

    init {
        _stateInitialized.value = false
    }

    fun start() {
        _stateInitialized.value = true
    }

    fun finish() {
        _stateInitialized.value = false
    }

}
