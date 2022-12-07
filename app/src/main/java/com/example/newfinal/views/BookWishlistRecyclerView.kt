package com.example.newfinal.views

import WishlistAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfinal.ElementAdapter
import com.example.newfinal.ListElement
import com.example.newfinal.R
import com.example.newfinal.databinding.ActivityBookWishlistRecyclerViewBinding
import com.example.newfinal.databinding.CardsElementBinding
import com.example.newfinal.databinding.ListElementBinding

import com.example.newfinal.model.BookSearchResultData
import com.example.newfinal.repository.Repository
import com.example.newfinal.viewmodel.BookDataViewModel
import com.example.newfinal.viewmodel.MainViewModel
import com.example.newfinal.viewmodel.ViewModelFactory
import com.google.gson.Gson

class BookWishlistRecyclerView : Fragment(R.layout.activity_book_wishlist_recycler_view), WishlistAdapter.DeleteBookInterface {
    lateinit var elements: ArrayList<BookSearchResultData>
    private lateinit var binding: ActivityBookWishlistRecyclerViewBinding
    private lateinit var mBookDataViewModel: BookDataViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityBookWishlistRecyclerViewBinding.bind(view )
        mBookDataViewModel = ViewModelProvider(this).get(BookDataViewModel::class.java)
        mBookDataViewModel.readAllData.observe(viewLifecycleOwner, Observer{ book ->
            Log.e("err", book.toString())
            for (item in book){
                elements.add(item);
            }
            binding.bookWishListRecyclerView.adapter?.notifyDataSetChanged()
        })
        init()
    }

    fun init(): Unit {
        elements = ArrayList<BookSearchResultData>();
        val wishlistAdapter = WishlistAdapter()

        wishlistAdapter.submitList(elements)

        binding.bookWishListRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val recyclerView = binding.bookWishListRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = wishlistAdapter
    }

    override fun onClick(book: BookSearchResultData) {
        mBookDataViewModel.deleteBook(book)

    }
}