package com.example.unitbooks.view.main.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.unitbooks.data.Repository
import com.example.unitbooks.model.BookItem

class SearchViewModel(
    val repository: Repository
): ViewModel() {

    private val _booksResponse = MutableLiveData<PagingData<BookItem>>()
    val booksResponse: LiveData<PagingData<BookItem>> get() = _booksResponse

    fun getPagingBooksFiltered(query: String): LiveData<PagingData<BookItem>> {
        val response = repository.getPagingVolumesFiltered(query).cachedIn(viewModelScope)
        _booksResponse.value = response.value
        return response
    }
}