package com.example.newfinal

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    init {
        Log.e("aaa","smth")
    }

    override fun onCleared(){
        Log.e("aaa","smth")
        super.onCleared()
    }
}