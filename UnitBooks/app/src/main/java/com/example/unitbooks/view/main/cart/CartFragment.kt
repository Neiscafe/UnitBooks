package com.example.unitbooks.view.main.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.unitbooks.R
import com.example.unitbooks.databinding.FragmentCartBinding
import com.example.unitbooks.view.main.cart.viewmodel.CartViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding: FragmentCartBinding get() = _binding!!
    private val viewModel by viewModel<CartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModelObserver()
    }

    private fun setupViewModelObserver() {
//        lifecycleScope.launch {
//            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
//                viewModel.getBooksFromDb().observe(viewLifecycleOwner) {
//                    binding.tvCart.text = it.size.toString()
//                }
//            }
//        }
    }
}