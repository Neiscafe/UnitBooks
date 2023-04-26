package com.example.unitbooks.view.main.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.unitbooks.data.Repository
import com.example.unitbooks.model.BookItem
import com.example.unitbooks.model.BookResponse

class ShopViewModel(
    val repository: Repository
) : ViewModel() {

    suspend fun getPagingBooks(): LiveData<PagingData<BookItem>> {
        val response = repository.getPagingVolumes().cachedIn(viewModelScope)
        return response
    }

    suspend fun getHotDeals(): LiveData<BookResponse>{
        return repository.getHotDeals()
    }

}