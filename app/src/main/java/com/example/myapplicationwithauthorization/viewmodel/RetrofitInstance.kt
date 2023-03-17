package com.example.myapplicationwithauthorization.viewmodel

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_AUTHORIZATION_HEADER = "X-RapidAPI-Key"

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder()
            .addHeader(
                API_AUTHORIZATION_HEADER,
                "bba9fdc651mshc58bdd6e002bf30p18e392jsnd6652b8ccbd4"
            )
            .build()

        return chain.proceed(newRequest)
    }
}

class RetrofitInstance {

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private val authorizationInterceptor = AuthorizationInterceptor()
    private val client = OkHttpClient.Builder()
        .addInterceptor(provideHttpLoggingInterceptor())
        .addInterceptor(authorizationInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://trivia-by-api-ninjas.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val apiService = retrofit.create(TriviaApiService::class.java)

}