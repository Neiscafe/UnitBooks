package com.example.unitbooks.view.main.shop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.unitbooks.data.Repository
import com.example.unitbooks.model.Book

class SearchViewModel(
    val repository: Repository
): ViewModel() {
    fun getPagingBooksFiltered(query: String): LiveData<PagingData<Book>> {
        return repository.getPagingVolumesFilteredApi(query).cachedIn(viewModelScope)
    }
}