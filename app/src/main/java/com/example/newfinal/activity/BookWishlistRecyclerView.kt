package com.example.newfinal.activity

import WishlistAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfinal.databinding.ActivityBookWishlistRecyclerViewBinding
import com.example.newfinal.model.BookSearchResultData
import com.example.newfinal.viewmodel.BookDataViewModel

class BookWishlistRecyclerView : AppCompatActivity(), WishlistAdapter.DeleteBookInterface {

    private lateinit var binding: ActivityBookWishlistRecyclerViewBinding
    private lateinit var mBookDataViewModel: BookDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookWishlistRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Wishlist"

        // Recyclerview
        val wishlistAdapter = WishlistAdapter()
        binding.bookWishListRecyclerView.adapter = wishlistAdapter
        binding.bookWishListRecyclerView.layoutManager = LinearLayoutManager(this)

        // BookDataViewModel
        mBookDataViewModel = ViewModelProvider(this).get(BookDataViewModel::class.java)
        mBookDataViewModel.readAllData.observe(this, Observer{ book ->
            wishlistAdapter.setData(book)
        })
    }

    override fun onClick(book: BookSearchResultData) {
        mBookDataViewModel.deleteBook(book)
        Toast.makeText(applicationContext, "Book removed from wishlist", Toast.LENGTH_LONG)
            .show()
    }
}