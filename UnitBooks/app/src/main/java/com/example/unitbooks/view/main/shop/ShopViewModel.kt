package com.example.unitbooks.view.main.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.unitbooks.data.Repository
import com.example.unitbooks.model.BookItem

class ShopViewModel(
    val repository: Repository
) : ViewModel() {

    private val _booksResponse = MutableLiveData<PagingData<BookItem>>()
    val booksResponse: LiveData<PagingData<BookItem>> get() = _booksResponse

    private val _loading = MutableLiveData<Boolean>()
    val loading: MutableLiveData<Boolean> = _loading

    fun getPagingBooks(): LiveData<PagingData<BookItem>> {
        val response = repository.getPagingVolumes().cachedIn(viewModelScope)
        _booksResponse.value = response.value
        return response
    }

//    suspend fun getBooks() {
//        viewModelScope.launch {
//            _booksResponse.value = repository.getVolumes()
//        }
//    }
}