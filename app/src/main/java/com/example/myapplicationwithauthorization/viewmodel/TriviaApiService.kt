package com.example.myapplicationwithauthorization.viewmodel

import retrofit2.http.GET

interface TriviaApiService {
    @GET("/v1/trivia")
    suspend fun getQuestion() : QuestionData
}