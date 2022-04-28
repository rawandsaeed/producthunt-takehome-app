package com.eos_gnss.producthuntsampleappp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eos_gnss.producthuntsampleappp.GetPostsQuery
import com.eos_gnss.producthuntsampleappp.data.repository.AppRepository
import com.eos_gnss.producthuntsampleappp.util.Resource
import kotlinx.coroutines.launch

class HomeViewModel(
    private val appRepository: AppRepository
) : ViewModel() {

    private val getPostsMutableList = MutableLiveData<Resource<List<GetPostsQuery.Edge>>>()
    val getPostLiveData: LiveData<Resource<List<GetPostsQuery.Edge>>> = getPostsMutableList

    fun getPosts() {
        viewModelScope.launch {
            getPostsMutableList.postValue(Resource.Loading)
            try {
                getPostsMutableList.postValue(Resource.Success(appRepository.getPosts()))
            } catch (e: Exception) {
                getPostsMutableList.postValue(Resource.Failure(e))
            }

        }
    }
}