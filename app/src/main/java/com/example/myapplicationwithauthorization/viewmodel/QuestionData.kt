package com.example.myapplicationwithauthorization.viewmodel


class QuestionData : ArrayList<DataItem>()

data class DataItem(
    val category: String,
    val question: String,
    val answer: String
)