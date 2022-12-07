package com.example.newfinal.activity

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newfinal.model.BookSearchResultData

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(books: BookSearchResultData)

    @Query("SELECT * FROM book_data")
    fun readAllData(): LiveData<List<BookSearchResultData>>

    @Delete
    suspend fun deleteBook(book: BookSearchResultData)
}