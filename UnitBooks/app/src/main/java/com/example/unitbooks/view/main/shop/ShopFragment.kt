package com.example.unitbooks.view.main.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private val catalogAdapter by lazy { CatalogAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listFill()
    }

    private fun listFill() {
        setupWidgets()
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getPagingBooks().observe(viewLifecycleOwner) {
                    catalogAdapter.submitData(lifecycle, it)
                }
            }
        }
    }

    private fun setupWidgets() {
        svSearchView = binding.svSearchview
        rvCatalog = binding.rvCatalog
        rvCatalog.adapter = catalogAdapter
        rvCatalog.layoutManager = GridLayoutManager(requireContext(), 4)
        catalogAdapter.setClickListener(object : CatalogAdapter.ClickListener {
            override fun onItemClick(volumeInfoElement: BookItem, position: Int) {
                Toast.makeText(activity, "Clique ativado", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
