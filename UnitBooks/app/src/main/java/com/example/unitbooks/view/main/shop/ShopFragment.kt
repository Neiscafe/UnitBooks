package com.example.unitbooks.view.main.shop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unitbooks.R
import com.example.unitbooks.databinding.FragmentShopBinding
import com.example.unitbooks.model.BookItem
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding: FragmentShopBinding get() = _binding!!
    private val viewModel by viewModel<ShopViewModel>()
    private lateinit var rvCatalog: RecyclerView
    private lateinit var svSearchView: SearchView
    private lateinit var catalogAdapter: CatalogAdapter
    private val tvHotDeals by lazy { binding.tvHotDeals }
    private val tvTrending by lazy { binding.tvTrending }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupScreen()
        setupViewModelObserver()
    }

    override fun onResume() {
        super.onResume()
        setupScreen()
    }

    private fun setupViewModelObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getPagingBooks().observe(viewLifecycleOwner) {
                    catalogAdapter.submitData(lifecycle, it)
                }
            }
        }
    }

    private fun setupScreen() {
        svSearchView = binding.svSearchviewCatalog
        rvCatalog = binding.rvCatalog
        catalogAdapter = CatalogAdapter()
        rvCatalog.adapter = catalogAdapter
        rvCatalog.layoutManager = GridLayoutManager(requireContext(), 4)
        catalogAdapter.setClickListener(object : CatalogAdapter.ClickListener {
            override fun onItemClick(volumeInfoElement: BookItem, position: Int) {
                Toast.makeText(requireContext(), "Clique ativado", Toast.LENGTH_SHORT).show()
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


