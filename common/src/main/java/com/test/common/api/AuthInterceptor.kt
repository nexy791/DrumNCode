package com.test.common.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val apiKey: String,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url.newBuilder().addQueryParameter(QUERY_KEY, apiKey).build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }

    companion object {
        private const val QUERY_KEY = "api_key"
    }
}