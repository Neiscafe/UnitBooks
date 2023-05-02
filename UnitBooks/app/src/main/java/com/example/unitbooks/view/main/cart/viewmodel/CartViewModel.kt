package com.example.unitbooks.view.main.cart.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.unitbooks.data.Repository
import com.example.unitbooks.di.repositoryModule
import com.example.unitbooks.model.Book

class CartViewModel(private val repository: Repository) : ViewModel() {
    suspend fun getBooksFromDb(): LiveData<List<Book>> {
        return repository.getAllBooksDb()
    }
}