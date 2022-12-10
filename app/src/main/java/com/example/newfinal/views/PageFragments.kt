package com.example.newfinal.views

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.newfinal.ListElement
import com.example.newfinal.R
import com.example.newfinal.databinding.PageOfBookBinding
import com.example.newfinal.model.BookSearchResultData
import com.example.newfinal.viewmodel.BookDataViewModel
import com.example.newfinal.viewmodel.BookModelFactory
import com.google.gson.Gson

class PageFragments : Fragment(R.layout.page_of_book) {
    var item: ListElement?= null
    private lateinit var binding: PageOfBookBinding;
    private lateinit var mBookDataViewModel: BookDataViewModel
    var id: String = ""
    var bookThumbnail: String = ""
    var bookName: String = ""
    var bookPublisher: String = ""
    var bookDescription: String = ""
    var previewLink: String = ""
    var isFavourite = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PageOfBookBinding.bind(view)
        val factory = BookModelFactory(application = requireActivity().application)

        mBookDataViewModel = ViewModelProvider(this, factory).get(BookDataViewModel::class.java)
        setData()
        val book = BookSearchResultData(
            id,
            bookThumbnail,
            bookName,
            bookPublisher,
            bookDescription,
            previewLink,
            bookThumbnail,
            isFavourite
        )

        binding.save?.setOnClickListener{
            Log.e("errBook", book.isFavourite.toString())
            if (book.isFavourite == true) {
                mBookDataViewModel.deleteBook(book)
                book.isFavourite = false
            } else {
                mBookDataViewModel.addBook(book)
                book.isFavourite = true
            }
        }
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    private fun setData(){
        val jsonStr = arguments?.getString("item")
        Log.d("item", jsonStr.toString())
        if(jsonStr != null){
            item = Gson().fromJson(jsonStr, ListElement::class.java)
            item?.let {
                bindImage(binding.imageView, it.color)
                binding.textView2.setText(it.name)
                binding.textView4.setText(it.author)
                binding.textView3.setText(it.desc)
                id = it.id
                bookThumbnail = it.color
                bookName = it.name
                bookPublisher = it.publisher
                bookDescription = it.desc
            }
        }else{
            Log.e("err", "foo")
        }
    }
}
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}