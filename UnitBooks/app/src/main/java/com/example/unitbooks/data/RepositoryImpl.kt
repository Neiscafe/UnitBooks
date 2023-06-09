package com.example.unitbooks.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.unitbooks.database.BookDao
import com.example.unitbooks.model.Book
import com.example.unitbooks.model.BookEntity
import com.example.unitbooks.network.BookService
import com.example.unitbooks.util.NetworkBookMapper

class RepositoryImpl(
    private val remote: BookService,
    private val catalogSource: CatalogPagingSource,
    private val searchSource: SearchPagingSource,
    private val bookDao: BookDao
) : Repository {


    override suspend fun getPagingVolumesApi(): LiveData<PagingData<Book>> {
        return Pager(PagingConfig(pageSize = 40)) { catalogSource }.liveData
    }

    override fun getPagingVolumesFilteredApi(query: String): LiveData<PagingData<Book>> {
        searchSource.setQuery(query)
        val pager = Pager(PagingConfig(pageSize = 40)) { searchSource }
        return pager.liveData
    }

    override suspend fun getHotDealsApi(): LiveData<List<Book>> {
        val liveData = MutableLiveData<List<Book>>()
        val mappedData = NetworkBookMapper().fromEntityList(remote.getHotDeals(0, 20).items)
        liveData.value = mappedData
        return liveData
    }

    override suspend fun insertBooksDb(books: List<Book>) {
        bookDao.insertBookList(books)
    }

    override suspend fun updateBooksDb() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBooksdb() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllBooksDb(): LiveData<List<Book>> {
        val liveData = MutableLiveData<List<Book>>()
        liveData.value = bookDao.getAllBooks()
        return liveData
    }

    override suspend fun getHotDealsDb(): LiveData<List<Book>> {
        val liveData = MutableLiveData<List<Book>>()
        liveData.value = bookDao.getHotDealsDb()
        return liveData
    }
}

interface Repository {
    suspend fun getPagingVolumesApi(): LiveData<PagingData<Book>>
    fun getPagingVolumesFilteredApi(query: String): LiveData<PagingData<Book>>

    suspend fun getHotDealsApi(): LiveData<List<Book>>

    suspend fun insertBooksDb(books: List<Book>)

    suspend fun updateBooksDb()
    suspend fun deleteBooksdb()
    suspend fun getAllBooksDb(): LiveData<List<Book>>
    suspend fun getHotDealsDb(): LiveData<List<Book>>
}
