package com.example.unitbooks.data

import android.content.ContentValues
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unitbooks.model.BookItem
import com.example.unitbooks.network.BookService

class SearchPagingSource(private val remote: BookService) : PagingSource<Int, BookItem>() {

    private var query: String = "search+terms"
    override fun getRefreshKey(state: PagingState<Int, BookItem>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookItem> {

        Log.i(ContentValues.TAG, "load: entrou no load")
        val firstElementIndex = params.key ?: 0
        return try {
            val bookResponse = remote.getVolumesFiltered(query, firstElementIndex).items
            val nextKey = if (bookResponse.isEmpty()) {
                Log.i(ContentValues.TAG, "load: falha no if")
                null
            } else {
                Log.i(ContentValues.TAG, "load: sucesso no if")
                firstElementIndex + 40
            }

            LoadResult.Page(
                data = bookResponse, prevKey = null, nextKey = nextKey
            )
        } catch (e: Exception) {
            Log.i(ContentValues.TAG, "load: falha no try catch ${e.message}")
            LoadResult.Error(e)
        }
    }

    fun setQuery(queryText: String){
        query = queryText
    }
}