package com.example.unitbooks.view.main.shop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.unitbooks.data.Repository
import com.example.unitbooks.model.Book
import com.example.unitbooks.model.BookEntity

class ShopViewModel(
    val repository: Repository
) : ViewModel() {

    suspend fun getPagingBooks(): LiveData<PagingData<Book>> {
        return repository.getPagingVolumesApi().cachedIn(viewModelScope)
    }

    suspend fun getHotDeals(): LiveData<List<Book>>{
        return repository.getHotDealsApi()
    }

}