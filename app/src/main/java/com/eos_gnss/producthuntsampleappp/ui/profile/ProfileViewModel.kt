package com.eos_gnss.producthuntsampleappp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eos_gnss.producthuntsampleappp.GetProfileDetailsQuery
import com.eos_gnss.producthuntsampleappp.data.repository.AppRepository
import com.eos_gnss.producthuntsampleappp.util.Resource
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val appRepository: AppRepository
): ViewModel() {

    private val getUserProfileMutableList = MutableLiveData<Resource<GetProfileDetailsQuery.User>>()
    val getUserProfileLiveData: LiveData<Resource<GetProfileDetailsQuery.User>> = getUserProfileMutableList

    fun getUserProfile(id: String) {
        viewModelScope.launch {
            getUserProfileMutableList.postValue(Resource.Loading)
            try {
                getUserProfileMutableList.postValue(Resource.Success(appRepository.getProfileDetails(id = id)))
            } catch (e: Exception) {
                getUserProfileMutableList.postValue(Resource.Failure(e))
            }
        }
    }
}