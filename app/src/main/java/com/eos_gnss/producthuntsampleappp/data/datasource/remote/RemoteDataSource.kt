package com.eos_gnss.producthuntsampleappp.data.datasource.remote

import com.eos_gnss.producthuntsampleappp.GetPostDetailsQuery
import com.eos_gnss.producthuntsampleappp.GetPostsQuery
import com.eos_gnss.producthuntsampleappp.GetProfileDetailsQuery
import com.eos_gnss.producthuntsampleappp.util.Resource

interface RemoteDataSource {

    suspend fun getPosts(): List<GetPostsQuery.Edge>

    suspend fun getPostDetails(id: String): GetPostDetailsQuery.Post

    suspend fun getProfileDetails(id: String): GetProfileDetailsQuery.User
}
