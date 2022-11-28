package com.example.newfinal.api


import com.example.newfinal.model.Books
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiBooksInterface {
        @GET(" ")
        suspend fun getBooks(
            @Query("q") inTitle: String,
            @Query("filter") filter: String,
            @Query("key") apiKey: String
        ): Books
}