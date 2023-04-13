package com.example.unitbooks.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.unitbooks.model.BookItem
import com.example.unitbooks.model.BookResponse
import com.example.unitbooks.network.BookService

class RepositoryImpl(
    private val remote: BookService,
    private val pagingSource: CatalogPagingSource
) : Repository {


    override suspend fun getVolumes(): BookResponse {
        return remote.getVolumes(0)
    }

    override fun getPagingVolumes(): LiveData<PagingData<BookItem>> {
        return Pager(PagingConfig(pageSize = 40)) { pagingSource }.liveData
    }
}

interface Repository {
    suspend fun getVolumes(): BookResponse
    fun getPagingVolumes(): LiveData<PagingData<BookItem>>
}
