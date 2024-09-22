package com.example.aventurape_androidmobile.domains.authentication.models

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class AuthInterceptor(private val token: String?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
       token?.let {
            request.addHeader("Authorization", "Bearer $it")
       }
        return chain.proceed(request.build())
    }
}
