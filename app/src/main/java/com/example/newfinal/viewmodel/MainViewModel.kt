package com.example.newfinal.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfinal.model.Books
import com.example.newfinal.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Books> = MutableLiveData()

    fun getBooks(title: String, filter:String, apiKey: String) {
        viewModelScope.launch {
            try{
                val response: Books = repository.getBooks(title, filter, apiKey)
                myResponse.value = response
            }catch (e: Exception){
                Log.e("eee", e.toString())
            }
        }
    }
}