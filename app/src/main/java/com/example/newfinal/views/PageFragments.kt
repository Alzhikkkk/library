package com.example.newfinal.views

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.newfinal.R

class PageFragments : Fragment(R.layout.page_of_book) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }
}