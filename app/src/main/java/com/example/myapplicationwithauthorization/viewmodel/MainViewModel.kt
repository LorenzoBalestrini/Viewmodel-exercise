package com.example.myapplicationwithauthorization.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val retrofit = RetrofitInstance()
    private var _details = MutableLiveData<QuestionData>()
    val details: LiveData<QuestionData>
        get() = _details

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    fun retrieveDetails() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _details.value = retrofit.apiService.getQuestion()
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            }
        }
    }


}