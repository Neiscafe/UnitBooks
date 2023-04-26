package com.example.unitbooks.network

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.unitbooks.model.BookItem
import com.example.unitbooks.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("volumes?q=search+terms")
    suspend fun getVolumes(
        @Query(value = "startIndex") startIndex: Int,
        @Query(value = "maxResults") maxResults: Int = 40
    ): BookResponse

    @GET("volumes")
    suspend fun getVolumesFiltered(
        @Query(value = "q") intitleValue: String,
        @Query(value = "startIndex") startIndex: Int,
        @Query(value = "maxResults") maxResults: Int = 40
    ): BookResponse

    @GET("volumes?q=search+terms")
    suspend fun getHotDeals(
        @Query(value = "startIndex") startIndex: Int = 0,
        @Query(value = "maxResults") maxResults: Int = 20
    ): BookResponse
//    @GET("volumes?q=search+terms")
//    suspend fun getPagingVolumes(
//        @Query(value = "startIndex") startIndex: Int,
//        @Query(value = "maxResults") maxResults: Int = 40
//    ): LiveData<PagingData<BookItem>>
}