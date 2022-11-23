package com.example.newfinal.repository
import com.example.newfinal.api.RetrofitInstance
import com.example.newfinal.model.Books
import retrofit2.Response

class Repository {
    suspend fun getBooks(title: String, filter:String, apiKey: String): Response<Books> {
        return RetrofitInstance.api.getBooks(title, filter,  apiKey)
    }
}