package com.example.newfinal.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class BookModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookDataViewModel::class.java)) {
            return BookDataViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}