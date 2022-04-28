package com.eos_gnss.producthuntsampleappp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eos_gnss.producthuntsampleappp.GetPostDetailsQuery
import com.eos_gnss.producthuntsampleappp.data.repository.AppRepository
import com.eos_gnss.producthuntsampleappp.util.Resource
import kotlinx.coroutines.launch

class PostDetailViewModel(
    private val appRepository: AppRepository
) : ViewModel() {

    private val getPostDetailDMutableList = MutableLiveData<Resource<GetPostDetailsQuery.Post>>()
    val getPosDetailLiveData: LiveData<Resource<GetPostDetailsQuery.Post>> = getPostDetailDMutableList

    fun getPostDetails(id: String) {
        viewModelScope.launch {
            getPostDetailDMutableList.postValue(Resource.Loading)
            try {
                getPostDetailDMutableList.postValue(Resource.Success(appRepository.getPostDetails(id)))
            } catch (e: Exception) {
                getPostDetailDMutableList.postValue(Resource.Failure(e))
            }
        }
    }
}