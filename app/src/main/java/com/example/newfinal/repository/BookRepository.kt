package com.example.newfinal.repository

import androidx.lifecycle.LiveData
import com.example.newfinal.activity.BookDao
import com.example.newfinal.model.BookSearchResultData


class BookRepository(private val bookDao: BookDao) {

    val readAllData: LiveData<List<BookSearchResultData>> = bookDao.readAllData()

    suspend fun addBook(book: BookSearchResultData) {
        bookDao.addBook(book)
    }

    suspend fun deleteBook(book: BookSearchResultData) {
        bookDao.deleteBook(book)
    }
}