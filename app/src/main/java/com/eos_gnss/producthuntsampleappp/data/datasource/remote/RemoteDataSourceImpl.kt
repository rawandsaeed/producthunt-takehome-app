package com.eos_gnss.producthuntsampleappp.data.datasource.remote

import com.eos_gnss.producthuntsampleappp.GetPostDetailsQuery
import com.eos_gnss.producthuntsampleappp.GetPostsQuery
import com.eos_gnss.producthuntsampleappp.GetProfileDetailsQuery
import com.eos_gnss.producthuntsampleappp.data.api.apolloClient
import com.eos_gnss.producthuntsampleappp.util.Resource

class RemoteDataSourceImpl() : RemoteDataSource {

    override suspend fun getPosts(): List<GetPostsQuery.Edge>{
        val result = apolloClient().query(GetPostsQuery()).execute().data?.posts?.edges
        return result ?: emptyList()
    }

    override suspend fun getPostDetails(id: String): GetPostDetailsQuery.Post {
        val result = apolloClient().query(GetPostDetailsQuery(id = id)).execute()
        return result.data?.post!!
    }

    override suspend fun getProfileDetails(id: String): GetProfileDetailsQuery.User {
        val result = apolloClient().query(GetProfileDetailsQuery(id = id)).execute()
        return result.data?.user!!
    }
}