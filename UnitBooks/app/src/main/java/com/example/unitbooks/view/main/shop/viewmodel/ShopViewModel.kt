package com.example.unitbooks.view.main.shop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.unitbooks.data.Repository
import com.example.unitbooks.model.Book

class ShopViewModel(
    val repository: Repository
) : ViewModel() {

    private val _isDataLoaded = MutableLiveData<Boolean>()
    val isDataLoaded get() = _isDataLoaded

    init{
        _isDataLoaded.value = false
    }

    suspend fun getPagingBooks(): LiveData<PagingData<Book>> {
        return repository.getPagingVolumesApi().cachedIn(viewModelScope)
    }

    suspend fun getHotDeals(): LiveData<List<Book>>{
        val data = repository.getHotDealsApi()
        _isDataLoaded.value = true
        return data
    }

    suspend fun insertBooks(books: List<Book>) {
        return repository.insertBooksDb(books)
    }

    suspend fun getHotDealsDb(): LiveData<List<Book>> {
        val data = repository.getHotDealsDb()
        return data
    }

}