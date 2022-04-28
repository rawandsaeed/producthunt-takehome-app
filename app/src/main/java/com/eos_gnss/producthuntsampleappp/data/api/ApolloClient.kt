package com.eos_gnss.producthuntsampleappp.data.api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response


private var instance: ApolloClient? = null

fun apolloClient(): ApolloClient {

    if (instance != null) return instance!!

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor())
        .build()

    instance = ApolloClient.Builder()
        .serverUrl(BASE_URL)
        .okHttpClient(okHttpClient = okHttpClient)
        .build()

    return instance!!
}

private class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", TOKEN)
            .build()

        return chain.proceed(request)
    }
}