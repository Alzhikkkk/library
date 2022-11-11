package com.example.newfinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newfinal.databinding.CardsElementBinding
import com.example.newfinal.databinding.ListElementBinding

class Recycle_fragment : Fragment(R.layout.cards_element) {

    lateinit var elements: ArrayList<ListElement>
    private lateinit var binding: CardsElementBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CardsElementBinding.bind(view)
        init()

        binding.poka4to.setOnClickListener{
            findNavController().navigate(R.id.action_recycle_fragment_to_pageFragment)
        }


    }

    fun next(){
        lateinit var binding: ListElementBinding
        binding.selectBook.setOnClickListener{
            findNavController().navigate(R.id.action_recycle_fragment_to_pageFragment)
        }
    }
    fun init(): Unit {
        elements = ArrayList<ListElement>();
        elements.add(ListElement("#775447", "Harry Potter", "Joanne Rowling", "Favorite"))
        elements.add(ListElement("#607d8b", "Abay Zholy", "Mukhtar Auezov", "Favorite"))
        elements.add(ListElement("#03a9f4", "Rich dad Poor dad", "Robert Kiyosaki", "Not Favorite"))
        elements.add(ListElement("#f44336", "Grokking Algorithms", "Адитья Бхаргава", "Favorite"))
        elements.add(ListElement("#009688", "Flowers for Algernon", "Daniel Keyes", "Not Favorite"))


        val listAdapter = ListAdapter(elements)
        val recyclerView = binding.listRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = listAdapter
    }




}