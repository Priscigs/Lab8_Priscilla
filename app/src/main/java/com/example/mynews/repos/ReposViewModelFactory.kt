package com.example.mynews.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynews.network.MyNewsApi

class ReposViewModelFactory(private val myNewsApi: MyNewsApi) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReposViewModel::class.java)) {
            return ReposViewModel(myNewsApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}