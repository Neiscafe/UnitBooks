package com.example.unitbooks.view.main.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unitbooks.databinding.FragmentSearchBinding
import com.example.unitbooks.model.BookItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var rvSearch : RecyclerView
    private lateinit var svSearchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWidgetsForSearch()
    }

    fun setupWidgetsForSearch() {
        rvSearch = binding.rvSearch
        searchAdapter = SearchAdapter()
        svSearchView = binding.svSearchview
        rvSearch.adapter = searchAdapter
        rvSearch.layoutManager = GridLayoutManager(requireContext(), 3)
        searchAdapter.setClickListener(object : SearchAdapter.ClickListener {
            override fun onItemClick(bookItem: BookItem, position: Int) {
                Toast.makeText(requireContext(), "${bookItem.volumeInfo.title}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        searchViewCallbacks()
    }

    private fun searchViewCallbacks() {
        svSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.getPagingBooksFiltered(query).observe(viewLifecycleOwner) {
                        searchAdapter.submitData(lifecycle, it)
                    }
                } ?: return false
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        svSearchView.isIconified = false
        svSearchView.setQuery(initialQuery, true)
        svSearchView.clearFocus()
    }

    companion object {
        private lateinit var initialQuery: String
        fun passQueryArgument(query: String){
            initialQuery = query
        }
    }


}