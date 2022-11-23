package com.example.newfinal.views

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfinal.ListAdapter
import com.example.newfinal.ListElement
import com.example.newfinal.R
import com.example.newfinal.databinding.CardsElementBinding
import com.example.newfinal.databinding.ListElementBinding
import com.example.newfinal.repository.Repository
import com.example.newfinal.utils.Constant
import com.example.newfinal.viewmodel.MainViewModel
import com.example.newfinal.viewmodel.ViewModelFactory

class Recycle_fragment : Fragment(R.layout.cards_element) {

    lateinit var elements: ArrayList<ListElement>
    private lateinit var binding: CardsElementBinding
    private lateinit var viewModel: MainViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CardsElementBinding.bind(view)
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        init()

        binding.poka4to.setOnClickListener{
            findNavController().navigate(R.id.action_recycle_fragment_to_pageFragment)
        }

        viewModel.getBooks("flowers", "partial", "")
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Log.e("success", response.body().toString())
                response.body()!!.items.forEach {
                    val el: ListElement = ListElement(it.volumeInfo!!.imageLinks!!.smallThumbnail.toString(), it.volumeInfo!!.title.toString(), it.volumeInfo!!.authors[0].toString(), "Favorite")
                    elements.add(el);
                }
            }else{
                Log.e("Failed", Constant.BASE_URL.toString())
            }
        })
    }

    fun next(){
        lateinit var binding: ListElementBinding
        binding.selectBook.setOnClickListener{
            findNavController().navigate(R.id.action_recycle_fragment_to_pageFragment)
        }
    }
    fun init(): Unit {
        elements = ArrayList<ListElement>();

        val listAdapter = ListAdapter(elements)
        val recyclerView = binding.listRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = listAdapter
    }




}