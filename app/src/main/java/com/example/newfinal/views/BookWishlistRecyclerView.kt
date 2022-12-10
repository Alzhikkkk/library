package com.example.newfinal.views

import WishlistAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfinal.R
import com.example.newfinal.databinding.ActivityBookWishlistRecyclerViewBinding
import com.example.newfinal.model.BookSearchResultData
import com.example.newfinal.viewmodel.BookDataViewModel
import com.example.newfinal.viewmodel.BookModelFactory


class BookWishlistRecyclerView : Fragment(R.layout.activity_book_wishlist_recycler_view), WishlistAdapter.DeleteBookInterface {
    private lateinit var binding: ActivityBookWishlistRecyclerViewBinding
    private var wishlistAdapter :WishlistAdapter?= null
    private lateinit var mBookDataViewModel: BookDataViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityBookWishlistRecyclerViewBinding.bind(view )
        val factory = BookModelFactory(application = requireActivity().application)
        mBookDataViewModel = ViewModelProvider(this, factory).get(BookDataViewModel::class.java)
        mBookDataViewModel.readAllData.observe(viewLifecycleOwner, Observer{ book ->
            wishlistAdapter!!.submitList(book)
        })
        init()
    }

    fun init(): Unit {
        wishlistAdapter = WishlistAdapter(this)
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