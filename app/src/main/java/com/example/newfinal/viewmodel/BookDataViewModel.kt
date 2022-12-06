package com.example.newfinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.newfinal.model.BookDatabase
import com.example.newfinal.model.BookSearchResultData
import com.example.newfinal.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDataViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<BookSearchResultData>>
    private val repository: BookRepository

    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        readAllData = repository.readAllData
    }

    fun addBook(book: BookSearchResultData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }

    fun deleteBook(book: BookSearchResultData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBook(book)
        }
    }
}