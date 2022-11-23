package com.example.newfinal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfinal.model.Books
import com.example.newfinal.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<Books>> = MutableLiveData()

    fun getBooks(title: String, filter:String, apiKey: String) {
        viewModelScope.launch {
            val response: Response<Books> = repository.getBooks(title, filter, apiKey)
            myResponse.value = response
        }
    }
}