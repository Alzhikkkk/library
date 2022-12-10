
package com.example.newfinal.views

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
import com.example.newfinal.databinding.ListElementBinding
import com.example.newfinal.databinding.SearchBinding

import com.example.newfinal.model.BookSearchResultData
import com.example.newfinal.repository.Repository
import com.example.newfinal.viewmodel.MainViewModel
import com.example.newfinal.viewmodel.ViewModelFactory
import com.google.gson.Gson

class SearchFragment : Fragment(R.layout.search) {

    lateinit var elements: ArrayList<ListElement>
    private lateinit var binding: SearchBinding
    private lateinit var databind: ListElementBinding
    private lateinit var viewModel: MainViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SearchBinding.bind(view)
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        init()

        viewModel.getBooks("Harry", "ebooks", "")
        val data = arrayListOf<BookSearchResultData>()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            response.items.forEach {
                val el: ListElement =
                    ListElement(it.id.toString(), it.volumeInfo!!.publisher.toString(), it.volumeInfo!!.imageLinks!!.smallThumbnail.toString(),
                        it.volumeInfo!!.title.toString(),
                        it.volumeInfo!!.authors[0].toString(),
                        it.volumeInfo!!.description.toString()
                    )
                elements.add(el);
                data.add(
                    BookSearchResultData(
                        it.id.toString(),
                        it.volumeInfo!!.imageLinks!!.smallThumbnail.toString(),
                        it.volumeInfo!!.title.toString(),
                        it.volumeInfo!!.publisher.toString(),
                        it.volumeInfo!!.description.toString(),
                        it.volumeInfo!!.previewLink.toString(),
                        it.volumeInfo!!.imageLinks!!.thumbnail.toString()
                    )
                )
                binding.recyclerView.adapter?.notifyDataSetChanged()

            }
        })

        binding.profil.setOnClickListener{
            this.onClickSearch(binding.searchView.query.toString())
        }
    }

    fun next() {
        lateinit var binding: ListElementBinding
        binding.selectBook.setOnClickListener {
            findNavController().navigate(R.id.action_recycle_fragment_to_pageFragment)
        }
    }

    fun init(): Unit {
        elements = ArrayList<ListElement>();
        Log.e("err3", elements.toString())
        val listAdapter = ElementAdapter(::onClickedName)
        listAdapter.submitList(elements)
        val recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = listAdapter
    }

    fun onClickedName(item: ListElement){
        val bundle = Bundle()
        bundle.putString("item", Gson().toJson(item))
        findNavController().navigate(R.id.action_searchFragment_to_pageFragment, bundle)
    }

    fun onClickSearch(search: String){
        val bundle1 = Bundle()
        bundle1.putString("search", search)
        findNavController().navigate(R.id.action_searchFragment_to_recycle_fragment, bundle1)

    }

}