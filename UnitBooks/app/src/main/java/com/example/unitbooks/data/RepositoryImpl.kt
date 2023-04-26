package com.example.unitbooks.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.unitbooks.model.BookItem
import com.example.unitbooks.model.BookResponse
import com.example.unitbooks.network.BookService

class RepositoryImpl(
    private val remote: BookService,
    private val catalogSource: CatalogPagingSource,
    private val searchSource: SearchPagingSource
) : Repository {


    override suspend fun getVolumes(): BookResponse {
        return remote.getVolumes(0)
    }

    override suspend fun getPagingVolumes(): LiveData<PagingData<BookItem>> {
        return Pager(PagingConfig(pageSize = 40)) { catalogSource }.liveData
    }

    override fun getPagingVolumesFiltered(query: String): LiveData<PagingData<BookItem>> {
        searchSource.setQuery(query)
        val pager = Pager(PagingConfig(pageSize = 40)) { searchSource }
        return pager.liveData
    }

    override suspend fun getHotDeals(): LiveData<BookResponse> {
        val liveData = MutableLiveData<BookResponse>()
        liveData.value = remote.getHotDeals(0, 20)
        return liveData
    }
}

interface Repository {
    suspend fun getVolumes(): BookResponse
    suspend fun getPagingVolumes(): LiveData<PagingData<BookItem>>
    fun getPagingVolumesFiltered(query: String): LiveData<PagingData<BookItem>>

    suspend fun getHotDeals(): LiveData<BookResponse>
}
