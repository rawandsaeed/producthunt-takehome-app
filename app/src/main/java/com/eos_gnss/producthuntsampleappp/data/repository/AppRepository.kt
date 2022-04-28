package com.eos_gnss.producthuntsampleappp.data.repository

import com.eos_gnss.producthuntsampleappp.GetPostDetailsQuery
import com.eos_gnss.producthuntsampleappp.GetPostsQuery
import com.eos_gnss.producthuntsampleappp.GetProfileDetailsQuery
import com.eos_gnss.producthuntsampleappp.data.datasource.remote.RemoteDataSource
import com.eos_gnss.producthuntsampleappp.util.Resource

class AppRepository(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getPosts(): List<GetPostsQuery.Edge>{
        return remoteDataSource.getPosts()
    }

    suspend fun getPostDetails(id: String): GetPostDetailsQuery.Post {
        return remoteDataSource.getPostDetails(id = id)
    }

    suspend fun getProfileDetails(id: String): GetProfileDetailsQuery.User{
        return remoteDataSource.getProfileDetails(id = id)
    }

}