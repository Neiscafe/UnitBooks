package com.example.unitbooks.data

import android.content.ContentValues
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unitbooks.model.Book
import com.example.unitbooks.network.BookService
import com.example.unitbooks.util.NetworkBookMapper

class SearchPagingSource(private val remote: BookService) : PagingSource<Int, Book>() {

    private var query: String = "search+terms"
    override fun getRefreshKey(state: PagingState<Int, Book>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {

        val firstElementIndex = params.key ?: 0
        return try {
            val bookResponse = remote.getVolumesFiltered(query, firstElementIndex).items
            val mappedData = NetworkBookMapper().fromEntityList(bookResponse)
            val nextKey = if (mappedData.isEmpty()) {
                null
            } else {
                firstElementIndex + 40
            }

            LoadResult.Page(
                data = mappedData, prevKey = null, nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    fun setQuery(queryText: String){
        query = queryText
    }
}