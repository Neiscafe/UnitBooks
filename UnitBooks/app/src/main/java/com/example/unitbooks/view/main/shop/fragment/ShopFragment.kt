package com.example.unitbooks.view.main.shop.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unitbooks.R
import com.example.unitbooks.databinding.FragmentShopBinding
import com.example.unitbooks.model.Book
import com.example.unitbooks.view.main.shop.adapter.CatalogAdapter
import com.example.unitbooks.view.main.shop.adapter.HotDealsAdapter
import com.example.unitbooks.view.main.shop.viewmodel.ShopViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding: FragmentShopBinding get() = _binding!!
    private val viewModel by viewModel<ShopViewModel>()
    private lateinit var rvCatalog: RecyclerView
    private lateinit var rvHotDeals: RecyclerView
    private lateinit var svSearchView: SearchView
    private lateinit var catalogAdapter: CatalogAdapter
    private lateinit var hotDealsAdapter: HotDealsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModelObserver()
    }

    override fun onResume() {
        super.onResume()
        setupScreen()
    }

    private fun setupViewModelObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isDataLoaded.observe(viewLifecycleOwner) {
                    checkLoadedData(it)
                }
            }
        }
    }

    private fun checkLoadedData(it: Boolean?) {
        lifecycleScope.launch {
            if (it == true) {
                Log.i("ShopFragment", "True")
                hotDealsDb()
                catalogDataDb()
            } else {
                Log.i("ShopFragment", "False")
                catalogData()
                hotDealsData()
            }
        }
    }

    private fun catalogDataDb() {

    }

    private suspend fun hotDealsDb() {
        viewModel.getHotDealsDb().observe(viewLifecycleOwner) {
            Log.i("ShopFragment", "hotDealsData: Getting data from database")
            hotDealsAdapter.append(it)
        }
    }

    private suspend fun hotDealsData() {
        viewModel.getHotDeals().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                Log.i("ShopFragment", "hotDealsData: Getting data from api")
                hotDealsAdapter.append(it)
                viewModel.insertBooks(it)
            }
        }
    }

    private suspend fun catalogData() {
        viewModel.getPagingBooks().observe(viewLifecycleOwner) {
            catalogAdapter.submitData(lifecycle, it)
        }
    }

    private fun setupScreen() {
        svSearchView = binding.svSearchviewCatalog

        rvCatalog = binding.rvCatalog
        rvHotDeals = binding.rvHotDeals

        catalogAdapter = CatalogAdapter()
        hotDealsAdapter = HotDealsAdapter()

        rvHotDeals.adapter = hotDealsAdapter
        rvCatalog.adapter = catalogAdapter

        rvCatalog.layoutManager = GridLayoutManager(requireContext(), 3)
        rvHotDeals.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        hotDealsAdapter.setOnClickListener(object : HotDealsAdapter.ClickListener {
            override fun onItemClick(position: Int, book: Book) {
            }
        })

        catalogAdapter.setClickListener(object : CatalogAdapter.ClickListener {
            override fun onItemClick(book: Book, position: Int) {
            }
        })
        searchViewCallbacks()
    }

    private fun searchViewCallbacks() {
        svSearchView.setQuery("", false)
        svSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_shopFragment_to_searchFragment)
                    SearchFragment.passQueryArgument(query)
                } ?: return false
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}


