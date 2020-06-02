package com.example.mynews.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynews.models.NewsAnswer
import com.example.mynews.network.MyNewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MyNewsStatus{
    START,
    LOADING,
    ERROR,
    DONE
}

class ReposViewModel(myNewsApi: MyNewsApi) : ViewModel() {

    private val _myNewsItem = MutableLiveData<List<NewsAnswer>>()
    val myNewsItem: LiveData<List<NewsAnswer>>
        get() = _myNewsItem

    private val _status = MutableLiveData<MyNewsStatus>()
    val status: LiveData<MyNewsStatus>
        get() = _status

    private val _currentNewsRepo = MutableLiveData<NewsAnswer>()
    val currentGithubRepo: LiveData<NewsAnswer>
        get() = _currentNewsRepo

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        startStatus()
        getRepos()
    }

    fun startStatus(){
        _status.value = MyNewsStatus.START
    }

    fun openNews(newsRepo: NewsAnswer){
        _currentNewsRepo.value = newsRepo
    }

    private fun getRepos(){
        coroutineScope.launch {
            val newsDeferred = MyNewsApi.retrofitService.getProperties(_status.value)
            try {
                _status.value = MyNewsStatus.LOADING
                val repos = newsDeferred.await()
                _status.value = MyNewsStatus.DONE
                _myNewsItem.value = repos
            } catch (e: Exception){
                _status.value = MyNewsStatus.ERROR
                _myNewsItem.value = emptyList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
