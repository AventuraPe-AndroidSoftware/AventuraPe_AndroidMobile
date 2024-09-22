package com.example.aventurape_androidmobile.utils


import com.example.aventurape_androidmobile.domains.authentication.models.AuthInterceptor
import com.example.aventurape_androidmobile.utils.interfaces.Placeholder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    //aqui coloquen la url base de su api
    private const val BASE_URL = "http://10.0.2.2:8090/api/v1/"
    private var authToken: String? = null
    private val gson: Gson = GsonBuilder().create()

    private val authInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
        authToken?.let {
            request.addHeader("Authorization", "Bearer $it")
        }
        chain.proceed(request.build())
    }
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()



    val placeholder: Placeholder = retrofit.create(Placeholder::class.java)

    fun setToken(token: String?) {
        authToken = token
    }


}